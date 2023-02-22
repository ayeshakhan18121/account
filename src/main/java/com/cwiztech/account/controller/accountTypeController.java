package com.cwiztech.account.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cwiztech.datalogs.model.APIRequestDataLog;
import com.cwiztech.datalogs.model.DatabaseTables;
import com.cwiztech.datalogs.model.tableDataLogs;
import com.cwiztech.datalogs.repository.apiRequestDataLogRepository;
import com.cwiztech.datalogs.repository.databaseTablesRepository;
import com.cwiztech.datalogs.repository.tableDataLogRepository;
import com.cwiztech.account.model.AccountType;
import com.cwiztech.account.repository.accountTypeRepository;
import com.cwiztech.token.AccessToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
@RequestMapping("/accounttype")

public class accountTypeController {
	
	private static final Logger log = LoggerFactory.getLogger(accountTypeController.class);

	@Autowired
	private accountTypeRepository accounttyperepository;
	
	@Autowired
	private apiRequestDataLogRepository apirequestdatalogRepository;
	
	@Autowired
	private tableDataLogRepository tbldatalogrepository;

	@Autowired
	private databaseTablesRepository databasetablesrepository;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity get(@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/accounttype", null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		List<AccountType> assessmentactivities = accounttyperepository.findActive();
		return new ResponseEntity(getAPIResponse(assessmentactivities, null , null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity getAll(@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/accounttype/all", null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		List<AccountType> assessmentactivities = accounttyperepository.findAll();
		
		return new ResponseEntity(getAPIResponse(assessmentactivities, null , null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity getOne(@PathVariable Long id, @RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/accounttype/"+id, null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		AccountType accounttype = accounttyperepository.findOne(id);
		
		return new ResponseEntity(getAPIResponse(null, accounttype , null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/ids", method = RequestMethod.POST)
	public ResponseEntity getByIDs(@RequestBody String data, @RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("POST", "/accounttype/ids", data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		List<Integer> accounttype_IDS = new ArrayList<Integer>(); 
		JSONObject jsonObj = new JSONObject(data);
		JSONArray jsonassessmentactivities = jsonObj.getJSONArray("assessmentactivities");
		for (int i=0; i<jsonassessmentactivities.length(); i++) {
			accounttype_IDS.add((Integer) jsonassessmentactivities.get(i));
		}
		List<AccountType> assessmentactivities = new ArrayList<AccountType>();
		if (jsonassessmentactivities.length()>0)
			assessmentactivities = accounttyperepository.findByIDs(accounttype_IDS);
		
		return new ResponseEntity(getAPIResponse(assessmentactivities, null , null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/notin/ids", method = RequestMethod.POST)
	public ResponseEntity getByNotInIDs(@RequestBody String data, @RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("POST", "/accounttype/notin/ids", data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		List<Integer> accounttype_IDS = new ArrayList<Integer>(); 
		JSONObject jsonObj = new JSONObject(data);
		JSONArray jsonassessmentactivities = jsonObj.getJSONArray("assessmentactivities");
		for (int i=0; i<jsonassessmentactivities.length(); i++) {
			accounttype_IDS.add((Integer) jsonassessmentactivities.get(i));
		}
		List<AccountType> assessmentactivities = new ArrayList<AccountType>();
		if (jsonassessmentactivities.length()>0)
			assessmentactivities = accounttyperepository.findByNotInIDs(accounttype_IDS);
		
		return new ResponseEntity(getAPIResponse(assessmentactivities, null , null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity insert(@RequestBody String data,@RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("POST", "/accounttype", data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		
		return insertupdateAll(null, new JSONObject(data), apiRequest);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity update(@PathVariable Long id, @RequestBody String data, @RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		
		APIRequestDataLog apiRequest = checkToken("PUT", "/accounttype/"+id, data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		JSONObject jsonObj = new JSONObject(data);
		jsonObj.put("accounttype_ID", id);
		
		return insertupdateAll(null, jsonObj, apiRequest);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity insertupdate(@RequestBody String data, @RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("PUT", "/accounttype", data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		
		return insertupdateAll(new JSONArray(data), null, apiRequest);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity insertupdateAll(JSONArray jsonAssessmentactivities, JSONObject jsonAccountType, APIRequestDataLog apiRequest) throws JsonProcessingException, JSONException, ParseException {
	    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();

		List<AccountType> assessmentactivities = new ArrayList<AccountType>();
		if (jsonAccountType != null) {
			jsonAssessmentactivities = new JSONArray();
			jsonAssessmentactivities.put(jsonAccountType);
		}
		log.info(jsonAssessmentactivities.toString());
		
		for (int a=0; a<jsonAssessmentactivities.length(); a++) {
			JSONObject jsonObj = jsonAssessmentactivities.getJSONObject(a);
			AccountType accounttype = new AccountType();
			long accounttypeid = 0;

			if (jsonObj.has("accounttype_ID")) {
				accounttypeid = jsonObj.getLong("accounttype_ID");
				if (accounttypeid != 0) {
					accounttype = accounttyperepository.findOne(accounttypeid);
					
					if (accounttype == null)
						return new ResponseEntity(getAPIResponse(null, null , null, null, "Invalid AccountType Data!", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
				}
			}
			
			if (accounttypeid == 0) {
				if (!jsonObj.has("accounttype_NAME") || jsonObj.isNull("accounttype_NAME"))
					return new ResponseEntity(getAPIResponse(null, null , null, null, "accounttype NAME is missing", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
				
				if (!jsonObj.has("accounttype_CODE") || jsonObj.isNull("accounttype_CODE"))
					return new ResponseEntity(getAPIResponse(null, null , null, null, "accounttype CODE is missing", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
					
			}
			
			if (jsonObj.has("accounttype_NAME") && !jsonObj.isNull("accounttype_NAME"))
			accounttype.setACCOUNTTYPE_NAME(jsonObj.getString("accounttype_NAME"));

		    if (jsonObj.has("accounttype_CODE")  && !jsonObj.isNull("accounttype_CODE"))
			accounttype.setACCOUNTTYPE_CODE(jsonObj.getString("accounttype_CODE"));

		    if (jsonObj.has("accounttype_DESC") && !jsonObj.isNull("accounttype_DESC"))
			accounttype.setACCOUNTTYPE_DESCRIPTION(jsonObj.getString("accounttype_DESC"));
			
		    if (accounttypeid == 0)
				accounttype.setISACTIVE("Y");
		    else if (jsonObj.has("isactive"))
				accounttype.setISACTIVE(jsonObj.getString("isactive"));

			accounttype.setMODIFIED_BY(apiRequest.getREQUEST_ID());
			accounttype.setMODIFIED_WORKSTATION(apiRequest.getLOG_WORKSTATION());
			accounttype.setMODIFIED_WHEN(dateFormat1.format(date));
			assessmentactivities.add(accounttype);
		}
		
		for (int a=0; a<assessmentactivities.size(); a++) {
			AccountType accounttype = assessmentactivities.get(a);
			accounttype = accounttyperepository.saveAndFlush(accounttype);
			assessmentactivities.get(a).setACCOUNTTYPE_ID(accounttype.getACCOUNTTYPE_ID());
		}
		
		List<Integer> accounttype_IDS = new ArrayList<Integer>(); 
		for (int a=0; a<assessmentactivities.size(); a++) {
			accounttype_IDS.add((int) assessmentactivities.get(a).getACCOUNTTYPE_ID());
		}
		assessmentactivities = accounttyperepository.findByIDs(accounttype_IDS);
		
		ResponseEntity responseentity;
		if (jsonAccountType != null)
			responseentity = new ResponseEntity(getAPIResponse(null, assessmentactivities.get(0) , null, null, null, apiRequest, true).getREQUEST_OUTPUT(), HttpStatus.OK);
		else
			responseentity = new ResponseEntity(getAPIResponse(assessmentactivities, null , null, null, null, apiRequest, true).getREQUEST_OUTPUT(), HttpStatus.OK);
		return responseentity;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity delete(@PathVariable Long id,@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/accounttype/"+id, null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		AccountType accounttype = accounttyperepository.findOne(id);
		accounttyperepository.delete(accounttype);
		
		return new ResponseEntity(getAPIResponse(null, accounttype , null, null, null, apiRequest, true).getREQUEST_OUTPUT(), HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public ResponseEntity remove(@PathVariable Long id,@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/accounttype/"+id, null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		
		JSONObject accounttype = new JSONObject();
		accounttype.put("accounttype_ID", id);
		accounttype.put("isactive", "N");
		
		return insertupdateAll(null, accounttype, apiRequest);
	}

	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseEntity getBySearch(@RequestBody String data, @RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		return BySearch(data, true, headToken);
	}

	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/search/all", method = RequestMethod.POST)
	public ResponseEntity getAllBySearch(@RequestBody String data, @RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		return BySearch(data, false, headToken);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity BySearch(String data, boolean active, String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("POST", "/accounttype/search" + ((active == true) ? "" : "/all"), data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		JSONObject jsonObj = new JSONObject(data);

		List<AccountType> assessmentactivities = ((active == true)
				? accounttyperepository.findBySearch("%" + jsonObj.getString("search") + "%")
				: accounttyperepository.findAllBySearch("%" + jsonObj.getString("search") + "%"));
		
		return new ResponseEntity(getAPIResponse(assessmentactivities, null , null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/advancedsearch", method = RequestMethod.POST)
	public ResponseEntity getByAdvancedSearch(@RequestBody String data, @RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		return ByAdvancedSearch(data, true, headToken);
	}

	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/advancedsearch/all", method = RequestMethod.POST)
	public ResponseEntity getAllByAdvancedSearch(@RequestBody String data, @RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		return ByAdvancedSearch(data, false, headToken);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity ByAdvancedSearch(String data, boolean active, String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("POST", "/accounttype/advancedsearch" + ((active == true) ? "" : "/all"), data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		
		JSONObject jsonObj = new JSONObject(data);
		long accounttype_ID = 0;
	
		if (jsonObj.has("accounttype_ID"))
			accounttype_ID = jsonObj.getLong("accounttype_ID");
		
		List<AccountType>  assessmentactivities = ((active == true)
				? accounttyperepository.findByAdvancedSearch(accounttype_ID)
				: accounttyperepository.findAllByAdvancedSearch(accounttype_ID));
		
		return new ResponseEntity(getAPIResponse(assessmentactivities, null , null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}

	public APIRequestDataLog checkToken(String requestType, String requestURI, String requestBody, String workstation, String accessToken) throws JsonProcessingException {
		JSONObject checkTokenResponse = AccessToken.checkToken(accessToken);
		DatabaseTables databaseTableID = databasetablesrepository.findOne(AccountType.getDatabaseTableID());
		APIRequestDataLog apiRequest;
		
		log.info(requestType + ": " + requestURI);
		if (requestBody != null)
			log.info("Input: " + requestBody);

		if (checkTokenResponse.has("error")) {
			apiRequest = tableDataLogs.apiRequestDataLog(requestType, databaseTableID, (long) 0, requestURI, requestBody, workstation);
			apiRequest = tableDataLogs.errorDataLog(apiRequest, "invalid_token", "Token was not recognised");
			apirequestdatalogRepository.saveAndFlush(apiRequest);
			return apiRequest;
		}
		
		Long requestUser = (long) 0;
		if (accessToken != null && accessToken != "")
			requestUser = checkTokenResponse.getLong("user_ID");
		apiRequest = tableDataLogs.apiRequestDataLog(requestType, databaseTableID, requestUser, requestURI, requestBody, workstation);
		apiRequest.setREQUEST_OUTPUT(accessToken);
		return apiRequest;
	}
	
	APIRequestDataLog getAPIResponse(List<AccountType> assessmentactivities, AccountType accounttype , JSONArray jsonAssessmentactivities, JSONObject jsonAccountType, String message, APIRequestDataLog apiRequest, boolean isTableLog) throws JSONException, JsonProcessingException, ParseException {
		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		long accounttypeID = 0;
		
		if (message != null) {
			apiRequest = tableDataLogs.errorDataLog(apiRequest, "AccountType", message);
			apirequestdatalogRepository.saveAndFlush(apiRequest);
		} else {
			if (accounttype != null) {
				apiRequest.setREQUEST_OUTPUT(mapper.writeValueAsString(accounttype));
				accounttypeID = accounttype.getACCOUNTTYPE_ID();
			} else if(assessmentactivities != null){
					apiRequest.setREQUEST_OUTPUT(mapper.writeValueAsString(assessmentactivities));
			}else if (jsonAssessmentactivities != null){
				apiRequest.setREQUEST_OUTPUT(jsonAssessmentactivities.toString());
			} else if (jsonAccountType != null){
				apiRequest.setREQUEST_OUTPUT(jsonAccountType.toString());
			}
			apiRequest.setRESPONSE_DATETIME(dateFormat1.format(date));
			apiRequest.setREQUEST_STATUS("Success");
			apirequestdatalogRepository.saveAndFlush(apiRequest);
		}
		
		if (isTableLog)
			tbldatalogrepository.saveAndFlush(tableDataLogs.TableSaveDataLog(accounttypeID, apiRequest.getDATABASETABLE_ID(), apiRequest.getREQUEST_ID(), apiRequest.getREQUEST_OUTPUT()));
		
		if (apiRequest.getREQUEST_OUTPUT().contains("bearer"))
			apiRequest.setREQUEST_OUTPUT(null);
		
		log.info("Output: " + apiRequest.getREQUEST_OUTPUT());
		log.info("--------------------------------------------------------");

		return apiRequest;
	}
}