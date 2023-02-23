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
import com.cwiztech.account.model.Currency;
import com.cwiztech.account.repository.currencyRepository;
import com.cwiztech.token.AccessToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
@RequestMapping("/currency")

public class currencyController {
	
	private static final Logger log = LoggerFactory.getLogger(currencyController.class);

	@Autowired
	private currencyRepository currencyrepository;
	
	@Autowired
	private apiRequestDataLogRepository apirequestdatalogRepository;
	
	@Autowired
	private tableDataLogRepository tbldatalogrepository;

	@Autowired
	private databaseTablesRepository databasetablesrepository;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity get(@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/currency", null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		List<Currency> currencies = currencyrepository.findActive();
		return new ResponseEntity(getAPIResponse(currencies, null , null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity getAll(@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/currency/all", null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		List<Currency> currencies = currencyrepository.findAll();
		
		return new ResponseEntity(getAPIResponse(currencies, null , null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity getOne(@PathVariable Long id, @RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/currency/"+id, null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		Currency currency = currencyrepository.findOne(id);
		
		return new ResponseEntity(getAPIResponse(null, currency , null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/ids", method = RequestMethod.POST)
	public ResponseEntity getByIDs(@RequestBody String data, @RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("POST", "/currency/ids", data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		List<Integer> currency_IDS = new ArrayList<Integer>(); 
		JSONObject jsonObj = new JSONObject(data);
		JSONArray jsoncurrencies = jsonObj.getJSONArray("currencies");
		for (int i=0; i<jsoncurrencies.length(); i++) {
			currency_IDS.add((Integer) jsoncurrencies.get(i));
		}
		List<Currency> currencies = new ArrayList<Currency>();
		if (jsoncurrencies.length()>0)
			currencies = currencyrepository.findByIDs(currency_IDS);
		
		return new ResponseEntity(getAPIResponse(currencies, null , null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/notin/ids", method = RequestMethod.POST)
	public ResponseEntity getByNotInIDs(@RequestBody String data, @RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("POST", "/currency/notin/ids", data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		List<Integer> currency_IDS = new ArrayList<Integer>(); 
		JSONObject jsonObj = new JSONObject(data);
		JSONArray jsoncurrencies = jsonObj.getJSONArray("currencies");
		for (int i=0; i<jsoncurrencies.length(); i++) {
			currency_IDS.add((Integer) jsoncurrencies.get(i));
		}
		List<Currency> currencies = new ArrayList<Currency>();
		if (jsoncurrencies.length()>0)
			currencies = currencyrepository.findByNotInIDs(currency_IDS);
		
		return new ResponseEntity(getAPIResponse(currencies, null , null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity insert(@RequestBody String data,@RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("POST", "/currency", data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		
		return insertupdateAll(null, new JSONObject(data), apiRequest);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity update(@PathVariable Long id, @RequestBody String data, @RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		
		APIRequestDataLog apiRequest = checkToken("PUT", "/currency/"+id, data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		JSONObject jsonObj = new JSONObject(data);
		jsonObj.put("currency_ID", id);
		
		return insertupdateAll(null, jsonObj, apiRequest);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity insertupdate(@RequestBody String data, @RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("PUT", "/currency", data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		
		return insertupdateAll(new JSONArray(data), null, apiRequest);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity insertupdateAll(JSONArray jsonAssessmentactivities, JSONObject jsonCurrency, APIRequestDataLog apiRequest) throws JsonProcessingException, JSONException, ParseException {
	    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();

		List<Currency> currencies = new ArrayList<Currency>();
		if (jsonCurrency != null) {
			jsonAssessmentactivities = new JSONArray();
			jsonAssessmentactivities.put(jsonCurrency);
		}
		log.info(jsonAssessmentactivities.toString());
		
		for (int a=0; a<jsonAssessmentactivities.length(); a++) {
			JSONObject jsonObj = jsonAssessmentactivities.getJSONObject(a);
			Currency currency = new Currency();
			long currencyid = 0;

			if (jsonObj.has("currency_ID")) {
				currencyid = jsonObj.getLong("currency_ID");
				if (currencyid != 0) {
					currency = currencyrepository.findOne(currencyid);
					
					if (currency == null)
						return new ResponseEntity(getAPIResponse(null, null , null, null, "Invalid Currency Data!", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
				}
			}
			
			if (currencyid == 0) {
				if (!jsonObj.has("location_ID") || jsonObj.isNull("location_ID"))
					return new ResponseEntity(getAPIResponse(null, null , null, null, "location ID is missing", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
				
				if (!jsonObj.has("iso_CODE") || jsonObj.isNull("iso_CODE"))
					return new ResponseEntity(getAPIResponse(null, null , null, null, "iso CODE is missing", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
				
				if (!jsonObj.has("exchange_RATE") || jsonObj.isNull("exchange_RATE"))
					return new ResponseEntity(getAPIResponse(null, null , null, null, "exchange RATE is missing", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
				
				if (!jsonObj.has("currency_FORMAT") || jsonObj.isNull("currency_FORMAT"))
					return new ResponseEntity(getAPIResponse(null, null , null, null, "currency FORMAT is missing", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
				
				if (!jsonObj.has("currency_SYMBOL") || jsonObj.isNull("currency_SYMBOL"))
					return new ResponseEntity(getAPIResponse(null, null , null, null, "currency SYMBOL is missing", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
				
				if (!jsonObj.has("currencysymbolplacement_ID") || jsonObj.isNull("currencysymbolplacement_ID"))
					return new ResponseEntity(getAPIResponse(null, null , null, null, "currencysymbolplacement ID is missing", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
				
			}
			
			if (jsonObj.has("location_ID") && !jsonObj.isNull("location_ID"))
			currency.setLOCATION_ID(jsonObj.getLong("location_ID"));
			
			if (jsonObj.has("iso_CODE") && !jsonObj.isNull("iso_CODE"))
				currency.setISO_CODE(jsonObj.getString("iso_CODE"));
			
			if (jsonObj.has("exchange_RATE") && !jsonObj.isNull("exchange_RATE"))
				currency.setEXCHANGE_RATE(jsonObj.getDouble("exchange_RATE"));
			
			if (jsonObj.has("currency_FORMAT")  && !jsonObj.isNull("currency_FORMAT"))
				currency.setCURRENCY_FORMAT(jsonObj.getString("currency_FORMAT"));
			
			if (jsonObj.has("currency_SYMBOL") && !jsonObj.isNull("currency_SYMBOL"))
				currency.setCURRENCY_SYMBOL(jsonObj.getString("currency_SYMBOL"));
			
		    if (jsonObj.has("currencysymbolplacement_ID") && !jsonObj.isNull("currencysymbolplacement_ID"))
			currency.setCURRENCYSYMBOLPLACEMENT_ID(jsonObj.getLong("currencysymbolplacement_ID"));
		 
		    if (currencyid == 0)
				currency.setISACTIVE("Y");
		    else if (jsonObj.has("isactive"))
				currency.setISACTIVE(jsonObj.getString("isactive"));

			currency.setMODIFIED_BY(apiRequest.getREQUEST_ID());
			currency.setMODIFIED_WORKSTATION(apiRequest.getLOG_WORKSTATION());
			currency.setMODIFIED_WHEN(dateFormat1.format(date));
			currencies.add(currency);
		}
		
		for (int a=0; a<currencies.size(); a++) {
			Currency currency = currencies.get(a);
			currency = currencyrepository.saveAndFlush(currency);
			currencies.get(a).setCURRENCY_ID(currency.getCURRENCY_ID());
		}
		
		List<Integer> currency_IDS = new ArrayList<Integer>(); 
		for (int a=0; a<currencies.size(); a++) {
			currency_IDS.add((int) currencies.get(a).getCURRENCY_ID());
		}
		currencies = currencyrepository.findByIDs(currency_IDS);
		
		ResponseEntity responseentity;
		if (jsonCurrency != null)
			responseentity = new ResponseEntity(getAPIResponse(null, currencies.get(0) , null, null, null, apiRequest, true).getREQUEST_OUTPUT(), HttpStatus.OK);
		else
			responseentity = new ResponseEntity(getAPIResponse(currencies, null , null, null, null, apiRequest, true).getREQUEST_OUTPUT(), HttpStatus.OK);
		return responseentity;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity delete(@PathVariable Long id,@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/currency/"+id, null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		Currency currency = currencyrepository.findOne(id);
		currencyrepository.delete(currency);
		
		return new ResponseEntity(getAPIResponse(null, currency , null, null, null, apiRequest, true).getREQUEST_OUTPUT(), HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public ResponseEntity remove(@PathVariable Long id,@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/currency/"+id, null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		
		JSONObject currency = new JSONObject();
		currency.put("id", id);
		currency.put("isactive", "N");
		
		return insertupdateAll(null, currency, apiRequest);
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
		APIRequestDataLog apiRequest = checkToken("POST", "/currency/search" + ((active == true) ? "" : "/all"), data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		JSONObject jsonObj = new JSONObject(data);

		List<Currency> currencies = ((active == true)
				? currencyrepository.findBySearch("%" + jsonObj.getString("search") + "%")
				: currencyrepository.findAllBySearch("%" + jsonObj.getString("search") + "%"));
		
		return new ResponseEntity(getAPIResponse(currencies, null , null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
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
		APIRequestDataLog apiRequest = checkToken("POST", "/currency/advancedsearch" + ((active == true) ? "" : "/all"), data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		
		List<Currency> currencies = new ArrayList<Currency>();
		JSONObject jsonObj = new JSONObject(data);
		long location_ID = 0, currencysymbolplacement_ID = 0;
	
		if (jsonObj.has("location_ID"))
			location_ID = jsonObj.getLong("location_ID");
		
		if (jsonObj.has("currencysymbolplacement_ID"))
			currencysymbolplacement_ID = jsonObj.getLong("currencysymbolplacement_ID");
		
		if(location_ID != 0 || currencysymbolplacement_ID != 0){
			 currencies = ((active == true)
				? currencyrepository.findByAdvancedSearch(location_ID,  currencysymbolplacement_ID)
				: currencyrepository.findAllByAdvancedSearch(location_ID,  currencysymbolplacement_ID));
		}
		return new ResponseEntity(getAPIResponse(currencies, null , null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}

	public APIRequestDataLog checkToken(String requestType, String requestURI, String requestBody, String workstation, String accessToken) throws JsonProcessingException {
		JSONObject checkTokenResponse = AccessToken.checkToken(accessToken);
		DatabaseTables databaseTableID = databasetablesrepository.findOne(Currency.getDatabaseTableID());
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
	
	APIRequestDataLog getAPIResponse(List<Currency> currencies, Currency currency , JSONArray JsonAttributes, JSONObject JsonAttribute, String message, APIRequestDataLog apiRequest, boolean isTableLog) throws JSONException, JsonProcessingException, ParseException {
		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		long currencyID = 0;
		
		if (message != null) {
			apiRequest = tableDataLogs.errorDataLog(apiRequest, "Currency", message);
			apirequestdatalogRepository.saveAndFlush(apiRequest);
		} else {
			if (currency != null) {
				if(currency.getLOCATION_ID() != null) {
					JSONObject location = new JSONObject(AccountService.GET("currencyparrent/"+currency.getLOCATION_ID(), apiRequest.getREQUEST_OUTPUT()));
					currency.setLOCATION_DETAIL(location.toString());
			    }
				if(currency.getCURRENCYSYMBOLPLACEMENT_ID() != null) {
					JSONObject currencysymbolplacement = new JSONObject(AccountService.GET("currencysymbolplacement/"+currency.getCURRENCYSYMBOLPLACEMENT_ID(), apiRequest.getREQUEST_OUTPUT()));
					currency.setCURRENCYSYMBOLPLACEMENT_DETAIL(currencysymbolplacement.toString());
				}
			
				apiRequest.setREQUEST_OUTPUT(mapper.writeValueAsString(currency));
				currencyID = currency.getCURRENCY_ID();
				
				} else if(currencies != null){
					if (currencies.size()>0) {
					List<Integer> locationList = new ArrayList<Integer>();
					for (int i=0; i<currencies.size(); i++) {
						locationList.add(Integer.parseInt(currencies.get(i).getLOCATION_ID().toString()));
					}
					JSONArray locationObject = new JSONArray(AccountService.POST("location/ids", "{locations: "+locationList+"}", apiRequest.getREQUEST_OUTPUT()));
					
					List<Integer> currencysymbolplacementList = new ArrayList<Integer>();
					for (int i=0; i<currencies.size(); i++) {
						currencysymbolplacementList.add(Integer.parseInt(currencies.get(i).getCURRENCYSYMBOLPLACEMENT_ID().toString()));
					}
					JSONArray currencysymbolplacementObject = new JSONArray(AccountService.POST("currencysymbolplacement/ids", "{currencysymbolplacements: "+currencysymbolplacementList+"}", apiRequest.getREQUEST_OUTPUT()));
					
					
					for (int i=0; i<currencies.size(); i++) {
					for (int j=0; j<locationObject.length(); j++) {
						JSONObject location = locationObject.getJSONObject(j);
						if(currencies.get(i).getLOCATION_ID() == location.getLong("location_ID") ) {
							currencies.get(i).setLOCATION_DETAIL(location.toString());
						}
					}
					
					for (int j=0; j<currencysymbolplacementObject.length(); j++) {
						JSONObject currencysymbolplacement = locationObject.getJSONObject(j);
						if(currencies.get(i).getCURRENCYSYMBOLPLACEMENT_ID() == currencysymbolplacement.getLong("currencysymbolplacement_ID") ) {
							currencies.get(i).setCURRENCYSYMBOLPLACEMENT_DETAIL(currencysymbolplacement.toString());
						}
					}
		      }
			}
				apiRequest.setREQUEST_OUTPUT(mapper.writeValueAsString(currencies));
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
			tbldatalogrepository.saveAndFlush(tableDataLogs.TableSaveDataLog(currencyID, apiRequest.getDATABASETABLE_ID(), apiRequest.getREQUEST_ID(), apiRequest.getREQUEST_OUTPUT()));
		
		log.info("Output: " + apiRequest.getREQUEST_OUTPUT());
		log.info("--------------------------------------------------------");

		return apiRequest;
	}
}