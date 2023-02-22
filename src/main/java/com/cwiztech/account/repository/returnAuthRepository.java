package com.cwiztech.account.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cwiztech.account.model.ReturnAuth;

public interface returnAuthRepository extends JpaRepository<ReturnAuth, Long> {

	@Query(value = "select * from TBLRETURNAUTH where ISACTIVE='Y'", nativeQuery = true)
	public List<ReturnAuth> findActive();
	
	@Query(value = "select * from TBLRETURNAUTH "
			+ "where RETURNAUTH_ID in (:ids) "
			+ "", nativeQuery = true)
	public List<ReturnAuth> findByIDs(@Param("ids") List<Integer> ids);

	@Query(value = "select * from TBLRETURNAUTH "
			+ "where RETURNAUTH_ID not in (:ids) "
			+ "", nativeQuery = true)
	public List<ReturnAuth> findByNotInIDs(@Param("ids") List<Integer> ids);	

	@Query(value = "select * from TBLRETURNAUTH "
			+ "where (RETURNAUTH_CODE like ?1 or  RETURNAUTH_DATE like ?1 or DELIVERY_DATE like ?1 or EXCHANGE_RATE like ?1 or RATE like ?1 or DISCOUNT like ?1 ) "
			+ "and ISACTIVE='Y'", nativeQuery = true)
	public List<ReturnAuth> findBySearch(String search);

	@Query(value = "select * from TBLRETURNAUTH "
			+ "where (RETURNAUTH_CODE like ?1 or  RETURNAUTH_DATE like ?1 or DELIVERY_DATE like ?1 or EXCHANGE_RATE like ?1 or RATE like ?1 or DISCOUNT like ?1) "
			, nativeQuery = true)
	public List<ReturnAuth> findAllBySearch(String search);
	
	@Query(value = "select * from TBLRETURNAUTH " 
			+ "where CUSTOMERREFUND_ID LIKE CASE WHEN ?1 = 0 THEN CUSTOMERREFUND_ID ELSE ?1 END "
			+ "and CUSTOMER_ID LIKE CASE WHEN ?1 = 0 THEN CUSTOMER_ID ELSE ?1 END "
			+ "and PRODUCT_ID LIKE CASE WHEN ?1 = 0 THEN PRODUCT_ID ELSE ?1 END "
			+ "and CURRENCY_ID LIKE CASE WHEN ?1 = 0 THEN CURRENCY_ID ELSE ?1 END "	
			+ "and RETURNSTATUS_ID LIKE CASE WHEN ?1 = 0 THEN RETURNSTATUS_ID ELSE ?1 END "
			+ "and SALEORDERTYPE_ID LIKE CASE WHEN ?1 = 0 THEN SALEORDERTYPE_ID ELSE ?1 END "
			+ "and ISACTIVE='Y'", nativeQuery = true)
	List<ReturnAuth> findByAdvancedSearch(Long customer_ID, Long product_ID, Long currency_ID, Long returnstatus_ID, Long saleordertype_ID, Long customerrefund_ID);

	@Query(value = "select * from TBLRETURNAUTH " 
			+ "where CUSTOMERREFUND_ID LIKE CASE WHEN ?1 = 0 THEN CUSTOMERREFUND_ID ELSE ?1 END "
			+ "and CUSTOMER_ID LIKE CASE WHEN ?1 = 0 THEN CUSTOMER_ID ELSE ?1 END "
			+ "and PRODUCT_ID LIKE CASE WHEN ?1 = 0 THEN PRODUCT_ID ELSE ?1 END "
			+ "and CURRENCY_ID LIKE CASE WHEN ?1 = 0 THEN CURRENCY_ID ELSE ?1 END "	
			+ "and RETURNSTATUS_ID LIKE CASE WHEN ?1 = 0 THEN RETURNSTATUS_ID ELSE ?1 END "
			+ "and SALEORDERTYPE_ID LIKE CASE WHEN ?1 = 0 THEN SALEORDERTYPE_ID ELSE ?1 END "
			, nativeQuery = true)
	List<ReturnAuth> findAllByAdvancedSearch(Long customer_ID, Long product_ID, Long currency_ID, Long returnstatus_ID, Long saleordertype_ID, Long customerrefund_ID);
	
}
