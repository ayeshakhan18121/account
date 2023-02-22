//package com.cwiztech.common.controller;
//
//import java.text.ParseException;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import com.cwiztech.academicmanagement.model.AcademicsYear;
//import com.cwiztech.datalogs.model.APIRequestDataLog;
//import com.cwiztech.datalogs.model.DatabaseTables;
//import com.cwiztech.datalogs.model.tableDataLogs;
//import com.cwiztech.datalogs.repository.apiRequestDataLogRepository;
//import com.cwiztech.datalogs.repository.databaseTablesRepository;
//import com.cwiztech.login.model.LoginUser;
//import com.cwiztech.login.repository.loginUserRepository;
//import com.fasterxml.jackson.core.JsonProcessingException;
//
//@RestController
//@CrossOrigin
//@RequestMapping("/findaddress")
//public class postalAddressController {
//	private static final Logger log = LoggerFactory.getLogger(postalAddressController.class);
//
//	private  String findAddressUKAPIPath;
//	private  String findAddressUKAPIKey;
//	private  String findDetailAddressUKAPIPath;
//	
//	@Autowired
//	public postalAddressController(Environment env) {
//		this.findAddressUKAPIPath = env.getRequiredProperty("file_path.findAddressUKAPIPath");
//		this.findDetailAddressUKAPIPath = env.getRequiredProperty("file_path.findDetailAddressUKAPIPath");
//		this.findAddressUKAPIKey = env.getRequiredProperty("file_path.findAddressUKAPIKey");
//	}
//	
//	@Autowired
//	private loginUserRepository userrepository;
//
//	@Autowired
//	private apiRequestDataLogRepository apirequestdatalogRepository;
//
//	@Autowired
//	private databaseTablesRepository databaetablesrepository;
//
//	@RequestMapping(value = "/uk", method = RequestMethod.POST)
//	public String findUKAddress(@RequestBody String data)
//			throws JsonProcessingException, JSONException, ParseException {
//		LoginUser requestUser;
//
//		log.info("POST: /findaddress/uk");
//		log.info("Input: " + data);
//
//		JSONObject jsonObj = new JSONObject(data);
//		String rtnAddress, workstation = null;
//		RestTemplate restTemplate = new RestTemplate();
//
//		DatabaseTables databaseTableID = databaetablesrepository.findOne(AcademicsYear.getDatabaseTableID());
//		
//		if (jsonObj.has("user"))
//			requestUser = userrepository.getUser(jsonObj.getString("user"));
//		else
//			requestUser = userrepository.findOne((long) 0);
//		if (jsonObj.has("workstation"))
//			workstation = jsonObj.getString("workstation");
//
//		jsonObj.put("key", findAddressUKAPIKey);
//		jsonObj.put("country", "GBR");
//
//		APIRequestDataLog apiRequest = tableDataLogs.apiRequestDataLog("POST", databaseTableID, 0, "/findaddress/uk", data, requestUser, workstation);
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//
//		HttpEntity<String> entity = new HttpEntity<String>(jsonObj.toString(),headers);
//		rtnAddress = restTemplate.postForObject(findAddressUKAPIPath, entity, String.class);
//
//		apiRequest.setREQUEST_OUTPUT(rtnAddress);
//		apiRequest.setREQUEST_STATUS("Success");
//		apirequestdatalogRepository.saveAndFlush(apiRequest);
//
//		log.info("Output: " + rtnAddress);
//		log.info("--------------------------------------------------------");
//
//		return rtnAddress;
//	}
//
//	@RequestMapping(value = "/uk/detail", method = RequestMethod.POST)
//	public String findUKAddressDetail(@RequestBody String data)
//			throws JsonProcessingException, JSONException, ParseException {
//		LoginUser requestUser;
//
//		log.info("POST: /findaddress/uk/detail");
//		log.info("Input: " + data);
//
//		JSONObject jsonObj = new JSONObject(data);
//		String rtnAddress, workstation = null;
//		RestTemplate restTemplate = new RestTemplate();
//
//		DatabaseTables databaseTableID = databaetablesrepository.findOne(AcademicsYear.getDatabaseTableID());
//		
//		if (jsonObj.has("user"))
//			requestUser = userrepository.getUser(jsonObj.getString("user"));
//		else
//			requestUser = userrepository.findOne((long) 0);
//		if (jsonObj.has("workstation"))
//			workstation = jsonObj.getString("workstation");
//
//		jsonObj.put("key", findAddressUKAPIKey);
//		jsonObj.put("country", "GBR");
//
//		APIRequestDataLog apiRequest = tableDataLogs.apiRequestDataLog("POST", databaseTableID, 0, "/findaddress/uk/detail", data, requestUser, workstation);
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//
//		HttpEntity<String> entity = new HttpEntity<String>(jsonObj.toString(),headers);
//		rtnAddress = restTemplate.postForObject(findDetailAddressUKAPIPath, entity, String.class);
//
//		apiRequest.setREQUEST_OUTPUT(rtnAddress);
//		apiRequest.setREQUEST_STATUS("Success");
//		apirequestdatalogRepository.saveAndFlush(apiRequest);
//
//		log.info("Output: " + rtnAddress);
//		log.info("--------------------------------------------------------");
//
//		return rtnAddress;
//	}
//
//};
