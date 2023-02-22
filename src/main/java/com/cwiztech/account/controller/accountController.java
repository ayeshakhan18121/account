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
import com.cwiztech.services.AccountService;
import com.cwiztech.services.LookupService;
import com.cwiztech.account.model.Account;
import com.cwiztech.account.repository.accountRepository;
import com.cwiztech.token.AccessToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
@RequestMapping("/account")

public class accountController {
	
	private static final Logger log = LoggerFactory.getLogger(accountController.class);

	@Autowired
	private accountRepository accountrepository;
	
	@Autowired
	private apiRequestDataLogRepository apirequestdatalogRepository;
	
	@Autowired
	private tableDataLogRepository tbldatalogrepository;

	@Autowired
	private databaseTablesRepository databasetablesrepository;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity get(@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/account", null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		List<Account> accounts = accountrepository.findActive();
		return new ResponseEntity(getAPIResponse(accounts, null , null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity getAll(@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/account/all", null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		List<Account> accounts = accountrepository.findAll();
		
		return new ResponseEntity(getAPIResponse(accounts, null , null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity getOne(@PathVariable Long id, @RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/account/"+id, null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		Account account = accountrepository.findOne(id);
		
		return new ResponseEntity(getAPIResponse(null, account , null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/ids", method = RequestMethod.POST)
	public ResponseEntity getByIDs(@RequestBody String data, @RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("POST", "/account/ids", data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		List<Integer> account_IDS = new ArrayList<Integer>(); 
		JSONObject jsonObj = new JSONObject(data);
		JSONArray jsonaccounts = jsonObj.getJSONArray("accounts");
		for (int i=0; i<jsonaccounts.length(); i++) {
			account_IDS.add((Integer) jsonaccounts.get(i));
		}
		List<Account> accounts = new ArrayList<Account>();
		if (jsonaccounts.length()>0)
			accounts = accountrepository.findByIDs(account_IDS);
		
		return new ResponseEntity(getAPIResponse(accounts, null , null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/notin/ids", method = RequestMethod.POST)
	public ResponseEntity getByNotInIDs(@RequestBody String data, @RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("POST", "/account/notin/ids", data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		List<Integer> account_IDS = new ArrayList<Integer>(); 
		JSONObject jsonObj = new JSONObject(data);
		JSONArray jsonaccounts = jsonObj.getJSONArray("accounts");
		for (int i=0; i<jsonaccounts.length(); i++) {
			account_IDS.add((Integer) jsonaccounts.get(i));
		}
		List<Account> accounts = new ArrayList<Account>();
		if (jsonaccounts.length()>0)
			accounts = accountrepository.findByNotInIDs(account_IDS);
		
		return new ResponseEntity(getAPIResponse(accounts, null , null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity insert(@RequestBody String data,@RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("POST", "/account", data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		
		return insertupdateAll(null, new JSONObject(data), apiRequest);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity update(@PathVariable Long id, @RequestBody String data, @RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		
		APIRequestDataLog apiRequest = checkToken("PUT", "/account/"+id, data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		JSONObject jsonObj = new JSONObject(data);
		jsonObj.put("id", id);
		
		return insertupdateAll(null, jsonObj, apiRequest);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity insertupdate(@RequestBody String data, @RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("PUT", "/account", data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		
		return insertupdateAll(new JSONArray(data), null, apiRequest);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity insertupdateAll(JSONArray jsonAssessmentactivities, JSONObject jsonAccount, APIRequestDataLog apiRequest) throws JsonProcessingException, JSONException, ParseException {
	    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();

		List<Account> accounts = new ArrayList<Account>();
		if (jsonAccount != null) {
			jsonAssessmentactivities = new JSONArray();
			jsonAssessmentactivities.put(jsonAccount);
		}
		log.info(jsonAssessmentactivities.toString());
		
		for (int a=0; a<jsonAssessmentactivities.length(); a++) {
			JSONObject jsonObj = jsonAssessmentactivities.getJSONObject(a);
			Account account = new Account();
			long accountid = 0;

			if (jsonObj.has("account_ID")) {
				accountid = jsonObj.getLong("account_ID");
				if (accountid != 0) {
					account = accountrepository.findOne(accountid);
					
					if (account == null)
						return new ResponseEntity(getAPIResponse(null, null , null, null, "Invalid Account Data!", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
				}
			}
			
			if (accountid == 0) {
				if (!jsonObj.has("accounttype_ID") || jsonObj.isNull("accounttype_ID"))
					return new ResponseEntity(getAPIResponse(null, null , null, null, "accounttype ID is missing", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
				
				if (!jsonObj.has("account_DATE") || jsonObj.isNull("account_DATE"))
					return new ResponseEntity(getAPIResponse(null, null , null, null, "account DATE is missing", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
				
				if (!jsonObj.has("account_CODE") || jsonObj.isNull("account_CODE"))
					return new ResponseEntity(getAPIResponse(null, null , null, null, "account CODE is missing", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
				
				if (!jsonObj.has("account_NAME") || jsonObj.isNull("account_NAME"))
					return new ResponseEntity(getAPIResponse(null, null , null, null, "account NAME is missing", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
				
			}
			
			if (jsonObj.has("accountparent_ID") && !jsonObj.isNull("accountparent_ID"))
			account.setACCOUNTPARENT_ID(jsonObj.getLong("accountparent_ID"));
			
			if (jsonObj.has("accounttype_ID") && !jsonObj.isNull("accounttype_ID"))
				account.setACCOUNTTYPE_ID(jsonObj.getLong("accounttype_ID"));
			
			if (jsonObj.has("account_DATE") && !jsonObj.isNull("account_DATE"))
				account.setACCOUNT_DATE(jsonObj.getString("account_DATE"));
			
			if (jsonObj.has("account_CODE")  && !jsonObj.isNull("account_CODE"))
				account.setACCOUNT_CODE(jsonObj.getString("account_CODE"));
			
			if (jsonObj.has("account_NAME") && !jsonObj.isNull("account_NAME"))
				account.setACCOUNT_NAME(jsonObj.getString("account_NAME"));
			
			if (jsonObj.has("account_DESCRIPTION") && !jsonObj.isNull("account_DESCRIPTION"))
				account.setACCOUNT_DESCRIPTION(jsonObj.getString("account_DESCRIPTION"));

		    if (jsonObj.has("bankaccount_NUMBER") && !jsonObj.isNull("bankaccount_NUMBER"))
			account.setBANKACCOUNT_NUMBER(jsonObj.getString("bankaccount_NUMBER"));
		    
		    if (jsonObj.has("generalratetype_ID") && !jsonObj.isNull("generalratetype_ID"))
				account.setGENERALRATETYPE_ID(jsonObj.getLong("generalratetype_ID"));
			
		    if (jsonObj.has("caseflowratetype_ID") && !jsonObj.isNull("caseflowratetype_ID"))
				account.setCASEFLOWRATETYPE_ID(jsonObj.getLong("caseflowratetype_ID"));
		    
		    if (accountid == 0)
				account.setISACTIVE("Y");
		    else if (jsonObj.has("isactive"))
				account.setISACTIVE(jsonObj.getString("isactive"));

			account.setMODIFIED_BY(apiRequest.getREQUEST_ID());
			account.setMODIFIED_WORKSTATION(apiRequest.getLOG_WORKSTATION());
			account.setMODIFIED_WHEN(dateFormat1.format(date));
			accounts.add(account);
		}
		
		for (int a=0; a<accounts.size(); a++) {
			Account account = accounts.get(a);
			account = accountrepository.saveAndFlush(account);
			accounts.get(a).setACCOUNT_ID(account.getACCOUNT_ID());
		}
		
		List<Integer> account_IDS = new ArrayList<Integer>(); 
		for (int a=0; a<accounts.size(); a++) {
			account_IDS.add((int) accounts.get(a).getACCOUNT_ID());
		}
		accounts = accountrepository.findByIDs(account_IDS);
		
		ResponseEntity responseentity;
		if (jsonAccount != null)
			responseentity = new ResponseEntity(getAPIResponse(null, accounts.get(0) , null, null, null, apiRequest, true).getREQUEST_OUTPUT(), HttpStatus.OK);
		else
			responseentity = new ResponseEntity(getAPIResponse(accounts, null , null, null, null, apiRequest, true).getREQUEST_OUTPUT(), HttpStatus.OK);
		return responseentity;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity delete(@PathVariable Long id,@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/account/"+id, null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		Account account = accountrepository.findOne(id);
		accountrepository.delete(account);
		
		return new ResponseEntity(getAPIResponse(null, account , null, null, null, apiRequest, true).getREQUEST_OUTPUT(), HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public ResponseEntity remove(@PathVariable Long id,@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/account/"+id, null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		
		JSONObject account = new JSONObject();
		account.put("id", id);
		account.put("isactive", "N");
		
		return insertupdateAll(null, account, apiRequest);
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
		APIRequestDataLog apiRequest = checkToken("POST", "/account/search" + ((active == true) ? "" : "/all"), data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		JSONObject jsonObj = new JSONObject(data);

		List<Account> accounts = ((active == true)
				? accountrepository.findBySearch("%" + jsonObj.getString("search") + "%")
				: accountrepository.findAllBySearch("%" + jsonObj.getString("search") + "%"));
		
		return new ResponseEntity(getAPIResponse(accounts, null , null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
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
		APIRequestDataLog apiRequest = checkToken("POST", "/account/advancedsearch" + ((active == true) ? "" : "/all"), data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		
		List<Account> accounts = new ArrayList<Account>();
		JSONObject jsonObj = new JSONObject(data);
		long accountparent_ID = 0, accounttype_ID = 0, generalratetype_ID, classflowratetype_ID = 0;
	
		if (jsonObj.has("accountparent_ID"))
			accountparent_ID = jsonObj.getLong("accountparent_ID");
		
		if (jsonObj.has("accounttype_ID"))
			accounttype_ID = jsonObj.getLong("accounttype_ID");
		
		if (jsonObj.has("generalratetype_ID"))
			generalratetype_ID = jsonObj.getLong("generalratetype_ID");
		
		if (jsonObj.has("classflowratetype_ID"))
			classflowratetype_ID = jsonObj.getLong("classflowratetype_ID");
		
		if(accountparent_ID != 0 || accounttype_ID != 0 || generalratetype_ID != 0 || classflowratetype_ID != 0){
			 accounts = ((active == true)
				? accountrepository.findByAdvancedSearch(accountparent_ID,  accounttype_ID,  generalratetype_ID, classflowratetype_ID)
				: accountrepository.findAllByAdvancedSearch(accountparent_ID,  accounttype_ID,  generalratetype_ID, classflowratetype_ID));
		}
		return new ResponseEntity(getAPIResponse(accounts, null , null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}

	public APIRequestDataLog checkToken(String requestType, String requestURI, String requestBody, String workstation, String accessToken) throws JsonProcessingException {
		JSONObject checkTokenResponse = AccessToken.checkToken(accessToken);
		DatabaseTables databaseTableID = databasetablesrepository.findOne(Account.getDatabaseTableID());
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
	
	APIRequestDataLog getAPIResponse(List<Account> accounts, Account account , JSONArray JsonAttributes, JSONObject JsonAttribute, String message, APIRequestDataLog apiRequest, boolean isTableLog) throws JSONException, JsonProcessingException, ParseException {
		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		long accountID = 0;
		
		if (message != null) {
			apiRequest = tableDataLogs.errorDataLog(apiRequest, "Account", message);
			apirequestdatalogRepository.saveAndFlush(apiRequest);
		} else {
			if (account != null) {
				if(account.getACCOUNTPARENT_ID() != null) {
					JSONObject accountparent = new JSONObject(AccountService.GET("accountparrent/"+account.getACCOUNTPARENT_ID(), apiRequest.getREQUEST_OUTPUT()));
					account.setACCOUNTPARENT_DETAIL(accountparent.toString());
			    }
				if(account.getACCOUNTTYPE_ID() != null) {
					JSONObject accounttype = new JSONObject(AccountService.GET("accounttype/"+account.getACCOUNTTYPE_ID(), apiRequest.getREQUEST_OUTPUT()));
					account.setACCOUNTTYPE_DETAIL(accounttype.toString());
				}
				if(account.getGENERALRATETYPE_ID() != null) {
					JSONObject generalratetype = new JSONObject(LookupService.GET("generalratetype/"+account.getGENERALRATETYPE_ID(), apiRequest.getREQUEST_OUTPUT()));
					account.setGENERALRATETYPE_DETAIL(generalratetype.toString());
				}
				if(account.getCASEFLOWRATETYPE_ID() != null) {
					JSONObject classflowratetype = new JSONObject(LookupService.GET("classflowratetype/"+account.getCASEFLOWRATETYPE_ID(), apiRequest.getREQUEST_OUTPUT()));
					account.setCASEFLOWRATETYPE_DETAIL(classflowratetype.toString());
				}
				apiRequest.setREQUEST_OUTPUT(mapper.writeValueAsString(account));
				accountID = account.getACCOUNT_ID();
				
				} else if(accounts != null){
					if (accounts.size()>0) {
					List<Integer> accountparentList = new ArrayList<Integer>();
					for (int i=0; i<accounts.size(); i++) {
						accountparentList.add(Integer.parseInt(accounts.get(i).getACCOUNTPARENT_ID().toString()));
					}
					JSONArray accountparentObject = new JSONArray(AccountService.POST("accountparent/ids", "{accountparents: "+accountparentList+"}", apiRequest.getREQUEST_OUTPUT()));
					
					List<Integer> accounttypeList = new ArrayList<Integer>();
					for (int i=0; i<accounts.size(); i++) {
						accounttypeList.add(Integer.parseInt(accounts.get(i).getACCOUNTTYPE_ID().toString()));
					}
					JSONArray accounttypeObject = new JSONArray(AccountService.POST("accounttype/ids", "{accounttypes: "+accounttypeList+"}", apiRequest.getREQUEST_OUTPUT()));
					
					List<Integer> generalratetypeList = new ArrayList<Integer>();
					for (int i=0; i<accounts.size(); i++) {
						if(accounts.get(i).getGENERALRATETYPE_ID() != null)
							generalratetypeList.add(Integer.parseInt(accounts.get(i).getGENERALRATETYPE_ID().toString()));
					}
					JSONArray generalratetypeObject = new JSONArray(AccountService.POST("generalratetype/ids", "{generalratetypes: "+generalratetypeList+"}", apiRequest.getREQUEST_OUTPUT()));
			
					List<Integer> classflowratetypeList = new ArrayList<Integer>();
					for (int i=0; i<accounts.size(); i++) {
						if(accounts.get(i).getCASEFLOWRATETYPE_ID() != null)
							classflowratetypeList.add(Integer.parseInt(accounts.get(i).getCASEFLOWRATETYPE_ID().toString()));
					}
					JSONArray classflowratetypeObject = new JSONArray(AccountService.POST("classflowratetype/ids", "{classflowratetypes: "+generalratetypeList+"}", apiRequest.getREQUEST_OUTPUT()));
			
					for (int i=0; i<accounts.size(); i++) {
					for (int j=0; j<accountparentObject.length(); j++) {
						JSONObject accountparent = accountparentObject.getJSONObject(j);
						if(accounts.get(i).getACCOUNTPARENT_ID() == accountparent.getLong("accountparent_ID") ) {
							accounts.get(i).setACCOUNTPARENT_DETAIL(accountparent.toString());
						}
					}
					
					for (int j=0; j<accounttypeObject.length(); j++) {
						JSONObject accounttype = accounttypeObject.getJSONObject(j);
						if(accounts.get(i).getACCOUNTTYPE_ID() == accounttype.getLong("accounttype_ID") ) {
							accounts.get(i).setACCOUNTTYPE_DETAIL(accounttype.toString());
						}
					}
					
					for (int j=0; j<generalratetypeObject.length(); j++) {
					JSONObject generalratetype = generalratetypeObject.getJSONObject(j);
					if(accounts.get(i).getGENERALRATETYPE_ID() == generalratetype.getLong("generalratetype_ID") ) {
						accounts.get(i).setGENERALRATETYPE_DETAIL(generalratetype.toString());
					}
				 }
					
					for (int j=0; j<classflowratetypeObject.length(); j++) {
						JSONObject classflowratetype = classflowratetypeObject.getJSONObject(j);
						if(accounts.get(i).getCASEFLOWRATETYPE_ID() == classflowratetype.getLong("classflowratetype_ID") ) {
							accounts.get(i).setCASEFLOWRATETYPE_DETAIL(classflowratetype.toString());
						}
					 }
		      }
			}
				apiRequest.setREQUEST_OUTPUT(mapper.writeValueAsString(accounts));
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
			tbldatalogrepository.saveAndFlush(tableDataLogs.TableSaveDataLog(accountID, apiRequest.getDATABASETABLE_ID(), apiRequest.getREQUEST_ID(), apiRequest.getREQUEST_OUTPUT()));
		
		log.info("Output: " + apiRequest.getREQUEST_OUTPUT());
		log.info("--------------------------------------------------------");

		return apiRequest;
	}
}