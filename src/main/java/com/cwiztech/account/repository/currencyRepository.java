package com.cwiztech.account.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cwiztech.account.model.Currency;

public interface currencyRepository extends JpaRepository<Currency, Long> {

	@Query(value = "select * from TBLCURRENCY where ISACTIVE='Y'", nativeQuery = true)
	public List<Currency> findActive();
	
	@Query(value = "select * from TBLCURRENCY "
			+ "where CURRENCY_ID in (:ids) "
			+ "", nativeQuery = true)
	public List<Currency> findByIDs(@Param("ids") List<Integer> ids);

	@Query(value = "select * from TBLCURRENCY "
			+ "where CURRENCY_ID not in (:ids) "
			+ "", nativeQuery = true)
	public List<Currency> findByNotInIDs(@Param("ids") List<Integer> ids);	

	@Query(value = "select * from TBLCURRENCY "
			+ "where (ISO_CODE like ?1 or EXCHANGE_RATE like ?1 or CURRENCY_FORMAT like ?1 or CURRENCY_SYMBOL like ?1) "
			+ "and ISACTIVE='Y'", nativeQuery = true)
	public List<Currency> findBySearch(String search);

	@Query(value = "select * from TBLCURRENCY "
			+ "where (ISO_CODE like ?1 or EXCHANGE_RATE like ?1 or CURRENCY_FORMAT like ?1 or CURRENCY_SYMBOL like ?1) "
			, nativeQuery = true)
	public List<Currency> findAllBySearch(String search);
	
	@Query(value = "select * from TBLCURRENCY " 
			+ "where COUNTRY_ID LIKE CASE WHEN ?1 = 0 THEN COUNTRY_ID ELSE ?1 END "
			+ "and CURRENCYSYMBOLPLACEMENT_ID LIKE CASE WHEN ?1 = 0 THEN CURRENCYSYMBOLPLACEMENT_ID ELSE ?1 END "
			+ "and ISACTIVE='Y'"
			, nativeQuery = true)
	List<Currency> findByAdvancedSearch(Long country_ID, Long currencysymbolplacement_ID);

	@Query(value = "select * from TBLCURRENCY " 
			+ "where COUNTRY_ID LIKE CASE WHEN ?1 = 0 THEN COUNTRY_ID ELSE ?1 END "
			+ "and CURRENCYSYMBOLPLACEMENT_ID LIKE CASE WHEN ?1 = 0 THEN CURRENCYSYMBOLPLACEMENT_ID ELSE ?1 END "
			, nativeQuery = true)
	List<Currency> findAllByAdvancedSearch(Long country_ID, Long currencysymbolplacement_ID);
	
}
