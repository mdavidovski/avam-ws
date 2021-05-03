package ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.aav;

import ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam.AvamWebServiceSinkApplication;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.test.client.MockWebServiceServer;
import org.springframework.xml.transform.ResourceSource;

import javax.xml.transform.Source;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.ws.test.client.RequestMatchers.payload;
import static org.springframework.ws.test.client.ResponseCreators.withPayload;

//FIXME
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AvamWebServiceSinkApplication.class)
public class AavAvamWebServiceClientIntTest {
    private MockWebServiceServer mockServer;

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    @Autowired
    private AavAvamWebServiceClient sut;

    @Autowired
    private ResourceLoader resourceLoader;

    @Before
    public void setUp() {
        this.mockServer = MockWebServiceServer.createServer(webServiceTemplate);
    }

    @Ignore
    @Test
    public void shouldEscapeNonXMLValues() throws Exception {
        Source responsePayload = new ResourceSource(resourceLoader.getResource("classpath:ok-response.xml"));
        Source expectedRequestPayload = new ResourceSource(resourceLoader.getResource("classpath:job-ad-request.xml"));

        mockServer.expect(payload(expectedRequestPayload)).andRespond(withPayload(responsePayload));

        AavEventDto dto = AavTestFactory.testAavEventDto();
        sut.canProcess(dto);
    }

    @Ignore
    @Test
    public void testCanProcess() {
        // Given
        AavEventDto dto = AavTestFactory.testAavEventDto();

        // When
        boolean canProcess = sut.canProcess(dto);

        //Then
        assertThat(canProcess).isTrue();
    }

}
