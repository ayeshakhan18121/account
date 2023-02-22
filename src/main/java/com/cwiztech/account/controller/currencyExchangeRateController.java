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
import com.cwiztech.account.model.CurrencyExchangeRate;
import com.cwiztech.account.repository.currencyExchangeRateRepository;
import com.cwiztech.services.AccountService;
import com.cwiztech.token.AccessToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
@RequestMapping("/currencyexchangerate")
public class currencyExchangeRateController {
	private static final Logger log = LoggerFactory.getLogger(currencyExchangeRateController.class);

	@Autowired
	private currencyExchangeRateRepository currencyexchangeraterepository;

	@Autowired
	private apiRequestDataLogRepository apirequestdatalogRepository;

	@Autowired
	private tableDataLogRepository tbldatalogrepository;

	@Autowired
	private databaseTablesRepository databasetablesrepository;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity get(@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/currencyexchangerate", null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		List<CurrencyExchangeRate> currencyexchangerates = currencyexchangeraterepository.findActive();
		return new ResponseEntity(getAPIResponse(currencyexchangerates, null, null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity getAll(@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/currencyexchangerate/all", null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		List<CurrencyExchangeRate> currencyexchangerates = currencyexchangeraterepository.findAll();
		
		return new ResponseEntity(getAPIResponse(currencyexchangerates, null, null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity getOne(@PathVariable Long id, @RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/currencyexchangerate/"+id, null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		CurrencyExchangeRate currencyexchangerate = currencyexchangeraterepository.findOne(id);
		
		return new ResponseEntity(getAPIResponse(null, currencyexchangerate, null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/ids", method = RequestMethod.POST)
	public ResponseEntity getByIDs(@RequestBody String data, @RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("POST", "/currencyexchangerate/ids", data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		List<Integer> ids = new ArrayList<Integer>(); 
		JSONObject jsonObj = new JSONObject(data);
		JSONArray jsoncurrencyexchangerates = jsonObj.getJSONArray("currencyexchangerates");
		for (int i=0; i<jsoncurrencyexchangerates.length(); i++) {
			ids.add((Integer) jsoncurrencyexchangerates.get(i));
		}
		List<CurrencyExchangeRate> currencyexchangerates = new ArrayList<CurrencyExchangeRate>();
		if (jsoncurrencyexchangerates.length()>0)
			currencyexchangerates = currencyexchangeraterepository.findByIDs(ids);
		
		return new ResponseEntity(getAPIResponse(currencyexchangerates, null, null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity insert(@RequestBody String data,@RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("POST", "/currencyexchangerate", data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		
		return insertupdateAll(null, new JSONObject(data), apiRequest);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity update(@PathVariable Long id, @RequestBody String data, @RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		
		APIRequestDataLog apiRequest = checkToken("PUT", "/currencyexchangerate/"+id, data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		JSONObject jsonObj = new JSONObject(data);
		jsonObj.put("currencyexchangerate_ID", id);
		
		return insertupdateAll(null, jsonObj, apiRequest);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity insertupdate(@RequestBody String data, @RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("PUT", "/currencyexchangerate", data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		
		return insertupdateAll(new JSONArray(data), null, apiRequest);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity insertupdateAll(JSONArray jsonCurrencyExchangeRates, JSONObject jsonCurrencyExchangeRate, APIRequestDataLog apiRequest) throws JsonProcessingException, JSONException, ParseException {
	    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();

		List<CurrencyExchangeRate> currencyexchangerates = new ArrayList<CurrencyExchangeRate>();
		if (jsonCurrencyExchangeRate != null) {
			jsonCurrencyExchangeRates = new JSONArray();
			jsonCurrencyExchangeRates.put(jsonCurrencyExchangeRate);
		}
		log.info(jsonCurrencyExchangeRates.toString());
		
		for (int a=0; a<jsonCurrencyExchangeRates.length(); a++) {
			JSONObject jsonObj = jsonCurrencyExchangeRates.getJSONObject(a);
			CurrencyExchangeRate currencyexchangerate = new CurrencyExchangeRate();
			long currencyexchangerateid = 0;

			if (jsonObj.has("currencyexchangerate_ID")) {
				currencyexchangerateid = jsonObj.getLong("currencyexchangerate_ID");
				if (currencyexchangerateid != 0) {
					currencyexchangerate = currencyexchangeraterepository.findOne(currencyexchangerateid);
					
					if (currencyexchangerate == null)
						return new ResponseEntity(getAPIResponse(null, null, null, null, "Invalid CurrencyExchangeRate Data!", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
				}
			}
			
			if (currencyexchangerateid == 0) {
				
				if (!jsonObj.has("effective_DATE") || jsonObj.isNull("effective_DATE"))
					return new ResponseEntity(getAPIResponse(null, null, null, null, "effective_DATE is missing", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
				
			}
		
			if (jsonObj.has("currency_ID") && !jsonObj.isNull("currency_ID")) 
				currencyexchangerate.setCURRENCY_ID(jsonObj.getLong("currency_ID"));
	
			if (jsonObj.has("effective_DATE") && !jsonObj.isNull("effective_DATE")) 
				currencyexchangerate.setEFFECTIVE_DATE(jsonObj.getString("effective_DATE"));
			
			if (currencyexchangerateid == 0)
				currencyexchangerate.setISACTIVE("Y");
			else if (jsonObj.has("isactive"))
				currencyexchangerate.setISACTIVE(jsonObj.getString("isactive"));

			currencyexchangerate.setMODIFIED_BY(apiRequest.getREQUEST_ID());
			currencyexchangerate.setMODIFIED_WORKSTATION(apiRequest.getLOG_WORKSTATION());
			currencyexchangerate.setMODIFIED_WHEN(dateFormat1.format(date));
			currencyexchangerates.add(currencyexchangerate);
		}
		
		for (int a=0; a<currencyexchangerates.size(); a++) {
			CurrencyExchangeRate currencyexchangerate = currencyexchangerates.get(a);
			currencyexchangerate = currencyexchangeraterepository.saveAndFlush(currencyexchangerate);
			currencyexchangerates.get(a).setCURRENCYEXCHANGERATE_ID(currencyexchangerate.getCURRENCYEXCHANGERATE_ID());
		}
		
		ResponseEntity responseentity;
		if (jsonCurrencyExchangeRate != null)
			responseentity = new ResponseEntity(getAPIResponse(null, currencyexchangerates.get(0), null, null, null, apiRequest, true).getREQUEST_OUTPUT(), HttpStatus.OK);
		else
			responseentity = new ResponseEntity(getAPIResponse(currencyexchangerates, null, null, null, null, apiRequest, true).getREQUEST_OUTPUT(), HttpStatus.OK);
		return responseentity;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity delete(@PathVariable Long id,@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/currencyexchangerate/"+id, null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		CurrencyExchangeRate currencyexchangerate = currencyexchangeraterepository.findOne(id);
		currencyexchangeraterepository.delete(currencyexchangerate);
		
		return new ResponseEntity(getAPIResponse(null, currencyexchangerate, null, null, null, apiRequest, true).getREQUEST_OUTPUT(), HttpStatus.OK);
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
		APIRequestDataLog apiRequest = checkToken("POST", "/currencyexchangerate/search" + ((active == true) ? "" : "/all"), data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		JSONObject jsonObj = new JSONObject(data);

		List<CurrencyExchangeRate> currencyexchangerates = ((active == true)
				? currencyexchangeraterepository.findBySearch("%" + jsonObj.getString("search") + "%")
				: currencyexchangeraterepository.findAllBySearch("%" + jsonObj.getString("search") + "%"));
		
		return new ResponseEntity(getAPIResponse(currencyexchangerates, null, null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
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
		APIRequestDataLog apiRequest = checkToken("POST", "/currencyexchangerate/advancedsearch" + ((active == true) ? "" : "/all"), data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		
		List<CurrencyExchangeRate> currencyexchangerates = new ArrayList<CurrencyExchangeRate>();
		JSONObject jsonObj = new JSONObject(data);
		long  currency_ID = 0;

		if (jsonObj.has("currency_ID"))
			currency_ID = jsonObj.getLong("currency_ID");
		
		if(currency_ID != 0){
		 currencyexchangerates = ((active == true)
				? currencyexchangeraterepository.findByAdvancedSearch(currency_ID)
				: currencyexchangeraterepository.findAllByAdvancedSearch(currency_ID));
		}
		return new ResponseEntity(getAPIResponse(currencyexchangerates, null, null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}

	public APIRequestDataLog checkToken(String requestType, String requestURI, String requestBody, String workstation, String accessToken) throws JsonProcessingException {
		JSONObject checkTokenResponse = AccessToken.checkToken(accessToken);
		DatabaseTables databaseTableID = databasetablesrepository.findOne(CurrencyExchangeRate.getDatabaseTableID());
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
	
	APIRequestDataLog getAPIResponse(List<CurrencyExchangeRate> currencyexchangerates, CurrencyExchangeRate currencyexchangerate, JSONArray JsonAttributes, JSONObject JsonAttribute, String message, APIRequestDataLog apiRequest, boolean isTableLog) throws JSONException, JsonProcessingException, ParseException {
		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		long currencyexchangerateID = 0;
		
		if (message != null) {
			apiRequest = tableDataLogs.errorDataLog(apiRequest, "CurrencyExchangeRate", message);
			apirequestdatalogRepository.saveAndFlush(apiRequest);
		} else {
			if (currencyexchangerate != null) {
			
				if(currencyexchangerate.getCURRENCY_ID() != null) {
					JSONObject currency = new JSONObject(AccountService.GET("currency/"+currencyexchangerate.getCURRENCY_ID(), apiRequest.getREQUEST_OUTPUT()));
					currencyexchangerate.setCURRENCY_DETAIL(currency.toString());
				}
				apiRequest.setREQUEST_OUTPUT(mapper.writeValueAsString(currencyexchangerate));
				currencyexchangerateID = currencyexchangerate.getCURRENCYEXCHANGERATE_ID();
			} else if(currencyexchangerates != null){
				if (currencyexchangerates.size()>0) {
				
				List<Integer> currencyList = new ArrayList<Integer>();
				for (int i=0; i<currencyexchangerates.size(); i++) {
					currencyList.add(Integer.parseInt(currencyexchangerates.get(i).getCURRENCY_ID().toString()));
				}
				JSONArray currencyObject = new JSONArray(AccountService.POST("currency/ids", "{currencys: "+currencyList+"}", apiRequest.getREQUEST_OUTPUT()));
				
				for (int i=0; i<currencyexchangerates.size(); i++) {
								
				for (int j=0; j<currencyObject.length(); j++) {
					JSONObject currency = currencyObject.getJSONObject(j);
					if(currencyexchangerates.get(i).getCURRENCY_ID() == currency.getLong("currency_ID") ) {
						currencyexchangerates.get(i).setCURRENCY_DETAIL(currency.toString());
					}
				}
	      }
		}
				apiRequest.setREQUEST_OUTPUT(mapper.writeValueAsString(currencyexchangerates));
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
			tbldatalogrepository.saveAndFlush(tableDataLogs.TableSaveDataLog(currencyexchangerateID, apiRequest.getDATABASETABLE_ID(), apiRequest.getREQUEST_ID(), apiRequest.getREQUEST_OUTPUT()));
		
		log.info("Output: " + apiRequest.getREQUEST_OUTPUT());
		log.info("--------------------------------------------------------");

		return apiRequest;
	}
}
