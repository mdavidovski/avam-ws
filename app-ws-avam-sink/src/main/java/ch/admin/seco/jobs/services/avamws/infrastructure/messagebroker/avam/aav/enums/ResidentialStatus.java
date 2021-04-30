package ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav.enums;

public enum ResidentialStatus {

    B1("10"), // B EU/EFTA (Aufenthaltsbewilligung)
    B2("11"), // B (Aufenthaltsbewilligung)
    B3("12"), // B (Aufenthaltsbewilligung, anerkannte Flüchtlinge)
    C1("20"), // C EU/EFTA (Niederlassungsbewilligung)
    C2("21"), // C (Niederlassungsbewilligung)
    C3("25"), // Ci EU/EFTA (Aufenthaltsbewill. mit Erwerbstätigk.)
    C4("26"), // Ci (Aufenthaltsbewilligung mit Erwerbstätigkeit)
    F1("40"), // F (Vorläufig aufgenommene Ausländer)
    F2("41"), // F (Vorläufig aufgenommene Flüchtlinge)
    G1("50"), // G EU/EFTA (Grenzgängerbewilligung)
    G2("51"), // G (Grenzgängerbewilligung)
    L1("70"), // L EU/EFTA (Kurzaufenthaltsbewilligung)
    L2("71"), // L (Kurzaufenthaltsbewilligung)
    N("90"), // N (Ausweis für Asylsuchende)
    K("99"); // K (Noch nicht abgeklärt)

    private final String avamCode;

    ResidentialStatus(String avamCode) {
        this.avamCode = avamCode;
    }

    public String getAvamCode() {
        return avamCode;
    }

}
