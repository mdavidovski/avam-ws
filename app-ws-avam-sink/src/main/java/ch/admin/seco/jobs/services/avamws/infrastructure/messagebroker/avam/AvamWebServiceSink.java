package ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam;


import ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav.AavEventDto;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import java.util.List;
import java.util.stream.Collectors;

@EnableBinding(Sink.class)
public class AvamWebServiceSink {

    private final AvamWebServiceClient avamWebServiceClient;

    private final List<AvamService<? extends EventDto>> avamServices;

    public AvamWebServiceSink(AvamWebServiceClient avamWebServiceClient, List<AvamService<? extends EventDto>> avamServices) {
        this.avamWebServiceClient = avamWebServiceClient;
        this.avamServices = avamServices;
    }

    @StreamListener(target = Sink.INPUT, condition = "headers[event]=='AavSubmitted'")
    public void submit(AavEventDto dto) {
      AvamService<AavEventDto> validAvamImplementation = (AvamService<AavEventDto>) getValidAvamImplementation(dto, avamServices);
      validAvamImplementation.process(dto);
    }

    private AvamService<? extends EventDto> getValidAvamImplementation(EventDto dto, List<AvamService<? extends EventDto>> avamServices) {
        List<AvamService<? extends EventDto>> validImplementations = avamServices.stream()
                .filter(it -> it.canProcess(dto))
                .collect(Collectors.toList());

        //TODO: Throw proper exception
        if (validImplementations.size() != 1) {
            throw new IllegalStateException("no no no");
        }

        return validImplementations.get(0);
    }

}

