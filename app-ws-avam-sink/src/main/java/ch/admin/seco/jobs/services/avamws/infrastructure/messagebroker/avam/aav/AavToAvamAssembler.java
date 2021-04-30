package ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav;

import ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav.enums.ContactType;
import ch.admin.seco.jobs.services.avamws.infrastructure.ws.avam.Countries;
import ch.admin.seco.jobs.services.avamws.infrastructure.ws.avam.CountryType;
import ch.admin.seco.jobs.services.avamws.infrastructure.ws.avam.TStesEgov;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDate;

import static org.springframework.util.StringUtils.hasText;

@Component
public class AavToAvamAssembler {

    private static final Logger LOG = LoggerFactory.getLogger(AavToAvamAssembler.class);

    private static final String SWISS_ISO_CODE = "CH";
    private static final BigInteger SWISS_UN_ID = BigInteger.valueOf(756);

    public TStesEgov toStesEgov(AavEventDto dto) {
        TStesEgov aav = new TStesEgov();
        aav.setStesnummerEgov(new BigInteger(dto.getEgovNumber()).longValue());
        aav.setSvNr(new BigInteger(dto.getSvNumber()).longValue());
        aav.setZasVorname(dto.getFirstName()); 
        aav.setZasName(dto.getLastName());
        aav.setGeburtsdatum(formatLocalDate(dto.getBirthday()));
        aav.setGeschlecht(dto.getGender().toString());
        aav.setZivilstand(dto.getCivilStatus().getAvamCode());
        aav.setNationalitaetBfsNr(BigInteger.valueOf(getCountryIdByIsoCode(dto.getNationality()).getUnId()));
        aav.setPostanschriftVorname(dto.getFirstNamePostal());
        aav.setPostanschriftName(dto.getLastNamePostal());
        aav.setStrasse(setEmptyStringIfNull(dto.getAddress().getStreet()));
        aav.setHausNr(setEmptyStringIfNull(dto.getAddress().getHouseNumber()));
        aav.setPostfach(dto.getAddress().getPoBox() != null ? Integer.parseInt(dto.getAddress().getPoBox()) : null);
        aav.setPlzOnrp(Integer.parseInt(dto.getAddress().getPostalCode()));
        aav.setLandBfsNr(SWISS_UN_ID); // Country for AAV is always CH
        aav.setTelefonPrivatNr(sanitizePhoneNumber(dto.getPhone()));
        aav.setTelefonMobileNr(sanitizePhoneNumber(dto.getMobile()));
        aav.setEmail(dto.getEmail());
        aav.setStellenantrittAb(formatLocalDate(dto.getAavFromDate()));
        aav.setBenutzerstellenCode(dto.getJobCenterCode());
        aav.setEmailKontakt(isContactTypeEmail(dto.getContactType()));
        aav.setEgovAnmeldedatum(formatLocalDate(LocalDate.now()));

        if (isNationalityNotSwiss(dto.getNationality())) {
            aav.setAufenthaltsstatus(dto.getResidentialStatus().getAvamCode());
            aav.setAufenthaltBis(formatLocalDate(dto.getResidentialPermissionEndDate()));
        }

        return aav;
    }

    private CountryType getCountryIdByIsoCode(String countryIsoCode) {
        try {
            String path = "schema/eCH0072_210308.xml";
            URL resource = this.getClass().getClassLoader().getResource(path);
            if (resource == null) {
                throw new IllegalStateException("Resource on path " + path + " not found");
            }
            JAXBContext jc = JAXBContext.newInstance(Countries.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            Countries countries =  (Countries) unmarshaller.unmarshal(Paths.get(resource.toURI()).toFile());

            return countries.getCountry().stream()
                    .filter(it -> countryIsoCode.equals(it.getIso2Id()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("Country " + countryIsoCode + " not found"));
        } catch (JAXBException | URISyntaxException e) {
            throw new IllegalStateException("Cannot map values to Country.class");
        }

    }

    private XMLGregorianCalendar formatLocalDate(LocalDate localDate) {
        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(localDate.toString());
        } catch (DatatypeConfigurationException e) {
            throw new IllegalStateException("Date cannot be parsed");
        }
    }

    /*
     * Check for a valid phone number and format as international number,
     * example: +41795551234 -> +41 795 551 234
     */
     private String sanitizePhoneNumber(String phone) {
        if (hasText(phone)) {
            try {
                Phonenumber.PhoneNumber phoneNumber = PhoneNumberUtil.getInstance().parse(phone, "CH");
                return PhoneNumberUtil.getInstance().format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL);
            } catch (NumberParseException e) {
                LOG.warn("Invalid phone number: {}", phone);
            }
        }
        return "";
    }

    private String setEmptyStringIfNull(String value) {
         return hasText(value) ? value : "";
    }

    private boolean isContactTypeEmail(ContactType contactType) {
        return ContactType.EMAIL.equals(contactType);
    }

    private boolean isNationalityNotSwiss(String nationality) {
        return !SWISS_ISO_CODE.equals(nationality);
    }

}
