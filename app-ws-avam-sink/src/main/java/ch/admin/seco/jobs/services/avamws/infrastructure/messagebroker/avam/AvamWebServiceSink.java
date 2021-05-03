package ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam;


import ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav.AavEventDto;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import java.util.List;
import java.util.stream.Collectors;

@EnableBinding(Sink.class)
public class AvamWebServiceSink {

    private final List<AvamWebServiceClient<? extends EventDto>> avamWebServiceClients;

    public AvamWebServiceSink(List<AvamWebServiceClient<? extends EventDto>> avamWebServiceClients) {
        this.avamWebServiceClients = avamWebServiceClients;
    }

    @StreamListener(target = Sink.INPUT, condition = "headers[event]=='AavSubmitted'")
    public void submit(AavEventDto dto) {
      AvamWebServiceClient<AavEventDto> validAvamImplementation = (AvamWebServiceClient<AavEventDto>) getValidAvamImplementation(dto, avamWebServiceClients);
      validAvamImplementation.process(dto);
    }

    private AvamWebServiceClient<? extends EventDto> getValidAvamImplementation(EventDto dto, List<AvamWebServiceClient<? extends EventDto>> avamWebServiceClients) {
        List<AvamWebServiceClient<? extends EventDto>> validImplementations = avamWebServiceClients.stream()
                .filter(it -> it.canProcess(dto))
                .collect(Collectors.toList());

        //TODO: Throw proper exception
        if (validImplementations.size() != 1) {
            throw new IllegalStateException("Implementation not found");
        }

        return validImplementations.get(0);
    }

}

