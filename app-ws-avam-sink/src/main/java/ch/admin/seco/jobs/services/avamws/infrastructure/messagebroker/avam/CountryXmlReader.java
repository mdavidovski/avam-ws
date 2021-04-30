package ch.admin.seco.jobs.services.avamws.infrastructure.messagebroker.avam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CountryXmlReader {

	private static final Logger LOGGER = LoggerFactory.getLogger(CountryXmlReader.class);

	private static final String FILENAME = "/Users/mdavidovski/Workspace/PRODYNA/seco/job-room/avam-ws/app-ws-avam-sink/src/main/resources/schema/eCH0072_210308.xml";

	public static void getCountryByIsoCode(String countryIsoCode) {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(FILENAME);

			NodeList countries = doc.getElementsByTagName("country");

			Stream<Node> nodeStream = IntStream.range(0, countries.getLength())
					.mapToObj(countries::item);

			Node x = nodeStream.collect(Collectors.toList()).get(0);

			System.out.println("lets see");

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

	}
}
