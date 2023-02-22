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
import com.cwiztech.account.model.ApprovalReturnAuth;
import com.cwiztech.account.repository.approvalReturnAuthRepository;
import com.cwiztech.services.AccountService;
import com.cwiztech.token.AccessToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
@RequestMapping("/approvalreturnauth")
public class approvalReturnAuthController {
	private static final Logger log = LoggerFactory.getLogger(approvalReturnAuthController.class);

	@Autowired
	private approvalReturnAuthRepository approvalreturnauthrepository;

	@Autowired
	private apiRequestDataLogRepository apirequestdatalogRepository;

	@Autowired
	private tableDataLogRepository tbldatalogrepository;

	@Autowired
	private databaseTablesRepository databasetablesrepository;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity get(@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/approvalreturnauth", null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		List<ApprovalReturnAuth> approvalreturnauths = approvalreturnauthrepository.findActive();
		return new ResponseEntity(getAPIResponse(approvalreturnauths, null, null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity getAll(@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/approvalreturnauth/all", null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		List<ApprovalReturnAuth> approvalreturnauths = approvalreturnauthrepository.findAll();
		
		return new ResponseEntity(getAPIResponse(approvalreturnauths, null, null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity getOne(@PathVariable Long id, @RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/approvalreturnauth/"+id, null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		ApprovalReturnAuth approvalreturnauth = approvalreturnauthrepository.findOne(id);
		
		return new ResponseEntity(getAPIResponse(null, approvalreturnauth, null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/ids", method = RequestMethod.POST)
	public ResponseEntity getByIDs(@RequestBody String data, @RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("POST", "/approvalreturnauth/ids", data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		List<Integer> ids = new ArrayList<Integer>(); 
		JSONObject jsonObj = new JSONObject(data);
		JSONArray jsonapprovalreturnauths = jsonObj.getJSONArray("approvalreturnauths");
		for (int i=0; i<jsonapprovalreturnauths.length(); i++) {
			ids.add((Integer) jsonapprovalreturnauths.get(i));
		}
		List<ApprovalReturnAuth> approvalreturnauths = new ArrayList<ApprovalReturnAuth>();
		if (jsonapprovalreturnauths.length()>0)
			approvalreturnauths = approvalreturnauthrepository.findByIDs(ids);
		
		return new ResponseEntity(getAPIResponse(approvalreturnauths, null, null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity insert(@RequestBody String data,@RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("POST", "/approvalreturnauth", data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		
		return insertupdateAll(null, new JSONObject(data), apiRequest);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity update(@PathVariable Long id, @RequestBody String data, @RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		
		APIRequestDataLog apiRequest = checkToken("PUT", "/approvalreturnauth/"+id, data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		JSONObject jsonObj = new JSONObject(data);
		jsonObj.put("approvalreturnauth_ID", id);
		
		return insertupdateAll(null, jsonObj, apiRequest);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity insertupdate(@RequestBody String data, @RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("PUT", "/approvalreturnauth", data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		
		return insertupdateAll(new JSONArray(data), null, apiRequest);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity insertupdateAll(JSONArray jsonApprovalReturnAuths, JSONObject jsonApprovalReturnAuth, APIRequestDataLog apiRequest) throws JsonProcessingException, JSONException, ParseException {
	    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();

		List<ApprovalReturnAuth> approvalreturnauths = new ArrayList<ApprovalReturnAuth>();
		if (jsonApprovalReturnAuth != null) {
			jsonApprovalReturnAuths = new JSONArray();
			jsonApprovalReturnAuths.put(jsonApprovalReturnAuth);
		}
		log.info(jsonApprovalReturnAuths.toString());
		
		for (int a=0; a<jsonApprovalReturnAuths.length(); a++) {
			JSONObject jsonObj = jsonApprovalReturnAuths.getJSONObject(a);
			ApprovalReturnAuth approvalreturnauth = new ApprovalReturnAuth();
			long approvalreturnauthid = 0;

			if (jsonObj.has("approvalreturnauth_ID")) {
				approvalreturnauthid = jsonObj.getLong("approvalreturnauth_ID");
				if (approvalreturnauthid != 0) {
					approvalreturnauth = approvalreturnauthrepository.findOne(approvalreturnauthid);
					
					if (approvalreturnauth == null)
						return new ResponseEntity(getAPIResponse(null, null, null, null, "Invalid ApprovalReturnAuth Data!", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
				}
			}
			
			if (approvalreturnauthid == 0) {
				
				if (!jsonObj.has("returnauth_ID") || jsonObj.isNull("returnauth_ID"))
					return new ResponseEntity(getAPIResponse(null, null, null, null, "returnauth_ID is missing", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
				
				if (!jsonObj.has("currency_ID") || jsonObj.isNull("currency_ID"))
					return new ResponseEntity(getAPIResponse(null, null, null, null, "currency_ID is missing", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
				
			}
		
			if (jsonObj.has("returnauth_ID") && !jsonObj.isNull("returnauth_ID")) 
				approvalreturnauth.setRETURNAUTH_ID(jsonObj.getLong("returnauth_ID"));
			
			if (jsonObj.has("currency_ID") && !jsonObj.isNull("currency_ID")) 
				approvalreturnauth.setCURRENCY_ID(jsonObj.getLong("currency_ID"));
	
			if (jsonObj.has("approvalreturnauth_AMOUNT") && !jsonObj.isNull("approvalreturnauth_AMOUNT")) 
				approvalreturnauth.setAPPROVALRETURNAUTH_AMOUNT(jsonObj.getDouble("approvalreturnauth_AMOUNT"));
			
			if (jsonObj.has("approvalreturnauth_DATE") && !jsonObj.isNull("approvalreturnauth_DATE"))
				approvalreturnauth.setAPPROVALRETURNAUTH_DATE(jsonObj.getString("approvalreturnauth_DATE"));
			
			if (approvalreturnauthid == 0)
				approvalreturnauth.setISACTIVE("Y");
			else if (jsonObj.has("isactive"))
				approvalreturnauth.setISACTIVE(jsonObj.getString("isactive"));

			approvalreturnauth.setMODIFIED_BY(apiRequest.getREQUEST_ID());
			approvalreturnauth.setMODIFIED_WORKSTATION(apiRequest.getLOG_WORKSTATION());
			approvalreturnauth.setMODIFIED_WHEN(dateFormat1.format(date));
			approvalreturnauths.add(approvalreturnauth);
		}
		
		for (int a=0; a<approvalreturnauths.size(); a++) {
			ApprovalReturnAuth approvalreturnauth = approvalreturnauths.get(a);
			approvalreturnauth = approvalreturnauthrepository.saveAndFlush(approvalreturnauth);
			approvalreturnauths.get(a).setAPPROVALRETURNAUTH_ID(approvalreturnauth.getAPPROVALRETURNAUTH_ID());
		}
		
		ResponseEntity responseentity;
		if (jsonApprovalReturnAuth != null)
			responseentity = new ResponseEntity(getAPIResponse(null, approvalreturnauths.get(0), null, null, null, apiRequest, true).getREQUEST_OUTPUT(), HttpStatus.OK);
		else
			responseentity = new ResponseEntity(getAPIResponse(approvalreturnauths, null, null, null, null, apiRequest, true).getREQUEST_OUTPUT(), HttpStatus.OK);
		return responseentity;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity delete(@PathVariable Long id,@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/approvalreturnauth/"+id, null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		ApprovalReturnAuth approvalreturnauth = approvalreturnauthrepository.findOne(id);
		approvalreturnauthrepository.delete(approvalreturnauth);
		
		return new ResponseEntity(getAPIResponse(null, approvalreturnauth, null, null, null, apiRequest, true).getREQUEST_OUTPUT(), HttpStatus.OK);
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
		APIRequestDataLog apiRequest = checkToken("POST", "/approvalreturnauth/search" + ((active == true) ? "" : "/all"), data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		JSONObject jsonObj = new JSONObject(data);

		List<ApprovalReturnAuth> approvalreturnauths = ((active == true)
				? approvalreturnauthrepository.findBySearch("%" + jsonObj.getString("search") + "%")
				: approvalreturnauthrepository.findAllBySearch("%" + jsonObj.getString("search") + "%"));
		
		return new ResponseEntity(getAPIResponse(approvalreturnauths, null, null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
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
		APIRequestDataLog apiRequest = checkToken("POST", "/approvalreturnauth/advancedsearch" + ((active == true) ? "" : "/all"), data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		
		List<ApprovalReturnAuth> approvalreturnauths = new ArrayList<ApprovalReturnAuth>();
		JSONObject jsonObj = new JSONObject(data);
		long returnauth_ID = 0, currency_ID = 0;

		if (jsonObj.has("returnauth_ID"))
			returnauth_ID = jsonObj.getLong("returnauth_ID");
		
		if (jsonObj.has("currency_ID"))
			currency_ID = jsonObj.getLong("currency_ID");
		
		if(returnauth_ID != 0 || currency_ID != 0){
		 approvalreturnauths = ((active == true)
				? approvalreturnauthrepository.findByAdvancedSearch(returnauth_ID , currency_ID)
				: approvalreturnauthrepository.findAllByAdvancedSearch(returnauth_ID , currency_ID));
		}
		return new ResponseEntity(getAPIResponse(approvalreturnauths, null, null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}

	public APIRequestDataLog checkToken(String requestType, String requestURI, String requestBody, String workstation, String accessToken) throws JsonProcessingException {
		JSONObject checkTokenResponse = AccessToken.checkToken(accessToken);
		DatabaseTables databaseTableID = databasetablesrepository.findOne(ApprovalReturnAuth.getDatabaseTableID());
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
		
		Long requestUser = checkTokenResponse.getLong("user_ID");
		apiRequest = tableDataLogs.apiRequestDataLog(requestType, databaseTableID, requestUser, requestURI, requestBody, workstation);
		apiRequest.setREQUEST_OUTPUT(accessToken);
		return apiRequest;
	}
	
	APIRequestDataLog getAPIResponse(List<ApprovalReturnAuth> approvalreturnauths, ApprovalReturnAuth approvalreturnauth, JSONArray JsonAttributes, JSONObject JsonAttribute, String message, APIRequestDataLog apiRequest, boolean isTableLog) throws JSONException, JsonProcessingException, ParseException {
		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		long approvalreturnauthID = 0;
		
		if (message != null) {
			apiRequest = tableDataLogs.errorDataLog(apiRequest, "ApprovalReturnAuth", message);
			apirequestdatalogRepository.saveAndFlush(apiRequest);
		} else {
			if (approvalreturnauth != null) {
				if(approvalreturnauth.getRETURNAUTH_ID() != null) {
				JSONObject returnauth = new JSONObject(AccountService.GET("returnauth/"+approvalreturnauth.getRETURNAUTH_ID(), apiRequest.getREQUEST_OUTPUT()));
				approvalreturnauth.setRETURNAUTH_DETAIL(returnauth.toString());
			}
				if(approvalreturnauth.getCURRENCY_ID() != null) {
					JSONObject currency = new JSONObject(AccountService.GET("currency/"+approvalreturnauth.getCURRENCY_ID(), apiRequest.getREQUEST_OUTPUT()));
					approvalreturnauth.setCURRENCY_DETAIL(currency.toString());
				}
				apiRequest.setREQUEST_OUTPUT(mapper.writeValueAsString(approvalreturnauth));
				approvalreturnauthID = approvalreturnauth.getAPPROVALRETURNAUTH_ID();
			} else if(approvalreturnauths != null){
				if (approvalreturnauths.size()>0) {
				List<Integer> returnauthList = new ArrayList<Integer>();
				for (int i=0; i<approvalreturnauths.size(); i++) {
					returnauthList.add(Integer.parseInt(approvalreturnauths.get(i).getRETURNAUTH_ID().toString()));
				}
				JSONArray returnauthObject = new JSONArray(AccountService.POST("returnauth/ids", "{returnauths: "+returnauthList+"}", apiRequest.getREQUEST_OUTPUT()));
				
				List<Integer> currencyList = new ArrayList<Integer>();
				for (int i=0; i<approvalreturnauths.size(); i++) {
					currencyList.add(Integer.parseInt(approvalreturnauths.get(i).getCURRENCY_ID().toString()));
				}
				JSONArray currencyObject = new JSONArray(AccountService.POST("currency/ids", "{currencys: "+currencyList+"}", apiRequest.getREQUEST_OUTPUT()));
				
				for (int i=0; i<approvalreturnauths.size(); i++) {
				for (int j=0; j<returnauthObject.length(); j++) {
					JSONObject returnauth = returnauthObject.getJSONObject(j);
					if(approvalreturnauths.get(i).getRETURNAUTH_ID() == returnauth.getLong("returnauth_ID") ) {
						approvalreturnauths.get(i).setRETURNAUTH_DETAIL(returnauth.toString());
					}
				}
				
				for (int j=0; j<currencyObject.length(); j++) {
					JSONObject currency = currencyObject.getJSONObject(j);
					if(approvalreturnauths.get(i).getCURRENCY_ID() == currency.getLong("currency_ID") ) {
						approvalreturnauths.get(i).setCURRENCY_DETAIL(currency.toString());
					}
				}
	      }
		}
				apiRequest.setREQUEST_OUTPUT(mapper.writeValueAsString(approvalreturnauths));
			}else if (JsonAttributes != null) {
				apiRequest.setREQUEST_OUTPUT(JsonAttributes.toString());
			} else if (JsonAttribute != null) {
				apiRequest.setREQUEST_OUTPUT(JsonAttribute.toString());
			}
			apiRequest.setRESPONSE_DATETIME(dateFormat1.format(date));
			apiRequest.setREQUEST_STATUS("Success");
			apirequestdatalogRepository.saveAndFlush(apiRequest);
		}
		
		if (isTableLog)
			tbldatalogrepository.saveAndFlush(tableDataLogs.TableSaveDataLog(approvalreturnauthID, apiRequest.getDATABASETABLE_ID(), apiRequest.getREQUEST_ID(), apiRequest.getREQUEST_OUTPUT()));
		
		log.info("Output: " + apiRequest.getREQUEST_OUTPUT());
		log.info("--------------------------------------------------------");

		return apiRequest;
	}
}
