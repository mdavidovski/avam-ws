package ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam;

public interface AvamService<T extends EventDto> {

    void process(T whatever);

    boolean canProcess(EventDto whatever);
}
