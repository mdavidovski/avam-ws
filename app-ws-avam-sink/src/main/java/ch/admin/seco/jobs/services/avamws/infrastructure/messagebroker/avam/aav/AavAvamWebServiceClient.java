package ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav;

import ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.AvamException;
import ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.AvamWebServiceClient;
import ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.EventDto;
import ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.enums.AvamAction;
import ch.admin.seco.jobs.services.avamws.infrastructure.ws.avam.DeliverStes;
import ch.admin.seco.jobs.services.avamws.infrastructure.ws.avam.DeliverStesResponse;
import ch.admin.seco.jobs.services.avamws.infrastructure.ws.avam.TStesEgov;
import ch.admin.seco.jobs.services.avamws.infrastructure.ws.avam.WSCredentials;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.WebServiceTemplate;

public class AavAvamWebServiceClient implements AvamWebServiceClient<AavEventDto> {

    private static final Logger LOG = LoggerFactory.getLogger(AavAvamWebServiceClient.class);

    private static final String AVAM_RESPONSE_OK = "NK_AVAM: OK";

    private final AavToAvamAssembler assembler;
    private final WebServiceTemplate webserviceTemplate;
    private final String username;
    private final String password;

    public AavAvamWebServiceClient(WebServiceTemplate webserviceTemplate, String username, String password) {
        this.webserviceTemplate = webserviceTemplate;
        this.username = username;
        this.password = password;
        this.assembler = new AavToAvamAssembler();
    }

    @Override
    public void process(AavEventDto eventDto) {
        LOG.info("Start sending registration of jobAdvertisement id=" + eventDto.getId() + " to AVAM");
        LOG.debug(eventDto.toString());
        TStesEgov tStesEgov = assembler.toStesEgov(eventDto);
        send(eventDto.getId(), tStesEgov);
    }

    @Override
    public boolean canProcess(EventDto eventDto) {
        return eventDto instanceof AavEventDto;
    }

    private void send(String id, TStesEgov tStesEgov) {
        DeliverStes request = new DeliverStes();
        request.setCredentials(getCredentials());
        request.setStes(tStesEgov);
        DeliverStesResponse response = (DeliverStesResponse) webserviceTemplate.marshalSendAndReceive(request);
        handleResponse(id, response);
    }

    private WSCredentials getCredentials() {
        WSCredentials credentials = new WSCredentials();
        credentials.setUsername(username);
        credentials.setPassword(password);
        return credentials;
    }

    void handleResponse(String id, DeliverStesResponse response) {
        AvamAction action = AvamAction.AAV_SUBMIT;
        String returnCode = StringUtils.trim(response.getDeliverStesReturn());
        LOG.info("Action {} for AAV id={} receives AVAM response: {}", action.name(), id, StringUtils.left(returnCode, 150));
        if (!AVAM_RESPONSE_OK.equals(returnCode)) {
            throw new AvamException(id, action.name(), returnCode);
        }
    }

}
