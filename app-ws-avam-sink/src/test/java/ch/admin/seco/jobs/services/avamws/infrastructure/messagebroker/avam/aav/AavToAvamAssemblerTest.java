package ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav;


import ch.admin.seco.jobs.services.avamws.infrastructure.ws.avam.TStesEgov;
import org.junit.Test;

import static ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav.AavTestFactory.AAV_FROM_DATE_FORMATTED;
import static ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav.AavTestFactory.AUSTRIAN_UN_ID;
import static ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav.AavTestFactory.BIRTHDAY_FORMATTED;
import static ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav.AavTestFactory.CIVIL_STATUS;
import static ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav.AavTestFactory.EGOV_NUMBER_FORMATTED;
import static ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav.AavTestFactory.EMAIL;
import static ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav.AavTestFactory.FIRST_NAME;
import static ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav.AavTestFactory.FIRST_NAME_POSTAL;
import static ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav.AavTestFactory.GENDER;
import static ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav.AavTestFactory.HOUSE_NUMBER;
import static ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav.AavTestFactory.JOB_CENTER_CODE;
import static ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav.AavTestFactory.LAST_NAME;
import static ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav.AavTestFactory.LAST_NAME_POSTAL;
import static ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav.AavTestFactory.MOBILE_FORMATTED;
import static ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav.AavTestFactory.PHONE_FORMATTED;
import static ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav.AavTestFactory.POSTAL_CODE_FORMATTED;
import static ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav.AavTestFactory.PO_BOX_FORMATTED;
import static ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav.AavTestFactory.RESIDENTIAL_PERMISSION_END_DATE_FORMATTED;
import static ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav.AavTestFactory.RESIDENTIAL_STATUS;
import static ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav.AavTestFactory.STREET;
import static ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav.AavTestFactory.SV_NUMBER_FORMATTED;
import static ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav.AavTestFactory.SWISS_UN_ID;
import static org.assertj.core.api.Assertions.assertThat;

public class AavToAvamAssemblerTest {

    private final AavToAvamAssembler aavToAvamAssembler = new AavToAvamAssembler();

    @Test
    public void toStesEgovFull() {
        // Given
        AavEventDto dto = AavTestFactory.testAavEventDto();

        // When
        TStesEgov aav = aavToAvamAssembler.toStesEgov(dto);

        // Assert Aav
        assertThat(aav.getStesnummerEgov()).isEqualTo(EGOV_NUMBER_FORMATTED);
        assertThat(aav.getSvNr()).isEqualTo(SV_NUMBER_FORMATTED);
        assertThat(aav.getZasVorname()).isEqualTo(FIRST_NAME);
        assertThat(aav.getZasName()).isEqualTo(LAST_NAME);
        assertThat(aav.getGeburtsdatum()).isEqualTo(BIRTHDAY_FORMATTED);
        assertThat(aav.getGeschlecht()).isEqualTo(GENDER.toString());
        assertThat(aav.getZivilstand()).isEqualTo(CIVIL_STATUS.getAvamCode());
        assertThat(aav.getNationalitaetBfsNr()).isEqualTo(AUSTRIAN_UN_ID);
        assertThat(aav.getPostanschriftVorname()).isEqualTo(FIRST_NAME_POSTAL);
        assertThat(aav.getPostanschriftName()).isEqualTo(LAST_NAME_POSTAL);
        assertThat(aav.getStrasse()).isEqualTo(STREET);
        assertThat(aav.getHausNr()).isEqualTo(HOUSE_NUMBER);
        assertThat(aav.getPostfach()).isEqualTo(PO_BOX_FORMATTED);
        assertThat(aav.getPlzOnrp()).isEqualTo(POSTAL_CODE_FORMATTED);
        assertThat(aav.getLandBfsNr()).isEqualTo(SWISS_UN_ID);
        assertThat(aav.getTelefonPrivatNr()).isEqualTo(PHONE_FORMATTED);
        assertThat(aav.getTelefonMobileNr()).isEqualTo(MOBILE_FORMATTED);
        assertThat(aav.getEmail()).isEqualTo(EMAIL);
        assertThat(aav.getStellenantrittAb()).isEqualTo(AAV_FROM_DATE_FORMATTED);
        assertThat(aav.getBenutzerstellenCode()).isEqualTo(JOB_CENTER_CODE);
        assertThat(aav.isEmailKontakt()).isEqualTo(true);
        assertThat(aav.getAufenthaltsstatus()).isEqualTo(RESIDENTIAL_STATUS.getAvamCode());
        assertThat(aav.getAufenthaltBis()).isEqualTo(RESIDENTIAL_PERMISSION_END_DATE_FORMATTED);
    }

    @Test
    public void toStesEgovFalsePhoneNumber() {
        // Given
        AavEventDto dto = AavTestFactory.testAavEventDto();
        dto.setPhone("Wrong Format");
        dto.setMobile("Wrong Format");

        // When
        TStesEgov aav = aavToAvamAssembler.toStesEgov(dto);

        // Assert Aav
        assertThat(aav.getTelefonPrivatNr()).isEmpty();
        assertThat(aav.getTelefonMobileNr()).isEmpty();
    }
}