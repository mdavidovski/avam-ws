package ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam;

import ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav.AavAvamService;
import ch.admin.seco.jobs.services.avamws.infrastructure.ws.avam.DeliverOste;
import ch.admin.seco.jobs.services.avamws.infrastructure.ws.avam.DeliverOsteResponse;
import com.sun.xml.bind.marshaller.CharacterEscapeHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.SoapMessageFactory;
import org.springframework.ws.soap.axiom.AxiomSoapMessageFactory;

import java.util.HashMap;

import static org.apache.commons.text.StringEscapeUtils.escapeXml10;
import static org.apache.commons.text.StringEscapeUtils.unescapeXml;

@Configuration
@EnableConfigurationProperties(AvamProperties.class)
public class AvamWebServiceClientConfig {

    private final AvamProperties avamProperties;

    public AvamWebServiceClientConfig(AvamProperties avamProperties) {
        this.avamProperties = avamProperties;
    }

    @Bean
    public WebServiceTemplate webServiceTemplate() {
        final WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        webServiceTemplate.setDefaultUri(avamProperties.getEndPointUrl());
        webServiceTemplate.setMarshaller(marshaller());
        webServiceTemplate.setUnmarshaller(marshaller());
        webServiceTemplate.setMessageFactory(messageFactory());

        return webServiceTemplate;
    }

    @Bean
    public SoapMessageFactory messageFactory() {
        AxiomSoapMessageFactory messageFactory = new AxiomSoapMessageFactory();
        //We must set payload caching to false otherwise the the marshaller does not call our CharacterEscapeHandler implementation.
        //As side effect, if we set the logging.level.org.springframework.ws.client.MessageTracing.received
        //to DEBUG or TRACE the WebServiceTemplate#hasFault method call throws an exception as it tries to read the payload for the
        //second time.
        messageFactory.setPayloadCaching(false);

        return messageFactory;
    }

    //TODO: Remove
    @Bean
    public AvamWebServiceClient avamServiceOld() {
        return new AvamWebServiceClient(
                webServiceTemplate(),
                avamProperties.getUsername(),
                avamProperties.getPassword());
    }

    //TODO: Extract for interface and not implementation
    @Bean
    public AavAvamService avamService() {
        return new AavAvamService(
                webServiceTemplate(),
                avamProperties.getUsername(),
                avamProperties.getPassword());
    }

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(DeliverOste.class, DeliverOsteResponse.class);

        HashMap<String, Object> marshallerProperties = new HashMap<>();
        marshallerProperties.put(CharacterEscapeHandler.class.getName(), (CharacterEscapeHandler) (ch, start, length, isAttVal, out)
                -> out.write(unescapeXml(escapeXml10(new String(ch, start, length)))));
        marshaller.setMarshallerProperties(marshallerProperties);

        return marshaller;
    }
}
