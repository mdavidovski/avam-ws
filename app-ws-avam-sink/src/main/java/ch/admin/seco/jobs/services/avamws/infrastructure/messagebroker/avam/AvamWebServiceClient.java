package ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.WebServiceTemplate;

//TODO: Add assembler/s
public class AvamWebServiceClient {

    private static final Logger LOG = LoggerFactory.getLogger(AvamWebServiceClient.class);

    private static final String AVAM_RESPONSE_OK = "NK_AVAM: OK";

    private final WebServiceTemplate webserviceTemplate;
    private final String username;
    private final String password;

    public AvamWebServiceClient(WebServiceTemplate webserviceTemplate, String username, String password) {
        this.webserviceTemplate = webserviceTemplate;
        this.username = username;
        this.password = password;
    }

}
