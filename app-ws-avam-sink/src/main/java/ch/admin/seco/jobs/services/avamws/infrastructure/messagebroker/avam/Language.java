package ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam;

//TODO: Maybe extract to a common package (when AVAM source is introduced)
public enum Language {
	DE,
	FR,
	IT;

	public String getChar() {
		switch (this) {
			case FR:
				return "f";
			case IT:
				return "i";
			case DE:
			default:
				return "d";
		}
	}
}
