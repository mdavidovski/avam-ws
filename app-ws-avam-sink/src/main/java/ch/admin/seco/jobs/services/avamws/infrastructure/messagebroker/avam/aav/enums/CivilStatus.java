package ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav.enums;

public enum CivilStatus {

    SINGLE("1"),
    MARRIED_OR_PARTNERSHIP("2"),
    WIDOWED("3"),
    DIVORCED_OR_DISSOLVED_PARTNERSHIP("4");

    private final String avamCode;

    CivilStatus(String avamCode) {
        this.avamCode = avamCode;
    }

    public String getAvamCode() {
        return avamCode;
    }

}
