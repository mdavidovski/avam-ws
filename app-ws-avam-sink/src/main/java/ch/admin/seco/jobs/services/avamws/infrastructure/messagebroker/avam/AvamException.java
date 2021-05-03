package ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam;

public class AvamException extends RuntimeException {

    public AvamException(String objectId, String action, String avamResponse) {
        super("Couldn't send Object with id: " + objectId +
                " action: " + action + "\n" + "Details: " + avamResponse);
    }
}
