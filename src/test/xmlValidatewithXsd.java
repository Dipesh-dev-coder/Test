package test;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class xmlValidatewithXsd {


	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException 
	{
		xmlValidatewithXsd  validatewithXsd = new xmlValidatewithXsd();
		String payLoad = "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4NCjxUUkFERV9YTUw+DQo8L1RSQURFX1hNTD4";
		byte[] decodedr = Base64.getDecoder().decode(payLoad);
		InputStream xmlIs = new ByteArrayInputStream(decodedr);
		
		InputStream xsdinputStream = validatewithXsd.getXsdInputStream("D:\\trade document\\trade_xml_xsd_Pelican_29_NOV_23.xsd");
		boolean xml = validatewithXsd.validateXml(xmlIs, xsdinputStream);
		System.out.println(xml);
		if(xml)
			System.out.println("xml validate");
		else
			System.out.println("Invalid xml");
	}
	public static boolean validateXml(InputStream xml, InputStream xsd){
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			Document doc = dbf.newDocumentBuilder().parse(xml);
			validateXml(doc, xsd);
			return true;
		} 
		catch (Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
	}

	public static boolean validateXml(Node root, InputStream xsd) {
		try {
			Source source = new StreamSource(xsd);
			Schema schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(source);
			Validator validator = schema.newValidator();
			validator.validate(new DOMSource(root));
			return true;
		} catch (Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
	}
	
	public static InputStream getXsdInputStream(String path) throws FileNotFoundException {
		File xsdFile = new File(path);
		InputStream xsdInputStream = new java.io.FileInputStream(xsdFile);
		return xsdInputStream;
	}

}
