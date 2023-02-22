package com.cwiztech.account.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cwiztech.account.model.CreditCardTransaction;


public interface creditCardTransactionRepository extends JpaRepository<CreditCardTransaction, Long> {

	@Query(value = "select * from TBLCREDITCARDTRANSACTION where ISACTIVE='Y'", nativeQuery = true)
	public List<CreditCardTransaction> findActive();
	
	@Query(value = "select * from TBLCREDITCARDTRANSACTION "
			+ "where CREDITCARDTRANSACTION_ID in (:ids) "
			+ "", nativeQuery = true)
	public List<CreditCardTransaction> findByIDs(@Param("ids") List<Integer> ids);

	@Query(value = "select * from TBLCREDITCARDTRANSACTION "
			+ "where CREDITCARDTRANSACTION_ID not in (:ids) "
			+ "", nativeQuery = true)
	public List<CreditCardTransaction> findByNotInIDs(@Param("ids") List<Integer> ids);	

	@Query(value = "select * from TBLCREDITCARDTRANSACTION "
			+ "where (TRANSACTION_DATE like ?1 or  TRANSACTION_AMOUNT like ?1 or TRANSACTION_STATUS like ?1 or NAME_ONCARD like ?1 or CARD_NUMBER like ?1 or AUTHCODE like ?1) "
			+ "and ISACTIVE='Y'", nativeQuery = true)
	public List<CreditCardTransaction> findBySearch(String search);

	@Query(value = "select * from TBLCREDITCARDTRANSACTION "
			+ "where (TRANSACTION_DATE like ?1 or  TRANSACTION_AMOUNT like ?1 or TRANSACTION_STATUS like ?1 or NAME_ONCARD like ?1 or CARD_NUMBER like ?1 or AUTHCODE like ?1) "
			, nativeQuery = true)
	public List<CreditCardTransaction> findAllBySearch(String search);
	
	@Query(value = "select * from TBLCREDITCARDTRANSACTION " 
			+ "where CUSTOMER_ID LIKE CASE WHEN ?1 = 0 THEN CUSTOMER_ID ELSE ?1 END "
			+ "and CARDTYPE_ID LIKE CASE WHEN ?1 = 0 THEN CARDTYPE_ID ELSE ?1 END "
			+ "and ISACTIVE='Y'", nativeQuery = true)
	List<CreditCardTransaction> findByAdvancedSearch(Long customer_ID, Long cardtype_ID);

	@Query(value = "select * from TBLCREDITCARDTRANSACTION " 
			+ "where CUSTOMER_ID LIKE CASE WHEN ?1 = 0 THEN CUSTOMER_ID ELSE ?1 END "
			+ "and CARDTYPE_ID LIKE CASE WHEN ?1 = 0 THEN CARDTYPE_ID ELSE ?1 END "
			, nativeQuery = true)
	List<CreditCardTransaction> findAllByAdvancedSearch(Long customer_ID, Long cardtype_ID);
	
}
