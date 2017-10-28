package jndi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import jndi.model.JndiParameters;

public class ConnectionPoolSTAXParser {
	private static List<JndiParameters> jndiParameterList = new ArrayList<JndiParameters>();

	public List<JndiParameters> getJndiParameterList() {
		return jndiParameterList;
	}

	// public static void main(String[] args) {
	// String fileName = "connection-pools.xml";
	// List<JndiParameters> empList = parseXML(fileName);
	// for (JndiParameters emp : empList) {
	// System.out.println(emp.toString());
	// }
	// }

	public static List<JndiParameters> parseXML(String fileName) {
		List<JndiParameters> empList = new ArrayList<JndiParameters>();
		JndiParameters emp = null;
		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		try {
			XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));
			while (xmlEventReader.hasNext()) {
				XMLEvent xmlEvent = xmlEventReader.nextEvent();
				if (xmlEvent.isStartElement()) {
					StartElement startElement = xmlEvent.asStartElement();
					if (startElement.getName().getLocalPart().equals("connection-pool")) {
						emp = new JndiParameters();
					} else if (startElement.getName().getLocalPart().equals("JndiName")) {
						// xmlEvent = xmlEventReader.nextEvent();
						// emp.setJndiName(xmlEvent.asCharacters().getData());

						xmlEvent = xmlEventReader.nextEvent();
						if (xmlEvent.isEndElement()) {
							emp.setJndiName(null);
						} else
							emp.setJndiName(xmlEvent.asCharacters().getData());
					} else if (startElement.getName().getLocalPart().equals("DriverName")) {
						// xmlEvent = xmlEventReader.nextEvent();
						// emp.setJndiDriverName(xmlEvent.asCharacters().getData());

						xmlEvent = xmlEventReader.nextEvent();
						if (xmlEvent.isEndElement()) {
							emp.setJndiDriverName(null);
						} else
							emp.setJndiDriverName(xmlEvent.asCharacters().getData());
					} else if (startElement.getName().getLocalPart().equals("DBUrl")) {
						// xmlEvent = xmlEventReader.nextEvent();
						// emp.setJndiDBUrl(xmlEvent.asCharacters().getData());

						xmlEvent = xmlEventReader.nextEvent();
						if (xmlEvent.isEndElement()) {
							emp.setJndiDBUrl(null);
						} else
							emp.setJndiDBUrl(xmlEvent.asCharacters().getData());
					} else if (startElement.getName().getLocalPart().equals("DBUsername")) {
						// xmlEvent = xmlEventReader.nextEvent();
						// emp.setJndiDBUser(xmlEvent.asCharacters().getData());

						xmlEvent = xmlEventReader.nextEvent();
						if (xmlEvent.isEndElement()) {
							emp.setJndiDBUser(null);
						} else
							emp.setJndiDBUser(xmlEvent.asCharacters().getData());
					} else if (startElement.getName().getLocalPart().equals("DBPassword")) {
						// xmlEvent = xmlEventReader.nextEvent();
						// emp.setJndiDBPass(xmlEvent.asCharacters().getData());

						xmlEvent = xmlEventReader.nextEvent();
						if (xmlEvent.isEndElement()) {
							emp.setJndiDBPass(null);
						} else
							emp.setJndiDBPass(xmlEvent.asCharacters().getData());
					}
				}
				if (xmlEvent.isEndElement()) {
					EndElement endElement = xmlEvent.asEndElement();
					if (endElement.getName().getLocalPart().equals("connection-pool")) {
						empList.add(emp);
					}
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		return empList;
	}
}
