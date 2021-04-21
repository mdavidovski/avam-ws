package ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam;


import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class AvamWebServiceSink {

    private final AvamWebServiceClient avamWebServiceClient;

    public AvamWebServiceSink(AvamWebServiceClient avamWebServiceClient) {
        this.avamWebServiceClient = avamWebServiceClient;
    }

    @StreamListener(target = Sink.INPUT, condition = "headers[event]=='MY_FIRST_EVENT'")
    public void register() {
        // FIXME
    }

}
