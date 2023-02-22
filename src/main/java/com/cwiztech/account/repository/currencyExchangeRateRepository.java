package com.cwiztech.account.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cwiztech.account.model.CurrencyExchangeRate;

public interface currencyExchangeRateRepository extends JpaRepository<CurrencyExchangeRate, Long> {


	@Query(value = "select * from TBLCURRENCYEXCHANGERATE where ISACTIVE='Y'", nativeQuery = true)
	public List<CurrencyExchangeRate> findActive();
	
	@Query(value = "select * from TBLCURRENCYEXCHANGERATE "
			+ "where CURRENCYEXCHANGERATE_ID in (:ids) "
			+ "", nativeQuery = true)
	public List<CurrencyExchangeRate> findByIDs(@Param("ids") List<Integer> ids);

	@Query(value = "select * from TBLCURRENCYEXCHANGERATE "
			+ "where CURRENCYEXCHANGERATE_ID not in (:ids) "
			+ "", nativeQuery = true)
	public List<CurrencyExchangeRate> findByNotInIDs(@Param("ids") List<Integer> ids);	

	@Query(value = "select * from TBLCURRENCYEXCHANGERATE "
			+ "where (EFFECTIVE_DATE like ?1 ) "
			+ "and ISACTIVE='Y'", nativeQuery = true)
	public List<CurrencyExchangeRate> findBySearch(String search);

	@Query(value = "select * from TBLCURRENCYEXCHANGERATE "
			+ "where (EFFECTIVE_DATE like ?1 ) "
			, nativeQuery = true)
	public List<CurrencyExchangeRate> findAllBySearch(String search);
	
	@Query(value = "select * from TBLCURRENCYEXCHANGERATE " 
			+ "where CURRENCY_ID LIKE CASE WHEN ?1 = 0 THEN CURRENCY_ID ELSE ?1 END "
			+ "and ISACTIVE='Y'", nativeQuery = true)
	List<CurrencyExchangeRate> findByAdvancedSearch(Long currency_ID);

	@Query(value = "select * from TBLCURRENCYEXCHANGERATE " 
			+ "where CURRENCY_ID LIKE CASE WHEN ?1 = 0 THEN CURRENCY_ID ELSE ?1 END "
			, nativeQuery = true)
	List<CurrencyExchangeRate> findAllByAdvancedSearch(Long currency_ID);
	
}
