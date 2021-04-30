package ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav;

import ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav.enums.CivilStatus;
import ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav.enums.ContactType;
import ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav.enums.Gender;
import ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav.enums.ResidentialStatus;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;
import java.time.LocalDate;

public class AavTestFactory {

    // Aav Event Dto
    static final Gender GENDER = Gender.MALE;
    static final String FIRST_NAME = "John";
    static final String LAST_NAME = "Doe";
    static final String FIRST_NAME_POSTAL = "J.";
    static final String LAST_NAME_POSTAL = "D.";
    static final String PHONE = "+41431231212";
    static final String MOBILE = "+41433212121";
    static final String EMAIL = "test@email.com";
    static final LocalDate BIRTHDAY = LocalDate.of(1992, 12, 1);
    static final String SV_NUMBER = "7569217076985";
    static final String EGOV_NUMBER = "9912345678909";
    static final CivilStatus CIVIL_STATUS = CivilStatus.MARRIED_OR_PARTNERSHIP;
    static final ContactType CONTACT_TYPE = ContactType.EMAIL;
    static final LocalDate AAV_FROM_DATE = LocalDate.of(2021, 1, 1);
    static final String NATIONALITY = "AT";
    static final ResidentialStatus RESIDENTIAL_STATUS = ResidentialStatus.B1;
    static final LocalDate RESIDENTIAL_PERMISSION_END_DATE = LocalDate.of(2031, 1, 1);

    // Address
    static final String STREET = "Test street";
    static final String HOUSE_NUMBER = "321";
    static final String PO_BOX = "123";
    static final String POSTAL_CODE = "3006";
    static final String CITY = "Bern";
    static final String COUNTRY = "CH";

    // Job Center (RAV)
    static final String JOB_CENTER_CODE = "joc";

    // Aav formatted values
    static final BigInteger AUSTRIAN_UN_ID = BigInteger.valueOf(40);
    static final BigInteger SWISS_UN_ID = BigInteger.valueOf(756);

    static final long EGOV_NUMBER_FORMATTED = 9912345678909L;
    static final long SV_NUMBER_FORMATTED = 7569217076985L;
    static final String PHONE_FORMATTED = "+41 43 123 12 12";
    static final String MOBILE_FORMATTED = "+41 43 321 21 21";
    static final XMLGregorianCalendar BIRTHDAY_FORMATTED = getXMLGregorianCalendarFromString("1992-12-01");
    static final Integer PO_BOX_FORMATTED = 123;
    static final int POSTAL_CODE_FORMATTED = 3006;
    static final XMLGregorianCalendar AAV_FROM_DATE_FORMATTED = getXMLGregorianCalendarFromString("2021-01-01");
    static final XMLGregorianCalendar RESIDENTIAL_PERMISSION_END_DATE_FORMATTED = getXMLGregorianCalendarFromString("2031-01-01");

    static AavEventDto testAavEventDto() {
        return new AavEventDto()
                .setEgovNumber(EGOV_NUMBER)
                .setGender(GENDER)
                .setJobCenterCode(JOB_CENTER_CODE)
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setFirstNamePostal(FIRST_NAME_POSTAL)
                .setLastNamePostal(LAST_NAME_POSTAL)
                .setPhone(PHONE)
                .setMobile(MOBILE)
                .setEmail(EMAIL)
                .setAddress(testAddress())
                .setBirthday(BIRTHDAY)
                .setSvNumber(SV_NUMBER)
                .setCivilStatus(CIVIL_STATUS)
                .setContactType(CONTACT_TYPE)
                .setAavFromDate(AAV_FROM_DATE)
                .setNationality(NATIONALITY)
                .setResidentialStatus(RESIDENTIAL_STATUS)
                .setResidentialPermissionEndDate(RESIDENTIAL_PERMISSION_END_DATE);
    }

    private static Address testAddress() {
        return new Address()
                .setStreet(STREET)
                .setHouseNumber(HOUSE_NUMBER)
                .setPoBox(PO_BOX)
                .setPostalCode(POSTAL_CODE)
                .setCity(CITY)
                .setCountry(COUNTRY);
    }

    private static XMLGregorianCalendar getXMLGregorianCalendarFromString(String date) {
        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(date);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }

}
