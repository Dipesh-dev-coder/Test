package interview;

import org.w3c.dom.*;
import org.xml.sax.InputSource;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.*;
import java.util.HashMap;
import java.util.Set;

import javax.xml.xpath.*;

public class XMLModifier {
	public static void main(String[] args) {
		try {
			// XML string
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
					"          <ExtractionRuleID>722434</ExtractionRuleID>\n" +
					"          <Category>Group 1</Category>\n" +
					"        </DocumentLevelField>\n" +
					"      </DocumentLevelFields>\n" +
					"    </Document>\n" +
					"  </Documents>\n" +
					"</Batch>";

			String jsonString = "{\r\n"
					+ "  \"Data\": {\r\n"
					+ "    \"Messages\": [\r\n"
					+ "      {\r\n"
					+ "        \"ExtractedInfo\": {\r\n"
					+ "          \"FieldElements\": [\r\n"
					+ "            {\r\n"
					+ "              \"confidence_entity\": 1,\r\n"
					+ "              \"end\": 407,\r\n"
					+ "              \"entity\": \"BILL_OF_EXCHANGE_DATE\",\r\n"
					+ "              \"extractor\": \"SpaCyExtractor\",\r\n"
					+ "              \"start\": 406,\r\n"
					+ "              \"value\": \"07/04/2022\"\r\n"
					+ "            }\r\n"
					+ "          ]\r\n"
					+ "        },\r\n"
					+ "        \"MessageDescription\": \" \"\r\n"
					+ "      }\r\n"
					+ "    ]\r\n"
					+ "  }\r\n"
					+ "}";
			
			// Convert XML string to Document
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(xml)));
			
			String entity= "", value ="", updatedXml ="";
			HashMap<String, Object> entityValueReturn = new HashMap<String, Object>();
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readValue(jsonString, JsonNode.class);
			JsonNode messagesNode = rootNode.path("Data").path("Messages");
			if (messagesNode.isArray()) {
				for (JsonNode messageNode : messagesNode) {
					JsonNode extractedInfoNode = messageNode.path("ExtractedInfo");
					JsonNode fieldElementsNode = extractedInfoNode.path("FieldElements");
					if (fieldElementsNode.isArray()) {
						for (JsonNode fieldElementNode : fieldElementsNode) {
							entity = fieldElementNode.path("entity").asText();
							value = fieldElementNode.path("value").asText();
							entityValueReturn.put(entity, value);
						}
						Set<String> keysSet = entityValueReturn.keySet();
						for (String key : keysSet) {

							String newValue =entityValueReturn.get(key).toString();
							// Create XPath expression to select Name and Value tags
							XPathFactory xPathfactory = XPathFactory.newInstance();
							XPath xpath = xPathfactory.newXPath();
							XPathExpression nameExpr = xpath.compile("//DocumentLevelField/Name");
							XPathExpression valueExpr = xpath.compile("//DocumentLevelField/Value");

							// Evaluate XPath expression to get NodeList for Name and Value tags
							NodeList nameNodeList = (NodeList) nameExpr.evaluate(doc, XPathConstants.NODESET);
							NodeList valueNodeList = (NodeList) valueExpr.evaluate(doc, XPathConstants.NODESET);

							// Replace the value of the Value tag
							for (int i = 0; i < valueNodeList.getLength(); i++) {
								Node valueNode = valueNodeList.item(i);
								if (valueNode.getNodeType() == Node.ELEMENT_NODE) {
									Element valueElement = (Element) valueNode;
									String name = nameNodeList.item(i).getTextContent();
									System.out.println("name <"+name+">");
									System.out.println("Key <"+key+">");
									if (key.equals(name)) {
										if(!newValue.isEmpty()) {
											valueElement.setTextContent(newValue);

											NodeList fields = doc.getElementsByTagName("DocumentLevelField");
											for (int j = 0; j < fields.getLength(); j++) {
												Element field = (Element) fields.item(j);
												NodeList names = field.getElementsByTagName("Name");
												if (names.getLength() > 0) {
													String str = names.item(0).getTextContent();
													if (key.equals(str)) {
														NodeList extractionNames = field.getElementsByTagName("ExtractionName");
														if (extractionNames.getLength() > 0) {
															Node extractionName = extractionNames.item(0);
															extractionName.setTextContent("SpaCyExtractor");
														}
														
														NodeList ExtractionRuleIDs = field.getElementsByTagName("ExtractionRuleID");
														if (ExtractionRuleIDs.getLength() > 0) {
															Node ExtractionRuleID = ExtractionRuleIDs.item(0);
															ExtractionRuleID.setTextContent("");
														}
														
														NodeList Confidences = field.getElementsByTagName("Confidence");
														if (Confidences.getLength() > 0) {
															Node Confidence = Confidences.item(0);
															Confidence.setTextContent("100.0");
														}
														
														NodeList OcrConfidences = field.getElementsByTagName("OcrConfidence");
														if (OcrConfidences.getLength() > 0) {
															Node OcrConfidence = OcrConfidences.item(0);
															OcrConfidence.setTextContent("100.0");
														}

														// Remove the contents of CoordinatesList
														NodeList coordinatesLists = field.getElementsByTagName("CoordinatesList");
														for (int k = 0; k < coordinatesLists.getLength(); k++) {
															Node coordinatesList = coordinatesLists.item(k);
															// Remove all child nodes of CoordinatesList
															while (coordinatesList.hasChildNodes()) {
																coordinatesList.removeChild(coordinatesList.getFirstChild());
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				// Convert Document to XML string
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				StringWriter writer = new StringWriter();
				transformer.transform(new DOMSource(doc), new StreamResult(writer));
				updatedXml = writer.toString();
				//System.out.println("Updated XML:\n" + updatedXml);
				System.out.println("Updated XML:\n" + updatedXml);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
