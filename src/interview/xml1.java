package interview;

import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class xml1 {
    public static void main(String[] args) {
    	String xml = "<Batch>\n" +
				"  <BatchInstanceIdentifier>BI6AB3</BatchInstanceIdentifier>\n" +
				"  <BatchClassIdentifier>BC8</BatchClassIdentifier>\n" +
				"  <Documents>\n" +
				"    <Document>\n" +
				"      <Identifier>DOC6</Identifier>\n" +
				"      <Type>Bill_of_Exchange</Type>\n" +
				"      <DocumentLevelFields>\n" +
				"        <DocumentLevelField>\n" +
				"          <Name>BILL_OF_EXCHANGE_DATE</Name>\n" +
				"          <Value>07.03.2022</Value>\n" +
				"          <Type>STRING</Type>\n" +
				"          <ExtractionType>system</ExtractionType>\n" +
				"          <Confidence>100.0</Confidence>\n" +
				"          <OcrConfidenceThreshold>90.0</OcrConfidenceThreshold>\n" +
				"          <OcrConfidence>98.0</OcrConfidence>\n" +
				"          <CoordinatesList>\n" +
				"            <Coordinates>\n" +
				"              <Value>07.03.2022</Value>\n" +
				"              <x0>1881</x0>\n" +
				"              <y0>776</y0>\n" +
				"              <x1>2100</x1>\n" +
				"              <y1>807</y1>\n" +
				"            </Coordinates>\n" +
				"          </CoordinatesList>\n" +
				"          <Page>PG8</Page>\n" +
				"          <FieldOrderNumber>1</FieldOrderNumber>\n" +
				"          <FieldValueChangeScript>false</FieldValueChangeScript>\n" +
				"          <ExtractionName>Key Value Extraction</ExtractionName>\n" +
				"          <ExtractionRuleID>722434</ExtractionRuleID>\n" +
				"          <Category>Group 1</Category>\n" +
				"        </DocumentLevelField>\n" +
				"        <DocumentLevelField>\n" +
				"          <Name>BILL_OF_EXCHANGE_NUMBER</Name>\n" +
				"          <Value />\n" +
				"          <Type>STRING</Type>\n" +
				"          <ExtractionType>system</ExtractionType>\n" +
				"          <Confidence>0.0</Confidence>\n" +
				"          <OcrConfidenceThreshold>0.0</OcrConfidenceThreshold>\n" +
				"          <OcrConfidence>0.0</OcrConfidence>\n" +
				"          <FieldOrderNumber>2</FieldOrderNumber>\n" +
				"          <ForceReview>false</ForceReview>\n" +
				"          <FieldValueChangeScript>false</FieldValueChangeScript>\n" +
				"          <ExtractionName>Recostar Extraction</ExtractionName>\n" +
				"          <Category>Group 1</Category>\n" +
				"        </DocumentLevelField>\n" +
				"      </DocumentLevelFields>\n" +
				"    </Document>\n" +
				"  </Documents>\n" +
				"</Batch>";

        try {
            // Parse the XML string
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xml)));
			

            // Update the ExtractionName for DRAWER
            NodeList fields = doc.getElementsByTagName("DocumentLevelField");
            for (int i = 0; i < fields.getLength(); i++) {
                Element field = (Element) fields.item(i);
                NodeList names = field.getElementsByTagName("Name");
                if (names.getLength() > 0) {
                    String name = names.item(0).getTextContent();
                    if ("BILL_OF_EXCHANGE_DATE".equals(name)) {
                        NodeList extractionNames = field.getElementsByTagName("ExtractionName");
                        if (extractionNames.getLength() > 0) {
                            Node extractionName = extractionNames.item(0);
                            extractionName.setTextContent("SpaCyExtractor");
                        }

                        // Remove the contents of CoordinatesList
                        NodeList coordinatesLists = field.getElementsByTagName("CoordinatesList");
                        for (int j = 0; j < coordinatesLists.getLength(); j++) {
                            Node coordinatesList = coordinatesLists.item(j);
                            // Remove all child nodes of CoordinatesList
                            while (coordinatesList.hasChildNodes()) {
                                coordinatesList.removeChild(coordinatesList.getFirstChild());
                            }
                        }
                    }
                }
            }

            // Convert the modified XML back to a string
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            String modifiedXml = writer.toString();

            System.out.println("Modified XML:");
            System.out.println(modifiedXml);

        } catch (ParserConfigurationException | IOException | org.xml.sax.SAXException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}

