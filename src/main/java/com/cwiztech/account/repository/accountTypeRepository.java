package com.cwiztech.account.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cwiztech.account.model.AccountType;

public interface accountTypeRepository extends JpaRepository<AccountType, Long> {

	@Query(value = "select * from TBLACCOUNTTYPE where ISACTIVE='Y'", nativeQuery = true)
	public List<AccountType> findActive();
	
	@Query(value = "select * from TBLACCOUNTTYPE "
			+ "where ACCOUNTTYPE_ID in (:ids) "
			+ "", nativeQuery = true)
	public List<AccountType> findByIDs(@Param("ids") List<Integer> ids);

	@Query(value = "select * from TBLACCOUNTTYPE "
			+ "where ACCOUNTTYPE_ID not in (:ids) "
			+ "", nativeQuery = true)
	public List<AccountType> findByNotInIDs(@Param("ids") List<Integer> ids);	

	@Query(value = "select * from TBLACCOUNTTYPE "
			+ "where (ACCOUNTTYPE_CODE like ?1 or ACCOUNTTYPE_NAME like ?1 or ACCOUNTTYPE_DESCRIPTION like ?1) "
			+ "and ISACTIVE='Y'", nativeQuery = true)
	public List<AccountType> findBySearch(String search);

	@Query(value = "select * from TBLACCOUNTTYPE "
			+ "where (ACCOUNTTYPE_CODE like ?1 or ACCOUNTTYPE_NAME like ?1 or ACCOUNTTYPE_DESCRIPTION like ?1) "
			, nativeQuery = true)
	public List<AccountType> findAllBySearch(String search);
	
	@Query(value = "select * from TBLACCOUNTTYPE " 
			+ "where ACCOUNTTYPE_ID LIKE CASE WHEN ?1 = 0 THEN ACCOUNTTYPE_ID ELSE ?1 END "
			+ "and ISACTIVE='Y'", nativeQuery = true)
	List<AccountType> findByAdvancedSearch(Long accounttype_ID);

	@Query(value = "select * from TBLACCOUNTTYPE " 
			+ "where ACCOUNTTYPE_ID LIKE CASE WHEN ?1 = 0 THEN ACCOUNTTYPE_ID ELSE ?1 END "
			, nativeQuery = true)
	List<AccountType> findAllByAdvancedSearch(Long accounttype_ID);
	
}
