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
import com.cwiztech.account.model.CreditCardTransaction;
import com.cwiztech.account.repository.creditCardTransactionRepository;
import com.cwiztech.services.AccountService;
import com.cwiztech.token.AccessToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
@RequestMapping("/creditcardtransaction")
public class creditCardTransactionController {
	private static final Logger log = LoggerFactory.getLogger(creditCardTransactionController.class);

	@Autowired
	private creditCardTransactionRepository creditcardtransactionrepository;

	@Autowired
	private apiRequestDataLogRepository apirequestdatalogRepository;

	@Autowired
	private tableDataLogRepository tbldatalogrepository;

	@Autowired
	private databaseTablesRepository databasetablesrepository;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity get(@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/creditcardtransaction", null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		List<CreditCardTransaction> creditcardtransactions = creditcardtransactionrepository.findActive();
		return new ResponseEntity(getAPIResponse(creditcardtransactions, null, null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity getAll(@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/creditcardtransaction/all", null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		List<CreditCardTransaction> creditcardtransactions = creditcardtransactionrepository.findAll();
		
		return new ResponseEntity(getAPIResponse(creditcardtransactions, null, null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity getOne(@PathVariable Long id, @RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/creditcardtransaction/"+id, null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		CreditCardTransaction creditcardtransaction = creditcardtransactionrepository.findOne(id);
		
		return new ResponseEntity(getAPIResponse(null, creditcardtransaction, null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/ids", method = RequestMethod.POST)
	public ResponseEntity getByIDs(@RequestBody String data, @RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("POST", "/creditcardtransaction/ids", data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		List<Integer> ids = new ArrayList<Integer>(); 
		JSONObject jsonObj = new JSONObject(data);
		JSONArray jsoncreditcardtransactions = jsonObj.getJSONArray("creditcardtransactions");
		for (int i=0; i<jsoncreditcardtransactions.length(); i++) {
			ids.add((Integer) jsoncreditcardtransactions.get(i));
		}
		List<CreditCardTransaction> creditcardtransactions = new ArrayList<CreditCardTransaction>();
		if (jsoncreditcardtransactions.length()>0)
			creditcardtransactions = creditcardtransactionrepository.findByIDs(ids);
		
		return new ResponseEntity(getAPIResponse(creditcardtransactions, null, null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity insert(@RequestBody String data,@RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("POST", "/creditcardtransaction", data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		
		return insertupdateAll(null, new JSONObject(data), apiRequest);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity update(@PathVariable Long id, @RequestBody String data, @RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		
		APIRequestDataLog apiRequest = checkToken("PUT", "/creditcardtransaction/"+id, data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		JSONObject jsonObj = new JSONObject(data);
		jsonObj.put("creditcardtransaction_ID", id);
		
		return insertupdateAll(null, jsonObj, apiRequest);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity insertupdate(@RequestBody String data, @RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("PUT", "/creditcardtransaction", data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		
		return insertupdateAll(new JSONArray(data), null, apiRequest);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity insertupdateAll(JSONArray jsonCreditCardTransactions, JSONObject jsonCreditCardTransaction, APIRequestDataLog apiRequest) throws JsonProcessingException, JSONException, ParseException {
	    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();

		List<CreditCardTransaction> creditcardtransactions = new ArrayList<CreditCardTransaction>();
		if (jsonCreditCardTransaction != null) {
			jsonCreditCardTransactions = new JSONArray();
			jsonCreditCardTransactions.put(jsonCreditCardTransaction);
		}
		log.info(jsonCreditCardTransactions.toString());
		
		for (int a=0; a<jsonCreditCardTransactions.length(); a++) {
			JSONObject jsonObj = jsonCreditCardTransactions.getJSONObject(a);
			CreditCardTransaction creditcardtransaction = new CreditCardTransaction();
			long creditcardtransactionid = 0;

			if (jsonObj.has("creditcardtransaction_ID")) {
				creditcardtransactionid = jsonObj.getLong("creditcardtransaction_ID");
				if (creditcardtransactionid != 0) {
					creditcardtransaction = creditcardtransactionrepository.findOne(creditcardtransactionid);
					
					if (creditcardtransaction == null)
						return new ResponseEntity(getAPIResponse(null, null, null, null, "Invalid CreditCardTransaction Data!", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
				}
			}
			
			if (creditcardtransactionid == 0) {
				
				if (!jsonObj.has("customer_ID") || jsonObj.isNull("customer_ID"))
					return new ResponseEntity(getAPIResponse(null, null, null, null, "customer_ID is missing", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
				
				if (!jsonObj.has("transaction_DATE") || jsonObj.isNull("transaction_DATE"))
					return new ResponseEntity(getAPIResponse(null, null, null, null, "transaction_DATE is missing", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
				
				if (!jsonObj.has("transaction_AMOUNT") || jsonObj.isNull("transaction_AMOUNT"))
					return new ResponseEntity(getAPIResponse(null, null, null, null, "transaction_AMOUNT is missing", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
				
				if (!jsonObj.has("name_ONCARD") || jsonObj.isNull("name_ONCARD"))
					return new ResponseEntity(getAPIResponse(null, null, null, null, "name_ONCARD is missing", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
				
				if (!jsonObj.has("card_NUMBER") || jsonObj.isNull("card_NUMBER"))
					return new ResponseEntity(getAPIResponse(null, null, null, null, "card_NUMBER is missing", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
				
			}
		
			if (jsonObj.has("customer_ID") && !jsonObj.isNull("customer_ID")) 
				creditcardtransaction.setCUSTOMER_ID(jsonObj.getLong("customer_ID"));
			
			if (jsonObj.has("transaction_DATE") && !jsonObj.isNull("transaction_DATE")) 
				creditcardtransaction.setTRANSACTION_DATE(jsonObj.getString("transaction_DATE"));
	
			if (jsonObj.has("transaction_AMOUNT") && !jsonObj.isNull("transaction_AMOUNT")) 
				creditcardtransaction.setTRANSACTION_AMOUNT(jsonObj.getDouble("transaction_AMOUNT"));
			
			if (jsonObj.has("transaction_STATUS") && !jsonObj.isNull("transaction_STATUS"))
				creditcardtransaction.setTRANSACTION_STATUS(jsonObj.getString("transaction_STATUS"));
			
			if (jsonObj.has("name_ONCARD") && !jsonObj.isNull("name_ONCARD"))
				creditcardtransaction.setNAME_ONCARD(jsonObj.getString("name_ONCARD"));
			
			if (jsonObj.has("card_NUMBER") && !jsonObj.isNull("card_NUMBER"))
				creditcardtransaction.setCARD_NUMBER(jsonObj.getString("card_NUMBER"));
			
			if (jsonObj.has("cardtype_ID") && !jsonObj.isNull("cardtype_ID"))
				creditcardtransaction.setCARDTYPE_ID(jsonObj.getLong("cardtype_ID"));
			
			if (jsonObj.has("authcode") && !jsonObj.isNull("authcode"))
				creditcardtransaction.setAUTHCODE(jsonObj.getLong("authcode"));
			
			if (creditcardtransactionid == 0)
				creditcardtransaction.setISACTIVE("Y");
			else if (jsonObj.has("isactive"))
				creditcardtransaction.setISACTIVE(jsonObj.getString("isactive"));

			creditcardtransaction.setMODIFIED_BY(apiRequest.getREQUEST_ID());
			creditcardtransaction.setMODIFIED_WORKSTATION(apiRequest.getLOG_WORKSTATION());
			creditcardtransaction.setMODIFIED_WHEN(dateFormat1.format(date));
			creditcardtransactions.add(creditcardtransaction);
		}
		
		for (int a=0; a<creditcardtransactions.size(); a++) {
			CreditCardTransaction creditcardtransaction = creditcardtransactions.get(a);
			creditcardtransaction = creditcardtransactionrepository.saveAndFlush(creditcardtransaction);
			creditcardtransactions.get(a).setCREDITCARDTRANSACTION_ID(creditcardtransaction.getCREDITCARDTRANSACTION_ID());
		}
		
		ResponseEntity responseentity;
		if (jsonCreditCardTransaction != null)
			responseentity = new ResponseEntity(getAPIResponse(null, creditcardtransactions.get(0), null, null, null, apiRequest, true).getREQUEST_OUTPUT(), HttpStatus.OK);
		else
			responseentity = new ResponseEntity(getAPIResponse(creditcardtransactions, null, null, null, null, apiRequest, true).getREQUEST_OUTPUT(), HttpStatus.OK);
		return responseentity;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity delete(@PathVariable Long id,@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/creditcardtransaction/"+id, null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		CreditCardTransaction creditcardtransaction = creditcardtransactionrepository.findOne(id);
		creditcardtransactionrepository.delete(creditcardtransaction);
		
		return new ResponseEntity(getAPIResponse(null, creditcardtransaction, null, null, null, apiRequest, true).getREQUEST_OUTPUT(), HttpStatus.OK);
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
		APIRequestDataLog apiRequest = checkToken("POST", "/creditcardtransaction/search" + ((active == true) ? "" : "/all"), data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		JSONObject jsonObj = new JSONObject(data);

		List<CreditCardTransaction> creditcardtransactions = ((active == true)
				? creditcardtransactionrepository.findBySearch("%" + jsonObj.getString("search") + "%")
				: creditcardtransactionrepository.findAllBySearch("%" + jsonObj.getString("search") + "%"));
		
		return new ResponseEntity(getAPIResponse(creditcardtransactions, null, null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
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
		APIRequestDataLog apiRequest = checkToken("POST", "/creditcardtransaction/advancedsearch" + ((active == true) ? "" : "/all"), data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		
		List<CreditCardTransaction> creditcardtransactions = new ArrayList<CreditCardTransaction>();
		JSONObject jsonObj = new JSONObject(data);
		long customer_ID = 0, cardtype_ID = 0;

		if (jsonObj.has("customer_ID"))
			customer_ID = jsonObj.getLong("customer_ID");
		
		if (jsonObj.has("cardtype_ID"))
			cardtype_ID = jsonObj.getLong("cardtype_ID");
		
		if(customer_ID != 0 || cardtype_ID != 0){
		 creditcardtransactions = ((active == true)
				? creditcardtransactionrepository.findByAdvancedSearch(customer_ID , cardtype_ID)
				: creditcardtransactionrepository.findAllByAdvancedSearch(customer_ID , cardtype_ID));
		}
		return new ResponseEntity(getAPIResponse(creditcardtransactions, null, null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}

	public APIRequestDataLog checkToken(String requestType, String requestURI, String requestBody, String workstation, String accessToken) throws JsonProcessingException {
		JSONObject checkTokenResponse = AccessToken.checkToken(accessToken);
		DatabaseTables databaseTableID = databasetablesrepository.findOne(CreditCardTransaction.getDatabaseTableID());
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
	
	APIRequestDataLog getAPIResponse(List<CreditCardTransaction> creditcardtransactions, CreditCardTransaction creditcardtransaction, JSONArray JsonAttributes, JSONObject JsonAttribute, String message, APIRequestDataLog apiRequest, boolean isTableLog) throws JSONException, JsonProcessingException, ParseException {
		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		long creditcardtransactionID = 0;
		
		if (message != null) {
			apiRequest = tableDataLogs.errorDataLog(apiRequest, "CreditCardTransaction", message);
			apirequestdatalogRepository.saveAndFlush(apiRequest);
		} else {
			if (creditcardtransaction != null) {
				if(creditcardtransaction.getCUSTOMER_ID() != null) {
				JSONObject customer = new JSONObject(AccountService.GET("customer/"+creditcardtransaction.getCUSTOMER_ID(), apiRequest.getREQUEST_OUTPUT()));
				creditcardtransaction.setCUSTOMER_DETAIL(customer.toString());
			}
				if(creditcardtransaction.getCARDTYPE_ID() != null) {
					JSONObject cardtype = new JSONObject(AccountService.GET("cardtype/"+creditcardtransaction.getCARDTYPE_ID(), apiRequest.getREQUEST_OUTPUT()));
					creditcardtransaction.setCARDTYPE_DETAIL(cardtype.toString());
				}
				apiRequest.setREQUEST_OUTPUT(mapper.writeValueAsString(creditcardtransaction));
				creditcardtransactionID = creditcardtransaction.getCREDITCARDTRANSACTION_ID();
			} else if(creditcardtransactions != null){
				if (creditcardtransactions.size()>0) {
				List<Integer> customerList = new ArrayList<Integer>();
				for (int i=0; i<creditcardtransactions.size(); i++) {
					customerList.add(Integer.parseInt(creditcardtransactions.get(i).getCUSTOMER_ID().toString()));
				}
				JSONArray customerObject = new JSONArray(AccountService.POST("customer/ids", "{customers: "+customerList+"}", apiRequest.getREQUEST_OUTPUT()));
				
				List<Integer> cardtypeList = new ArrayList<Integer>();
				for (int i=0; i<creditcardtransactions.size(); i++) {
					cardtypeList.add(Integer.parseInt(creditcardtransactions.get(i).getCARDTYPE_ID().toString()));
				}
				JSONArray cardtypeObject = new JSONArray(AccountService.POST("cardtype/ids", "{cardtypes: "+cardtypeList+"}", apiRequest.getREQUEST_OUTPUT()));
				
				for (int i=0; i<creditcardtransactions.size(); i++) {
				for (int j=0; j<customerObject.length(); j++) {
					JSONObject customer = customerObject.getJSONObject(j);
					if(creditcardtransactions.get(i).getCUSTOMER_ID() == customer.getLong("customer_ID") ) {
						creditcardtransactions.get(i).setCUSTOMER_DETAIL(customer.toString());
					}
				}
				
				for (int j=0; j<cardtypeObject.length(); j++) {
					JSONObject cardtype = cardtypeObject.getJSONObject(j);
					if(creditcardtransactions.get(i).getCARDTYPE_ID() == cardtype.getLong("cardtype_ID") ) {
						creditcardtransactions.get(i).setCARDTYPE_DETAIL(cardtype.toString());
					}
				}
	      }
		}
				apiRequest.setREQUEST_OUTPUT(mapper.writeValueAsString(creditcardtransactions));
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
			tbldatalogrepository.saveAndFlush(tableDataLogs.TableSaveDataLog(creditcardtransactionID, apiRequest.getDATABASETABLE_ID(), apiRequest.getREQUEST_ID(), apiRequest.getREQUEST_OUTPUT()));
		
		log.info("Output: " + apiRequest.getREQUEST_OUTPUT());
		log.info("--------------------------------------------------------");

		return apiRequest;
	}
}
