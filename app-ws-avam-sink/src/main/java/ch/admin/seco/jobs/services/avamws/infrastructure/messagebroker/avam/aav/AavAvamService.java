package ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav;

import ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.AvamService;
import ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.EventDto;
import ch.admin.seco.jobs.services.avamws.infrastructure.ws.avam.WSCredentials;
import org.springframework.ws.client.core.WebServiceTemplate;

public class AavAvamService implements AvamService<AavEventDto> {

    private static final String AVAM_RESPONSE_OK = "NK_AVAM: OK";

    private final WebServiceTemplate webserviceTemplate;
    private final String username;
    private final String password;

    public AavAvamService(WebServiceTemplate webserviceTemplate, String username, String password) {
        this.webserviceTemplate = webserviceTemplate;
        this.username = username;
        this.password = password;
    }

    @Override
    public void process(AavEventDto a) {

    }

    @Override
    public boolean canProcess(EventDto whatever) {
        return whatever instanceof AavEventDto;
    }

    WSCredentials getCredentials() {
        WSCredentials credentials = new WSCredentials();
        credentials.setUsername(username);
        credentials.setPassword(password);
        return credentials;
    }


}
