package com.cwiztech.account.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cwiztech.account.model.CustomerRefund;


public interface customerRefundRepository extends JpaRepository<CustomerRefund, Long> {
	

	@Query(value = "select * from TBLCUSTOMERREFUND where ISACTIVE='Y'", nativeQuery = true)
	public List<CustomerRefund> findActive();
	
	@Query(value = "select * from TBLCUSTOMERREFUND "
			+ "where CUSTOMERREFUND_ID in (:ids) "
			+ "", nativeQuery = true)
	public List<CustomerRefund> findByIDs(@Param("ids") List<Integer> ids);

	@Query(value = "select * from TBLCUSTOMERREFUND "
			+ "where CUSTOMERREFUND_ID not in (:ids) "
			+ "", nativeQuery = true)
	public List<CustomerRefund> findByNotInIDs(@Param("ids") List<Integer> ids);	

	@Query(value = "select * from TBLCUSTOMERREFUND "
			+ "where (CUSTOMERREFUND_CODE like ?1 or  CUSTOMERREFUND_DATE like ?1 or CUSTOMER_BALANCE like ?1 or CUSTOMERREFUND_AMOUNT like ?1 or EXCHANGE_RATE like ?1 or CHECK_NUMBER like ?1 or CREDITCARD_NUMBER like ?1 or EXPIRE_DATE like ?1 or NAME_ONCARD like ?1 or CARD_STREET like ?1 or CARD_ZIPCODE like ?1 or ISCCAPPROVED like ?1) "
			+ "and ISACTIVE='Y'", nativeQuery = true)
	public List<CustomerRefund> findBySearch(String search);

	@Query(value = "select * from TBLCUSTOMERREFUND "
			+ "where (CUSTOMERREFUND_CODE like ?1 or  CUSTOMERREFUND_DATE like ?1 or CUSTOMER_BALANCE like ?1 or CUSTOMERREFUND_AMOUNT like ?1 or EXCHANGE_RATE like ?1 or CHECK_NUMBER like ?1 or CREDITCARD_NUMBER like ?1 or EXPIRE_DATE like ?1 or NAME_ONCARD like ?1 or CARD_STREET like ?1 or CARD_ZIPCODE like ?1 or ISCCAPPROVED like ?1) "
			, nativeQuery = true)
	public List<CustomerRefund> findAllBySearch(String search);
	
	@Query(value = "select * from TBLCUSTOMERREFUND " 
			+ "where CUSTOMER_ID LIKE CASE WHEN ?1 = 0 THEN CUSTOMER_ID ELSE ?1 END "
			+ "and ACCOUNT_ID LIKE CASE WHEN ?1 = 0 THEN ACCOUNT_ID ELSE ?1 END "
			+ "and CURRENCY_ID LIKE CASE WHEN ?1 = 0 THEN CURRENCY_ID ELSE ?1 END "
			+ "and POSTINGPERIOD_ID LIKE CASE WHEN ?1 = 0 THEN POSTINGPERIOD_ID ELSE ?1 END "
			+ "and REFUNDMETHOD_ID LIKE CASE WHEN ?1 = 0 THEN REFUNDMETHOD_ID ELSE ?1 END "
			+ "and ISACTIVE='Y'", nativeQuery = true)
	List<CustomerRefund> findByAdvancedSearch(Long customer_ID, Long account_ID, Long currency_ID, Long postingperiod_ID, Long refundmethod_ID);

	@Query(value = "select * from TBLCUSTOMERREFUND " 
			+ "where CUSTOMER_ID LIKE CASE WHEN ?1 = 0 THEN CUSTOMER_ID ELSE ?1 END "
			+ "and ACCOUNT_ID LIKE CASE WHEN ?1 = 0 THEN ACCOUNT_ID ELSE ?1 END "
			+ "and CURRENCY_ID LIKE CASE WHEN ?1 = 0 THEN CURRENCY_ID ELSE ?1 END "
			+ "and POSTINGPERIOD_ID LIKE CASE WHEN ?1 = 0 THEN POSTINGPERIOD_ID ELSE ?1 END "
			+ "and REFUNDMETHOD_ID LIKE CASE WHEN ?1 = 0 THEN REFUNDMETHOD_ID ELSE ?1 END "
			, nativeQuery = true)
	List<CustomerRefund> findAllByAdvancedSearch(Long customer_ID, Long account_ID, Long currency_ID, Long postingperiod_ID, Long refundmethod_ID);
	

}
