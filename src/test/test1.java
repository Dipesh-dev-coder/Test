package test;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class test1 {

	public static void main(String[] args) {

//		String json = "{\"beneficiaryAccountNumber\":\"\",\"billCollectionReferenceNo\":\"\",\"pelicanTradeRefNo\":\"TRD_20241009_0962\",\"beneficiaryBankcode\":\"\",\"documentsReceiptDate\":\"\",\"merchantTrade\":\"\",\"backOfficeRefNo\":\"5c8654f5-a914-451e-9cb6-ff6e287ba3b1\",\"highSeasTradeFlag\":\"\",\"totalAmount\":\"987998.98\",\"billAmount\":\"\",\"purposeCode\":\"\",\"location\":\"SURECOMP_UK\",\"currency\":\"USD\",\"billPresentationDate\":\"\",\"productType\":\"Advise\",\"direction\":\"\"}";
//
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonNode jsonNode = objectMapper.readTree(json);
//            String productType = jsonNode.get("productType").asText();
//            System.out.println("Product Type: " + productType);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
		ObjectMapper jsonMapper = new ObjectMapper();
		List<Map<String, Object>> fileDetailsList = new ArrayList<Map<String, Object>>();
		String jsonString = "{\r\n"
				+ "    \"id\": \"6193ce83-a106-4b82-abf3-55905c89acd4\",\r\n"
				+ "    \"owner\": {\r\n"
				+ "        \"id\": \"8a7cd088-f505-417b-8963-5da6a3f67bf5\",\r\n"
				+ "        \"name\": \"Default\",\r\n"
				+ "        \"ethereum_address\": \"0x0f0A2a82CBa6CC86cD493C4AbC6106dE70a01d46\",\r\n"
				+ "        \"company_id\": \"8eb52b2d-1eda-4339-b2be-58af4df70537\",\r\n"
				+ "        \"company_name\": \"PelicanSecure Trade\",\r\n"
				+ "        \"company_logo\": \"api/v3/logos/by-company-id/8eb52b2d-1eda-4339-b2be-58af4df70537/logo/\"\r\n"
				+ "    },\r\n"
				+ "    \"recipient\": {\r\n"
				+ "        \"id\": \"a96dbf80-795c-44ef-9fdb-1730f7568853\",\r\n"
				+ "        \"name\": \"Default\",\r\n"
				+ "        \"ethereum_address\": \"0xF8297b701de833D28222B04AA5F81Df2a5f7B619\",\r\n"
				+ "        \"company_id\": \"72f34d40-e02e-4f06-adca-7f9929d528f5\",\r\n"
				+ "        \"company_name\": \"Pelican exporter\",\r\n"
				+ "        \"company_logo\": \"api/v3/logos/by-company-id/72f34d40-e02e-4f06-adca-7f9929d528f5/logo/\"\r\n"
				+ "    },\r\n"
				+ "    \"type\": \"outgoing\",\r\n"
				+ "    \"specialization_type\": \"standard\",\r\n"
				+ "    \"message\": null,\r\n"
				+ "    \"created\": \"2024-09-30T07:20:20.054384Z\",\r\n"
				+ "    \"sent\": \"2024-09-30T07:30:17.948946Z\",\r\n"
				+ "    \"delivered\": \"2024-09-30T07:30:23.434836Z\",\r\n"
				+ "    \"archived\": null,\r\n"
				+ "    \"sealed\": true,\r\n"
				+ "    \"delivery_status\": \"delivered_successfully\",\r\n"
				+ "    \"can_be_archived\": true,\r\n"
				+ "    \"failed_delivery_envelope_id\": null,\r\n"
				+ "    \"pay_to_collect\": false,\r\n"
				+ "    \"pay_to_collect_response\": 0,\r\n"
				+ "    \"meta_data\": null,\r\n"
				+ "    \"attachments\": [\r\n"
				+ "        {\r\n"
				+ "            \"id\": \"08e57bd7-d172-4d94-950d-7d3a588838e4\",\r\n"
				+ "            \"document_token_id\": \"f6d5ed77-7f5b-4d75-9f02-375a749585d7\",\r\n"
				+ "            \"document_id\": \"02656670-6e7f-48a2-a25e-a5a36bab69a9\",\r\n"
				+ "            \"document_name\": \"NOTIFICATION.pdf\",\r\n"
				+ "            \"document_type\": \"INV\"\r\n"
				+ "        },\r\n"
				+ "		{\r\n"
				+ "            \"id\": \"08e57bd7-d172-4d94-950d-7d3a588838e5\",\r\n"
				+ "            \"document_token_id\": \"f6d5ed77-7f5b-4d75-9f02-375a749585d7\",\r\n"
				+ "            \"document_id\": \"02656670-6e7f-48a2-a25e-a5a36bab69a9\",\r\n"
				+ "            \"document_name\": \"NOTIFICATION.pdf\",\r\n"
				+ "            \"document_type\": \"INV\"\r\n"
				+ "        }\r\n"
				+ "    ],\r\n"
				+ "    \"delegation\": null,\r\n"
				+ "    \"relay_data\": null\r\n"
				+ "}";
		try {
			JsonNode rootNode = jsonMapper.readValue(jsonString, JsonNode.class);
			JsonNode attachmentsNode = null;
			attachmentsNode = rootNode.findPath("attachments");
			
			if(attachmentsNode.isArray()) {
				for(JsonNode attachment : attachmentsNode) {
					Map<String, Object> fileDetailMap = new ConcurrentHashMap<String, Object>();
					fileDetailMap.put("FILE_REFERENCE", attachment.get("id").textValue());
					fileDetailMap.put("FILE_NAME", attachment.get("document_name").textValue());
					fileDetailMap.put("FILE_TYPE", attachment.get("document_type").textValue());
					fileDetailsList.add(fileDetailMap);			}
			}
			System.out.println("Got file details list as <"+ fileDetailsList+">");
		} catch (JsonMappingException e) {
			
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}		
    }
}

