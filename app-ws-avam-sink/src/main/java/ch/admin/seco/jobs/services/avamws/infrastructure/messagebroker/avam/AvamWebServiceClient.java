package ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam;

public interface AvamWebServiceClient<T extends EventDto> {

    void process(T eventDto);

    boolean canProcess(EventDto eventDto);

}
