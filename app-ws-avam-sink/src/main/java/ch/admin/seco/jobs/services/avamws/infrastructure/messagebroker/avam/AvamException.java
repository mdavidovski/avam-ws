package ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam;

//FIXME
public class AvamException extends RuntimeException {

    private final String action;
    private final String avamResponse;

    public AvamException(String action, String avamResponse) {
        super("Custom message");
        this.action = action;
        this.avamResponse = avamResponse;
    }
}
