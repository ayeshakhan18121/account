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
import com.cwiztech.account.model.ReturnAuth;
import com.cwiztech.account.repository.returnAuthRepository;
import com.cwiztech.services.AccountService;
import com.cwiztech.token.AccessToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
@RequestMapping("/returnauth")
public class returnAuthController {
	private static final Logger log = LoggerFactory.getLogger(returnAuthController.class);

	@Autowired
	private returnAuthRepository returnauthrepository;

	@Autowired
	private apiRequestDataLogRepository apirequestdatalogRepository;

	@Autowired
	private tableDataLogRepository tbldatalogrepository;

	@Autowired
	private databaseTablesRepository databasetablesrepository;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity get(@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/returnauth", null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		List<ReturnAuth> returnauths = returnauthrepository.findActive();
		return new ResponseEntity(getAPIResponse(returnauths, null, null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity getAll(@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/returnauth/all", null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		List<ReturnAuth> returnauths = returnauthrepository.findAll();
		
		return new ResponseEntity(getAPIResponse(returnauths, null, null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity getOne(@PathVariable Long id, @RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/returnauth/"+id, null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		ReturnAuth returnauth = returnauthrepository.findOne(id);
		
		return new ResponseEntity(getAPIResponse(null, returnauth, null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/ids", method = RequestMethod.POST)
	public ResponseEntity getByIDs(@RequestBody String data, @RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("POST", "/returnauth/ids", data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		List<Integer> ids = new ArrayList<Integer>(); 
		JSONObject jsonObj = new JSONObject(data);
		JSONArray jsonreturnauths = jsonObj.getJSONArray("returnauths");
		for (int i=0; i<jsonreturnauths.length(); i++) {
			ids.add((Integer) jsonreturnauths.get(i));
		}
		List<ReturnAuth> returnauths = new ArrayList<ReturnAuth>();
		if (jsonreturnauths.length()>0)
			returnauths = returnauthrepository.findByIDs(ids);
		
		return new ResponseEntity(getAPIResponse(returnauths, null, null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity insert(@RequestBody String data,@RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("POST", "/returnauth", data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		
		return insertupdateAll(null, new JSONObject(data), apiRequest);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity update(@PathVariable Long id, @RequestBody String data, @RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		
		APIRequestDataLog apiRequest = checkToken("PUT", "/returnauth/"+id, data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		JSONObject jsonObj = new JSONObject(data);
		jsonObj.put("returnauth_ID", id);
		
		return insertupdateAll(null, jsonObj, apiRequest);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity insertupdate(@RequestBody String data, @RequestHeader(value = "Authorization") String headToken)
			throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("PUT", "/returnauth", data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		
		return insertupdateAll(new JSONArray(data), null, apiRequest);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity insertupdateAll(JSONArray jsonReturnAuths, JSONObject jsonReturnAuth, APIRequestDataLog apiRequest) throws JsonProcessingException, JSONException, ParseException {
	    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();

		List<ReturnAuth> returnauths = new ArrayList<ReturnAuth>();
		if (jsonReturnAuth != null) {
			jsonReturnAuths = new JSONArray();
			jsonReturnAuths.put(jsonReturnAuth);
		}
		log.info(jsonReturnAuths.toString());
		
		for (int a=0; a<jsonReturnAuths.length(); a++) {
			JSONObject jsonObj = jsonReturnAuths.getJSONObject(a);
			ReturnAuth returnauth = new ReturnAuth();
			long returnauthid = 0;

			if (jsonObj.has("returnauth_ID")) {
				returnauthid = jsonObj.getLong("returnauth_ID");
				if (returnauthid != 0) {
					returnauth = returnauthrepository.findOne(returnauthid);
					
					if (returnauth == null)
						return new ResponseEntity(getAPIResponse(null, null, null, null, "Invalid ReturnAuth Data!", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
				}
			}
			
			if (returnauthid == 0) {
				
				if (!jsonObj.has("customerrefund_ID") || jsonObj.isNull("customerrefund_ID"))
					return new ResponseEntity(getAPIResponse(null, null, null, null, "customerrefund_ID is missing", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
				
				if (!jsonObj.has("customer_ID") || jsonObj.isNull("customer_ID"))
					return new ResponseEntity(getAPIResponse(null, null, null, null, "customer_ID is missing", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
				
				if (!jsonObj.has("product_ID") || jsonObj.isNull("product_ID"))
					return new ResponseEntity(getAPIResponse(null, null, null, null, "product_ID is missing", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
				
				if (!jsonObj.has("returnauth_DATE") || jsonObj.isNull("returnauth_DATE"))
					return new ResponseEntity(getAPIResponse(null, null, null, null, "returnauth_DATE is missing", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
				
				if (!jsonObj.has("delivery_DATE") || jsonObj.isNull("delivery_DATE"))
					return new ResponseEntity(getAPIResponse(null, null, null, null, "delivery_DATE is missing", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
				
				if (!jsonObj.has("exchange_Rate") || jsonObj.isNull("exchange_Rate"))
					return new ResponseEntity(getAPIResponse(null, null, null, null, "exchange_Rate is missing", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
				
				if (!jsonObj.has("currency_ID") || jsonObj.isNull("currency_ID"))
					return new ResponseEntity(getAPIResponse(null, null, null, null, "currency_ID is missing", apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
				
			}
			
			if (jsonObj.has("customerrefund_ID") && !jsonObj.isNull("customerrefund_ID")) 
				returnauth.setCUSTOMERREFUND_ID(jsonObj.getLong("customerrefund_ID"));
		
			if (jsonObj.has("customer_ID") && !jsonObj.isNull("customer_ID")) 
				returnauth.setCUSTOMER_ID(jsonObj.getLong("customer_ID"));
			
			if (jsonObj.has("product_ID") && !jsonObj.isNull("product_ID")) 
				returnauth.setPRODUCT_ID(jsonObj.getLong("product_ID"));
	
			if (jsonObj.has("returnauth_CODE") && !jsonObj.isNull("returnauth_CODE")) 
				returnauth.setRETURNAUTH_CODE(jsonObj.getString("returnauth_CODE"));
			
			if (jsonObj.has("returnauth_DATE") && !jsonObj.isNull("returnauth_DATE"))
				returnauth.setRETURNAUTH_DATE(jsonObj.getString("returnauth_DATE"));
			
			if (jsonObj.has("delivery_DATE") && !jsonObj.isNull("delivery_DATE"))
				returnauth.setDELIVERY_DATE(jsonObj.getDouble("delivery_DATE"));
			
			if (jsonObj.has("currency_ID") && !jsonObj.isNull("currency_ID")) 
				returnauth.setCURRENCY_ID(jsonObj.getLong("currency_ID"));
			
			if (jsonObj.has("exchange_Rate") && !jsonObj.isNull("exchange_Rate")) 
				returnauth.setEXCHANGE_RATE(jsonObj.getDouble("exchange_Rate"));
			
			if (jsonObj.has("rate") && !jsonObj.isNull("rate")) 
				returnauth.setRATE(jsonObj.getDouble("rate"));
			
			if (jsonObj.has("discount") && !jsonObj.isNull("discount")) 
				returnauth.setDISCOUNT(jsonObj.getDouble("discount"));
			
			if (jsonObj.has("check_NUMBER") && !jsonObj.isNull("check_NUMBER")) 
				returnauth.setRETURNSTATUS_ID(jsonObj.getLong("check_NUMBER"));
			
			if (jsonObj.has("creditcard_NUMBER") && !jsonObj.isNull("creditcard_NUMBER")) 
				returnauth.setSALEORDERTYPE_ID(jsonObj.getLong("creditcard_NUMBER"));
			
			if (returnauthid == 0)
				returnauth.setISACTIVE("Y");
			else if (jsonObj.has("isactive"))
				returnauth.setISACTIVE(jsonObj.getString("isactive"));

			returnauth.setMODIFIED_BY(apiRequest.getREQUEST_ID());
			returnauth.setMODIFIED_WORKSTATION(apiRequest.getLOG_WORKSTATION());
			returnauth.setMODIFIED_WHEN(dateFormat1.format(date));
			returnauths.add(returnauth);
		}
		
		for (int a=0; a<returnauths.size(); a++) {
			ReturnAuth returnauth = returnauths.get(a);
			returnauth = returnauthrepository.saveAndFlush(returnauth);
			returnauths.get(a).setRETURNAUTH_ID(returnauth.getRETURNAUTH_ID());
		}
		
		ResponseEntity responseentity;
		if (jsonReturnAuth != null)
			responseentity = new ResponseEntity(getAPIResponse(null, returnauths.get(0), null, null, null, apiRequest, true).getREQUEST_OUTPUT(), HttpStatus.OK);
		else
			responseentity = new ResponseEntity(getAPIResponse(returnauths, null, null, null, null, apiRequest, true).getREQUEST_OUTPUT(), HttpStatus.OK);
		return responseentity;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity delete(@PathVariable Long id,@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		APIRequestDataLog apiRequest = checkToken("GET", "/returnauth/"+id, null, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		ReturnAuth returnauth = returnauthrepository.findOne(id);
		returnauthrepository.delete(returnauth);
		
		return new ResponseEntity(getAPIResponse(null, returnauth, null, null, null, apiRequest, true).getREQUEST_OUTPUT(), HttpStatus.OK);
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
		APIRequestDataLog apiRequest = checkToken("POST", "/returnauth/search" + ((active == true) ? "" : "/all"), data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);

		JSONObject jsonObj = new JSONObject(data);

		List<ReturnAuth> returnauths = ((active == true)
				? returnauthrepository.findBySearch("%" + jsonObj.getString("search") + "%")
				: returnauthrepository.findAllBySearch("%" + jsonObj.getString("search") + "%"));
		
		return new ResponseEntity(getAPIResponse(returnauths, null, null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
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
		APIRequestDataLog apiRequest = checkToken("POST", "/returnauth/advancedsearch" + ((active == true) ? "" : "/all"), data, null, headToken);
		if (apiRequest.getREQUEST_STATUS() != null) return new ResponseEntity(apiRequest.getREQUEST_OUTPUT(), HttpStatus.BAD_REQUEST);
		
		List<ReturnAuth> returnauths = new ArrayList<ReturnAuth>();
		JSONObject jsonObj = new JSONObject(data);
		long customer_ID = 0, product_ID = 0, currency_ID = 0, returnstatus_ID = 0, saleordertype_ID =0, customerrefund_ID =0;

		if (jsonObj.has("customer_ID"))
			customer_ID = jsonObj.getLong("customer_ID");
		
		if (jsonObj.has("customerrefund_ID"))
			customerrefund_ID = jsonObj.getLong("customerrefund_ID");
		
		if (jsonObj.has("product_ID"))
			product_ID = jsonObj.getLong("product_ID");
		
		if (jsonObj.has("currency_ID"))
			currency_ID = jsonObj.getLong("currency_ID");
		
		if (jsonObj.has("returnstatus_ID"))
			returnstatus_ID = jsonObj.getLong("returnstatus_ID");
		
		if (jsonObj.has("saleordertype_ID"))
			saleordertype_ID = jsonObj.getLong("saleordertype_ID");
		
		if(customer_ID != 0 || product_ID != 0 || currency_ID != 0 || returnstatus_ID != 0 || saleordertype_ID != 0 || customerrefund_ID !=0){
		 returnauths = ((active == true)
				? returnauthrepository.findByAdvancedSearch(customer_ID , product_ID , currency_ID , returnstatus_ID , saleordertype_ID, customerrefund_ID)
				: returnauthrepository.findAllByAdvancedSearch(customer_ID , product_ID , currency_ID , returnstatus_ID , saleordertype_ID, customerrefund_ID));
		}
		return new ResponseEntity(getAPIResponse(returnauths, null, null, null, null, apiRequest, false).getREQUEST_OUTPUT(), HttpStatus.OK);
	}

	public APIRequestDataLog checkToken(String requestType, String requestURI, String requestBody, String workstation, String accessToken) throws JsonProcessingException {
		JSONObject checkTokenResponse = AccessToken.checkToken(accessToken);
		DatabaseTables databaseTableID = databasetablesrepository.findOne(ReturnAuth.getDatabaseTableID());
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
	
	APIRequestDataLog getAPIResponse(List<ReturnAuth> returnauths, ReturnAuth returnauth, JSONArray JsonAttributes, JSONObject JsonAttribute, String message, APIRequestDataLog apiRequest, boolean isTableLog) throws JSONException, JsonProcessingException, ParseException {
		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		long returnauthID = 0;
		
		if (message != null) {
			apiRequest = tableDataLogs.errorDataLog(apiRequest, "ReturnAuth", message);
			apirequestdatalogRepository.saveAndFlush(apiRequest);
		} else {
			if (returnauth != null) {
				if(returnauth.getCUSTOMERREFUND_ID() != null) {
					JSONObject customerrefund = new JSONObject(AccountService.GET("customerrefund/"+returnauth.getCUSTOMERREFUND_ID(), apiRequest.getREQUEST_OUTPUT()));
					returnauth.setCUSTOMERREFUND_DETAIL(customerrefund.toString());
				}
				if(returnauth.getCUSTOMER_ID() != null) {
				JSONObject customer = new JSONObject(AccountService.GET("customer/"+returnauth.getCUSTOMER_ID(), apiRequest.getREQUEST_OUTPUT()));
				returnauth.setCUSTOMER_DETAIL(customer.toString());
			}
				if(returnauth.getPRODUCT_ID() != null) {
					JSONObject product = new JSONObject(AccountService.GET("product/"+returnauth.getPRODUCT_ID(), apiRequest.getREQUEST_OUTPUT()));
					returnauth.setPRODUCT_DETAIL(product.toString());
				}
				if(returnauth.getCURRENCY_ID() != null) {
					JSONObject currency = new JSONObject(AccountService.GET("currency/"+returnauth.getCURRENCY_ID(), apiRequest.getREQUEST_OUTPUT()));
					returnauth.setCURRENCY_DETAIL(currency.toString());
				}
				if(returnauth.getRETURNSTATUS_ID() != null) {
					JSONObject postingpperiod = new JSONObject(AccountService.GET("postingpperiod/"+returnauth.getRETURNSTATUS_ID(), apiRequest.getREQUEST_OUTPUT()));
					returnauth.setRETURNSTATUS_DETAIL(postingpperiod.toString());
				}
				if(returnauth.getSALEORDERTYPE_ID() != null) {
					JSONObject saleordertype = new JSONObject(AccountService.GET("saleordertype/"+returnauth.getSALEORDERTYPE_ID(), apiRequest.getREQUEST_OUTPUT()));
					returnauth.setSALEORDERTYPE_DETAIL(saleordertype.toString());
				}
				apiRequest.setREQUEST_OUTPUT(mapper.writeValueAsString(returnauth));
				returnauthID = returnauth.getRETURNAUTH_ID();
			} else if(returnauths != null){
				if (returnauths.size()>0) {
					List<Integer> customerrefundList = new ArrayList<Integer>();
					for (int i=0; i<returnauths.size(); i++) {
						customerrefundList.add(Integer.parseInt(returnauths.get(i).getCUSTOMER_ID().toString()));
					}
					JSONArray customerrefundObject = new JSONArray(AccountService.POST("customerrefund/ids", "{customers: "+customerrefundList+"}", apiRequest.getREQUEST_OUTPUT()));
					
				List<Integer> customerList = new ArrayList<Integer>();
				for (int i=0; i<returnauths.size(); i++) {
					customerList.add(Integer.parseInt(returnauths.get(i).getCUSTOMER_ID().toString()));
				}
				JSONArray customerObject = new JSONArray(AccountService.POST("customer/ids", "{customers: "+customerList+"}", apiRequest.getREQUEST_OUTPUT()));
				
				List<Integer> productList = new ArrayList<Integer>();
				for (int i=0; i<returnauths.size(); i++) {
					productList.add(Integer.parseInt(returnauths.get(i).getPRODUCT_ID().toString()));
				}
				JSONArray productObject = new JSONArray(AccountService.POST("product/ids", "{products: "+productList+"}", apiRequest.getREQUEST_OUTPUT()));
				
				List<Integer> currencyList = new ArrayList<Integer>();
				for (int i=0; i<returnauths.size(); i++) {
					currencyList.add(Integer.parseInt(returnauths.get(i).getCURRENCY_ID().toString()));
				}
				JSONArray currencyObject = new JSONArray(AccountService.POST("currency/ids", "{currencies: "+currencyList+"}", apiRequest.getREQUEST_OUTPUT()));
				
				List<Integer> returnstatusList = new ArrayList<Integer>();
				for (int i=0; i<returnauths.size(); i++) {
					returnstatusList.add(Integer.parseInt(returnauths.get(i).getRETURNSTATUS_ID().toString()));
				}
				JSONArray returnstatusObject = new JSONArray(AccountService.POST("returnstatus/ids", "{returnstatuss: "+returnstatusList+"}", apiRequest.getREQUEST_OUTPUT()));
				
				List<Integer> saleordertypeList = new ArrayList<Integer>();
				for (int i=0; i<returnauths.size(); i++) {
					saleordertypeList.add(Integer.parseInt(returnauths.get(i).getSALEORDERTYPE_ID().toString()));
				}
				JSONArray saleordertypeObject = new JSONArray(AccountService.POST("saleordertype/ids", "{saleordertypes: "+saleordertypeList+"}", apiRequest.getREQUEST_OUTPUT()));
				
				for (int i=0; i<returnauths.size(); i++) {
					for (int j=0; j<customerrefundObject.length(); j++) {
						JSONObject customerrefund = productObject.getJSONObject(j);
						if(returnauths.get(i).getCUSTOMERREFUND_ID() == customerrefund.getLong("customerrefund_ID") ) {
							returnauths.get(i).setCUSTOMERREFUND_DETAIL(customerrefund.toString());
						}
					}
					
				for (int j=0; j<customerObject.length(); j++) {
					JSONObject customer = customerObject.getJSONObject(j);
					if(returnauths.get(i).getCUSTOMER_ID() == customer.getLong("customer_ID") ) {
						returnauths.get(i).setCUSTOMER_DETAIL(customer.toString());
					}
				}
				
				for (int j=0; j<productObject.length(); j++) {
					JSONObject product = productObject.getJSONObject(j);
					if(returnauths.get(i).getPRODUCT_ID() == product.getLong("product_ID") ) {
						returnauths.get(i).setPRODUCT_DETAIL(product.toString());
					}
				}
				
				for (int j=0; j<currencyObject.length(); j++) {
					JSONObject currency = currencyObject.getJSONObject(j);
					if(returnauths.get(i).getCURRENCY_ID() == currency.getLong("currency_ID") ) {
						returnauths.get(i).setCURRENCY_DETAIL(currency.toString());
					}
				}
				
				for (int j=0; j<returnstatusObject.length(); j++) {
					JSONObject returnstatus = productObject.getJSONObject(j);
					if(returnauths.get(i).getRETURNSTATUS_ID() == returnstatus.getLong("returnstatus_ID") ) {
						returnauths.get(i).setRETURNSTATUS_DETAIL(returnstatus.toString());
					}
				}
				
				for (int j=0; j<saleordertypeObject.length(); j++) {
					JSONObject saleordertype = saleordertypeObject.getJSONObject(j);
					if(returnauths.get(i).getSALEORDERTYPE_ID() == saleordertype.getLong("saleordertype_ID") ) {
						returnauths.get(i).setSALEORDERTYPE_DETAIL(saleordertype.toString());
					}
				}
	      }
		}
				apiRequest.setREQUEST_OUTPUT(mapper.writeValueAsString(returnauths));
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
			tbldatalogrepository.saveAndFlush(tableDataLogs.TableSaveDataLog(returnauthID, apiRequest.getDATABASETABLE_ID(), apiRequest.getREQUEST_ID(), apiRequest.getREQUEST_OUTPUT()));
		
		log.info("Output: " + apiRequest.getREQUEST_OUTPUT());
		log.info("--------------------------------------------------------");

		return apiRequest;
	}
}
