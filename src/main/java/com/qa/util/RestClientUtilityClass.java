package com.qa.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.data.Users;

public class RestClientUtilityClass {
		 public static int getStatus(CloseableHttpResponse closebaleHttpResponse) {
			 int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		     //System.out.println("Status Code--->"+ statusCode);
		     return statusCode;
		 }
		 public static String josonToString(CloseableHttpResponse closebaleHttpResponse) throws ParseException, IOException {
			 
			 String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
			 return responseString;
			 
		 }
		 public static JSONObject stringToJsonObject(String responseString) {
			   
			    JSONObject responseJson = new JSONObject(responseString);
				//System.out.println("Response JSON from API---> "+ responseJson);
				return responseJson;
		 }
		 public static  Header[] getHeader(CloseableHttpResponse closebaleHttpResponse) {
			 Header[] headersArray =  closebaleHttpResponse.getAllHeaders();
				HashMap<String, String> allHeaders = new HashMap<String, String>();	
				for(Header header : headersArray){
					allHeaders.put(header.getName(), header.getValue());
				}	
				System.out.println("Headers Array-->"+allHeaders);
				return headersArray;
		 }
		 public static HashMap getMapObject() {
			 HashMap<String, String> headerMap = new HashMap<String, String>();
				headerMap.put("Content-Type", "application/json");
				return headerMap;
			 
		 }
		 public static Users getUSerObject() {
			 Users users = new Users("morpheus", "leader");
			 return users;
		 }
		 public static ObjectMapper getMapperObject() {
			 ObjectMapper mapper = new ObjectMapper();
			 return mapper;
		 }
		 public static void ObjectToJson(ObjectMapper mapper) throws JsonGenerationException, JsonMappingException, IOException {
			 mapper.writeValue(new File("/src/main/java/com/qa/data/users.json"), RestClientUtilityClass.getUSerObject());
				
		 }
		 public static String jsonToObject(ObjectMapper mapper) throws JsonProcessingException {
			    String usersJsonString = mapper.writeValueAsString(RestClientUtilityClass.getUSerObject());
				System.out.println(usersJsonString);
				return usersJsonString;
		 }
}
