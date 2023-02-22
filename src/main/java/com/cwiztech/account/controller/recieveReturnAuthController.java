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
import com.cwiztech.account.model.RecieveReturnAuth;
import com.cwiztech.account.repository.recieveReturnAuthRepository;
import com.cwiztech.services.AccountService;
import com.cwiztech.services.LookupService;
import com.cwiztech.token.AccessToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
@RequestMapping("/recievereturnauth")
public class recieveReturnAuthController {
	private static final Logger log = LoggerFactory.getLogger(recieveReturnAuthController.class);

	@Autowired
	private recieveReturnAuthRepository recievereturnauthrepository;

	@Autowired
	private apiRequestDataLogRepository apirequestdatalogRepository;

	@Autowired
	private tableDataLogRepository tbldatalogrepository;

	@Autowired
	private databaseTablesRepository databasetablesrepository;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity get(@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/recievereturnauth", null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		List<RecieveReturnAuth> recievereturnauths = recievereturnauthrepository.findActive();
		return new ResponseEntity(getAPIResponse(recievereturnauths, null, null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity getAll(@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/recievereturnauth/all", null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		List<RecieveReturnAuth> recievereturnauths = recievereturnauthrepository.findAll();
		
		return new ResponseEntity(getAPIResponse(recievereturnauths, null, null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity getOne(@PathVariable Long id, @RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/recievereturnauth/"+id, null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		RecieveReturnAuth recievereturnauth = recievereturnauthrepository.findOne(id);
		
		return new ResponseEntity(getAPIResponse(null, recievereturnauth, null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/ids", method = RequestMethod.POST)
	public ResponseEntity getByIDs(@RequestBody String data, @RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("POST", "/recievereturnauth/ids", data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		List<Integer> ids = new ArrayList<Integer>(); 
		JSONObject jsonObj = new JSONObject(data);
		JSONArray jsonrecievereturnauths = jsonObj.getJSONArray("recievereturnauths");
		for (int i=0; i<jsonrecievereturnauths.length(); i++) {
			ids.add((Integer) jsonrecievereturnauths.get(i));
		}
		List<RecieveReturnAuth> recievereturnauths = new ArrayList<RecieveReturnAuth>();
		if (jsonrecievereturnauths.length()>0)
			recievereturnauths = recievereturnauthrepository.findByIDs(ids);
		
		return new ResponseEntity(getAPIResponse(recievereturnauths, null, null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity insert(@RequestBody String data,@RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("POST", "/recievereturnauth", data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		
		return insertupdateAll(null, new JSONObject(data), apiRequest);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity update(@PathVariable Long id, @RequestBody String data, @RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		
		APIRequestDataLog apiRequest = checkToken("PUT", "/recievereturnauth/"+id, data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		JSONObject jsonObj = new JSONObject(data);
		jsonObj.put("recievereturnauth_ID", id);
		
		return insertupdateAll(null, jsonObj, apiRequest);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity insertupdate(@RequestBody String data, @RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("PUT", "/recievereturnauth", data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		
		return insertupdateAll(new JSONArray(data), null, apiRequest);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity insertupdateAll(JSONArray jsonRecieveReturnAuths, JSONObject jsonRecieveReturnAuth, APIRequestDataLog apiRequest) throws JsonProcessingException, JSONException, ParseException {
	    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();

		List<RecieveReturnAuth> recievereturnauths = new ArrayList<RecieveReturnAuth>();
		if (jsonRecieveReturnAuth != null) {
			jsonRecieveReturnAuths = new JSONArray();
			jsonRecieveReturnAuths.put(jsonRecieveReturnAuth);
		}
		log.info(jsonRecieveReturnAuths.toString());
		
		for (int a=0; a<jsonRecieveReturnAuths.length(); a++) {
			JSONObject jsonObj = jsonRecieveReturnAuths.getJSONObject(a);
			RecieveReturnAuth recievereturnauth = new RecieveReturnAuth();
			long recievereturnauthid = 0;

			if (jsonObj.has("recievereturnauth_ID")) {
				recievereturnauthid = jsonObj.getLong("recievereturnauth_ID");
				if (recievereturnauthid != 0) {
					recievereturnauth = recievereturnauthrepository.findOne(recievereturnauthid);
					
					if (recievereturnauth == null)
						return new ResponseEntity(getAPIResponse(null, null, null, null, "Invalid RecieveReturnAuth Data!", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
				}
			}
			
			if (recievereturnauthid == 0) {
				
				if (!jsonObj.has("returnauth_ID") || jsonObj.isNull("returnauth_ID"))
					return new ResponseEntity(getAPIResponse(null, null, null, null, "returnauth_ID is missing", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
				
				if (!jsonObj.has("currency_ID") || jsonObj.isNull("currency_ID"))
					return new ResponseEntity(getAPIResponse(null, null, null, null, "currency_ID is missing", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
				
			}
		
			if (jsonObj.has("returnauth_ID") && !jsonObj.isNull("returnauth_ID")) 
				recievereturnauth.setRETURNAUTH_ID(jsonObj.getLong("returnauth_ID"));
			
			if (jsonObj.has("currency_ID") && !jsonObj.isNull("currency_ID")) 
				recievereturnauth.setCURRENCY_ID(jsonObj.getLong("currency_ID"));
	
			if (jsonObj.has("recievereturnauth_AMOUNT") && !jsonObj.isNull("recievereturnauth_AMOUNT")) 
				recievereturnauth.setRECIEVERETURNAUTH_AMOUNT(jsonObj.getDouble("recievereturnauth_AMOUNT"));
			
			if (jsonObj.has("recievereturnauth_DATE") && !jsonObj.isNull("recievereturnauth_DATE"))
				recievereturnauth.setRECIEVERETURNAUTH_DATE(jsonObj.getString("recievereturnauth_DATE"));
			
			if (recievereturnauthid == 0)
				recievereturnauth.setISACTIVE("Y");
			else if (jsonObj.has("isactive"))
				recievereturnauth.setISACTIVE(jsonObj.getString("isactive"));

			recievereturnauth.setMODIFIED_BY(apiRequest.getREQUEST_ID());
			recievereturnauth.setMODIFIED_WORKSTATION(apiRequest.getLOG_WORKSTATION());
			recievereturnauth.setMODIFIED_WHEN(dateFormat1.format(date));
			recievereturnauths.add(recievereturnauth);
		}
		
		for (int a=0; a<recievereturnauths.size(); a++) {
			RecieveReturnAuth recievereturnauth = recievereturnauths.get(a);
			recievereturnauth = recievereturnauthrepository.saveAndFlush(recievereturnauth);
			recievereturnauths.get(a).setRECIEVERETURNAUTH_ID(recievereturnauth.getRECIEVERETURNAUTH_ID());
		}
		
		ResponseEntity responseentity;
		if (jsonRecieveReturnAuth != null)
			responseentity = new ResponseEntity(getAPIResponse(null, recievereturnauths.get(0), null, null, null, apiRequest, true).getREQUEST_OUTPUT(), HttpStatus.OK);
		else
			responseentity = new ResponseEntity(getAPIResponse(recievereturnauths, null, null, null, null, apiRequest, true).getREQUEST_OUTPUT(), HttpStatus.OK);
		return responseentity;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity delete(@PathVariable Long id,@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/recievereturnauth/"+id, null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		RecieveReturnAuth recievereturnauth = recievereturnauthrepository.findOne(id);
		recievereturnauthrepository.delete(recievereturnauth);
		
		return new ResponseEntity(getAPIResponse(null, recievereturnauth, null, null, null, apiRequest, true).getREQUEST_OUTPUT(), HttpStatus.OK);
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
		APIRequestDataLog apiRequest = checkToken("POST", "/recievereturnauth/search" + ((active == true) ? "" : "/all"), data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		JSONObject jsonObj = new JSONObject(data);

		List<RecieveReturnAuth> recievereturnauths = ((active == true)
				? recievereturnauthrepository.findBySearch("%" + jsonObj.getString("search") + "%")
				: recievereturnauthrepository.findAllBySearch("%" + jsonObj.getString("search") + "%"));
		
		return new ResponseEntity(getAPIResponse(recievereturnauths, null, null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
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
		APIRequestDataLog apiRequest = checkToken("POST", "/recievereturnauth/advancedsearch" + ((active == true) ? "" : "/all"), data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		
		List<RecieveReturnAuth> recievereturnauths = new ArrayList<RecieveReturnAuth>();
		JSONObject jsonObj = new JSONObject(data);
		long returnauth_ID = 0, currency_ID = 0;

		if (jsonObj.has("returnauth_ID"))
			returnauth_ID = jsonObj.getLong("returnauth_ID");
		
		if (jsonObj.has("currency_ID"))
			currency_ID = jsonObj.getLong("currency_ID");
		
		if(returnauth_ID != 0 || currency_ID != 0){
		 recievereturnauths = ((active == true)
				? recievereturnauthrepository.findByAdvancedSearch(returnauth_ID , currency_ID)
				: recievereturnauthrepository.findAllByAdvancedSearch(returnauth_ID , currency_ID));
		}
		return new ResponseEntity(getAPIResponse(recievereturnauths, null, null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}

	public APIRequestDataLog checkToken(String requestType, String requestURI, String requestBody, String workstation, String accessToken) throws JsonProcessingException {
		JSONObject checkTokenResponse = AccessToken.checkToken(accessToken);
		DatabaseTables databaseTableID = databasetablesrepository.findOne(RecieveReturnAuth.getDatabaseTableID());
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
	
	APIRequestDataLog getAPIResponse(List<RecieveReturnAuth> recievereturnauths, RecieveReturnAuth recievereturnauth, JSONArray JsonAttributes, JSONObject JsonAttribute, String message, APIRequestDataLog apiRequest, boolean isTableLog) throws JSONException, JsonProcessingException, ParseException {
		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		long recievereturnauthID = 0;
		
		if (message != null) {
			apiRequest = tableDataLogs.errorDataLog(apiRequest, "RecieveReturnAuth", message);
			apirequestdatalogRepository.saveAndFlush(apiRequest);
		} else {
			if (recievereturnauth != null) {
				if(recievereturnauth.getRETURNAUTH_ID() != null) {
				JSONObject returnauth = new JSONObject(AccountService.GET("returnauth/"+recievereturnauth.getRETURNAUTH_ID(), apiRequest.getREQUEST_OUTPUT()));
				recievereturnauth.setRETURNAUTH_DETAIL(returnauth.toString());
			}
				if(recievereturnauth.getCURRENCY_ID() != null) {
					JSONObject currency = new JSONObject(AccountService.GET("currency/"+recievereturnauth.getCURRENCY_ID(), apiRequest.getREQUEST_OUTPUT()));
					recievereturnauth.setCURRENCY_DETAIL(currency.toString());
				}
				apiRequest.setREQUEST_OUTPUT(mapper.writeValueAsString(recievereturnauth));
				recievereturnauthID = recievereturnauth.getRECIEVERETURNAUTH_ID();
			} else if(recievereturnauths != null){
				if (recievereturnauths.size()>0) {
				List<Integer> returnauthList = new ArrayList<Integer>();
				for (int i=0; i<recievereturnauths.size(); i++) {
					returnauthList.add(Integer.parseInt(recievereturnauths.get(i).getRETURNAUTH_ID().toString()));
				}
				JSONArray returnauthObject = new JSONArray(AccountService.POST("returnauth/ids", "{returnauths: "+returnauthList+"}", apiRequest.getREQUEST_OUTPUT()));
				
				List<Integer> currencyList = new ArrayList<Integer>();
				for (int i=0; i<recievereturnauths.size(); i++) {
					currencyList.add(Integer.parseInt(recievereturnauths.get(i).getCURRENCY_ID().toString()));
				}
				JSONArray currencyObject = new JSONArray(AccountService.POST("currency/ids", "{currencys: "+currencyList+"}", apiRequest.getREQUEST_OUTPUT()));
				
				for (int i=0; i<recievereturnauths.size(); i++) {
				for (int j=0; j<returnauthObject.length(); j++) {
					JSONObject returnauth = returnauthObject.getJSONObject(j);
					if(recievereturnauths.get(i).getRETURNAUTH_ID() == returnauth.getLong("returnauth_ID") ) {
						recievereturnauths.get(i).setRETURNAUTH_DETAIL(returnauth.toString());
					}
				}
				
				for (int j=0; j<currencyObject.length(); j++) {
					JSONObject currency = currencyObject.getJSONObject(j);
					if(recievereturnauths.get(i).getCURRENCY_ID() == currency.getLong("currency_ID") ) {
						recievereturnauths.get(i).setCURRENCY_DETAIL(currency.toString());
					}
				}
	      }
		}
				apiRequest.setREQUEST_OUTPUT(mapper.writeValueAsString(recievereturnauths));
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
			tbldatalogrepository.saveAndFlush(tableDataLogs.TableSaveDataLog(recievereturnauthID, apiRequest.getDATABASETABLE_ID(), apiRequest.getREQUEST_ID(), apiRequest.getREQUEST_OUTPUT()));
		
		log.info("Output: " + apiRequest.getREQUEST_OUTPUT());
		log.info("--------------------------------------------------------");

		return apiRequest;
	}
}
