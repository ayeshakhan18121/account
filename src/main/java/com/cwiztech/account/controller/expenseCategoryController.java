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
import com.cwiztech.account.model.ExpenseCategory;
import com.cwiztech.account.repository.expenseCategoryRepository;
import com.cwiztech.token.AccessToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
@RequestMapping("/expenseaategory")

public class expenseCategoryController {
	
	private static final Logger log = LoggerFactory.getLogger(expenseCategoryController.class);

	@Autowired
	private expenseCategoryRepository expenseaategoryrepository;
	
	@Autowired
	private apiRequestDataLogRepository apirequestdatalogRepository;
	
	@Autowired
	private tableDataLogRepository tbldatalogrepository;

	@Autowired
	private databaseTablesRepository databasetablesrepository;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity get(@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/expenseaategory", null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		List<ExpenseCategory> assessmentactivities = expenseaategoryrepository.findActive();
		return new ResponseEntity(getAPIResponse(assessmentactivities, null , null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity getAll(@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/expenseaategory/all", null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		List<ExpenseCategory> assessmentactivities = expenseaategoryrepository.findAll();
		
		return new ResponseEntity(getAPIResponse(assessmentactivities, null , null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity getOne(@PathVariable Long id, @RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/expenseaategory/"+id, null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		ExpenseCategory expenseaategory = expenseaategoryrepository.findOne(id);
		
		return new ResponseEntity(getAPIResponse(null, expenseaategory , null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/ids", method = RequestMethod.POST)
	public ResponseEntity getByIDs(@RequestBody String data, @RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("POST", "/expenseaategory/ids", data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		List<Integer> expenseaategory_IDS = new ArrayList<Integer>(); 
		JSONObject jsonObj = new JSONObject(data);
		JSONArray jsonassessmentactivities = jsonObj.getJSONArray("assessmentactivities");
		for (int i=0; i<jsonassessmentactivities.length(); i++) {
			expenseaategory_IDS.add((Integer) jsonassessmentactivities.get(i));
		}
		List<ExpenseCategory> assessmentactivities = new ArrayList<ExpenseCategory>();
		if (jsonassessmentactivities.length()>0)
			assessmentactivities = expenseaategoryrepository.findByIDs(expenseaategory_IDS);
		
		return new ResponseEntity(getAPIResponse(assessmentactivities, null , null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/notin/ids", method = RequestMethod.POST)
	public ResponseEntity getByNotInIDs(@RequestBody String data, @RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("POST", "/expenseaategory/notin/ids", data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		List<Integer> expenseaategory_IDS = new ArrayList<Integer>(); 
		JSONObject jsonObj = new JSONObject(data);
		JSONArray jsonassessmentactivities = jsonObj.getJSONArray("assessmentactivities");
		for (int i=0; i<jsonassessmentactivities.length(); i++) {
			expenseaategory_IDS.add((Integer) jsonassessmentactivities.get(i));
		}
		List<ExpenseCategory> assessmentactivities = new ArrayList<ExpenseCategory>();
		if (jsonassessmentactivities.length()>0)
			assessmentactivities = expenseaategoryrepository.findByNotInIDs(expenseaategory_IDS);
		
		return new ResponseEntity(getAPIResponse(assessmentactivities, null , null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity insert(@RequestBody String data,@RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("POST", "/expenseaategory", data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		
		return insertupdateAll(null, new JSONObject(data), apiRequest);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity update(@PathVariable Long id, @RequestBody String data, @RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		
		APIRequestDataLog apiRequest = checkToken("PUT", "/expenseaategory/"+id, data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		JSONObject jsonObj = new JSONObject(data);
		jsonObj.put("expenseaategory_ID", id);
		
		return insertupdateAll(null, jsonObj, apiRequest);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity insertupdate(@RequestBody String data, @RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("PUT", "/expenseaategory", data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		
		return insertupdateAll(new JSONArray(data), null, apiRequest);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity insertupdateAll(JSONArray jsonAssessmentactivities, JSONObject jsonExpenseCategory, APIRequestDataLog apiRequest) throws JsonProcessingException, JSONException, ParseException {
	    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();

		List<ExpenseCategory> assessmentactivities = new ArrayList<ExpenseCategory>();
		if (jsonExpenseCategory != null) {
			jsonAssessmentactivities = new JSONArray();
			jsonAssessmentactivities.put(jsonExpenseCategory);
		}
		log.info(jsonAssessmentactivities.toString());
		
		for (int a=0; a<jsonAssessmentactivities.length(); a++) {
			JSONObject jsonObj = jsonAssessmentactivities.getJSONObject(a);
			ExpenseCategory expenseaategory = new ExpenseCategory();
			long expenseaategoryid = 0;

			if (jsonObj.has("expenseaategory_ID")) {
				expenseaategoryid = jsonObj.getLong("expenseaategory_ID");
				if (expenseaategoryid != 0) {
					expenseaategory = expenseaategoryrepository.findOne(expenseaategoryid);
					
					if (expenseaategory == null)
						return new ResponseEntity(getAPIResponse(null, null , null, null, "Invalid ExpenseCategory Data!", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
				}
			}
			
			if (expenseaategoryid == 0) {
				if (!jsonObj.has("expenseaategory_NAME") || jsonObj.isNull("expenseaategory_NAME"))
					return new ResponseEntity(getAPIResponse(null, null , null, null, "expenseaategory NAME is missing", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
				
				if (!jsonObj.has("expenseaategory_CODE") || jsonObj.isNull("expenseaategory_CODE"))
					return new ResponseEntity(getAPIResponse(null, null , null, null, "expenseaategory CODE is missing", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
					
			}
			
			if (jsonObj.has("expenseaategory_NAME") && !jsonObj.isNull("expenseaategory_NAME"))
			expenseaategory.setEXPENSECATEGORY_NAME(jsonObj.getString("expenseaategory_NAME"));

		    if (jsonObj.has("expenseaategory_CODE")  && !jsonObj.isNull("expenseaategory_CODE"))
			expenseaategory.setEXPENSECATEGORY_CODE(jsonObj.getString("expenseaategory_CODE"));

		    if (jsonObj.has("expenseaategory_DESC") && !jsonObj.isNull("expenseaategory_DESC"))
			expenseaategory.setEXPENSECATEGORY_DESCRIPTION(jsonObj.getString("expenseaategory_DESC"));
			
		    if (expenseaategoryid == 0)
				expenseaategory.setISACTIVE("Y");
		    else if (jsonObj.has("isactive"))
				expenseaategory.setISACTIVE(jsonObj.getString("isactive"));

			expenseaategory.setMODIFIED_BY(apiRequest.getREQUEST_ID());
			expenseaategory.setMODIFIED_WORKSTATION(apiRequest.getLOG_WORKSTATION());
			expenseaategory.setMODIFIED_WHEN(dateFormat1.format(date));
			assessmentactivities.add(expenseaategory);
		}
		
		for (int a=0; a<assessmentactivities.size(); a++) {
			ExpenseCategory expenseaategory = assessmentactivities.get(a);
			expenseaategory = expenseaategoryrepository.saveAndFlush(expenseaategory);
			assessmentactivities.get(a).setEXPENSECATEGORY_ID(expenseaategory.getEXPENSECATEGORY_ID());
		}
		
		List<Integer> expenseaategory_IDS = new ArrayList<Integer>(); 
		for (int a=0; a<assessmentactivities.size(); a++) {
			expenseaategory_IDS.add((int) assessmentactivities.get(a).getEXPENSECATEGORY_ID());
		}
		assessmentactivities = expenseaategoryrepository.findByIDs(expenseaategory_IDS);
		
		ResponseEntity responseentity;
		if (jsonExpenseCategory != null)
			responseentity = new ResponseEntity(getAPIResponse(null, assessmentactivities.get(0) , null, null, null, apiRequest, true).getREQUEST_OUTPUT(), HttpStatus.OK);
		else
			responseentity = new ResponseEntity(getAPIResponse(assessmentactivities, null , null, null, null, apiRequest, true).getREQUEST_OUTPUT(), HttpStatus.OK);
		return responseentity;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity delete(@PathVariable Long id,@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/expenseaategory/"+id, null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		ExpenseCategory expenseaategory = expenseaategoryrepository.findOne(id);
		expenseaategoryrepository.delete(expenseaategory);
		
		return new ResponseEntity(getAPIResponse(null, expenseaategory , null, null, null, apiRequest, true).getREQUEST_OUTPUT(), HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public ResponseEntity remove(@PathVariable Long id,@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/expenseaategory/"+id, null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		
		JSONObject expenseaategory = new JSONObject();
		expenseaategory.put("expenseaategory_ID", id);
		expenseaategory.put("isactive", "N");
		
		return insertupdateAll(null, expenseaategory, apiRequest);
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
		APIRequestDataLog apiRequest = checkToken("POST", "/expenseaategory/search" + ((active == true) ? "" : "/all"), data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		JSONObject jsonObj = new JSONObject(data);

		List<ExpenseCategory> assessmentactivities = ((active == true)
				? expenseaategoryrepository.findBySearch("%" + jsonObj.getString("search") + "%")
				: expenseaategoryrepository.findAllBySearch("%" + jsonObj.getString("search") + "%"));
		
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
		APIRequestDataLog apiRequest = checkToken("POST", "/expenseaategory/advancedsearch" + ((active == true) ? "" : "/all"), data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		
		JSONObject jsonObj = new JSONObject(data);
		long expenseaategory_ID = 0;
	
		if (jsonObj.has("expenseaategory_ID"))
			expenseaategory_ID = jsonObj.getLong("expenseaategory_ID");
		
		List<ExpenseCategory>  assessmentactivities = ((active == true)
				? expenseaategoryrepository.findByAdvancedSearch(expenseaategory_ID)
				: expenseaategoryrepository.findAllByAdvancedSearch(expenseaategory_ID));
		
		return new ResponseEntity(getAPIResponse(assessmentactivities, null , null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}

	public APIRequestDataLog checkToken(String requestType, String requestURI, String requestBody, String workstation, String accessToken) throws JsonProcessingException {
		JSONObject checkTokenResponse = AccessToken.checkToken(accessToken);
		DatabaseTables databaseTableID = databasetablesrepository.findOne(ExpenseCategory.getDatabaseTableID());
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
	
	APIRequestDataLog getAPIResponse(List<ExpenseCategory> assessmentactivities, ExpenseCategory expenseaategory , JSONArray jsonAssessmentactivities, JSONObject jsonExpenseCategory, String message, APIRequestDataLog apiRequest, boolean isTableLog) throws JSONException, JsonProcessingException, ParseException {
		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		long expenseaategoryID = 0;
		
		if (message != null) {
			apiRequest = tableDataLogs.errorDataLog(apiRequest, "ExpenseCategory", message);
			apirequestdatalogRepository.saveAndFlush(apiRequest);
		} else {
			if (expenseaategory != null) {
				apiRequest.setREQUEST_OUTPUT(mapper.writeValueAsString(expenseaategory));
				expenseaategoryID = expenseaategory.getEXPENSECATEGORY_ID();
			} else if(assessmentactivities != null){
					apiRequest.setREQUEST_OUTPUT(mapper.writeValueAsString(assessmentactivities));
			}else if (jsonAssessmentactivities != null){
				apiRequest.setREQUEST_OUTPUT(jsonAssessmentactivities.toString());
			} else if (jsonExpenseCategory != null){
				apiRequest.setREQUEST_OUTPUT(jsonExpenseCategory.toString());
			}
			apiRequest.setRESPONSE_DATETIME(dateFormat1.format(date));
			apiRequest.setREQUEST_STATUS("Success");
			apirequestdatalogRepository.saveAndFlush(apiRequest);
		}
		
		if (isTableLog)
			tbldatalogrepository.saveAndFlush(tableDataLogs.TableSaveDataLog(expenseaategoryID, apiRequest.getDATABASETABLE_ID(), apiRequest.getREQUEST_ID(), apiRequest.getREQUEST_OUTPUT()));
		
		if (apiRequest.getREQUEST_OUTPUT().contains("bearer"))
			apiRequest.setREQUEST_OUTPUT(null);
		
		log.info("Output: " + apiRequest.getREQUEST_OUTPUT());
		log.info("--------------------------------------------------------");

		return apiRequest;
	}
}