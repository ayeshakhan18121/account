package com.cwiztech.account.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cwiztech.account.model.Account;

public interface accountRepository extends JpaRepository<Account, Long> {

	@Query(value = "select * from TBLACCOUNT where ISACTIVE='Y'", nativeQuery = true)
	public List<Account> findActive();
	
	@Query(value = "select * from TBLACCOUNT "
			+ "where ACCOUNT_ID in (:ids) "
			+ "", nativeQuery = true)
	public List<Account> findByIDs(@Param("ids") List<Integer> ids);

	@Query(value = "select * from TBLACCOUNT "
			+ "where ACCOUNT_ID not in (:ids) "
			+ "", nativeQuery = true)
	public List<Account> findByNotInIDs(@Param("ids") List<Integer> ids);	

	@Query(value = "select * from TBLACCOUNT "
			+ "where (ACCOUNT_CODE like ?1 or  ACCOUNT_DATE like ?1 or ACCOUNT_NAME like ?1 or ACCOUNT_DESCRIPTION like ?1) "
			+ "and ISACTIVE='Y'", nativeQuery = true)
	public List<Account> findBySearch(String search);

	@Query(value = "select * from TBLACCOUNT "
			+ "where (ACCOUNT_CODE like ?1 or  ACCOUNT_DATE like ?1 or ACCOUNT_NAME like ?1 or ACCOUNT_DESCRIPTION like ?1) "
			, nativeQuery = true)
	public List<Account> findAllBySearch(String search);
	
	@Query(value = "select * from TBLACCOUNT " 
			+ "where CASEFLOWRATETYPE_ID LIKE CASE WHEN ?1 = 0 THEN CASEFLOWRATETYPE_ID ELSE ?1 END "
			+ "and ACCOUNTPARENT_ID LIKE CASE WHEN ?1 = 0 THEN ACCOUNTPARENT_ID ELSE ?1 END "
			+ "and ACCOUNTTYPE_ID LIKE CASE WHEN ?1 = 0 THEN ACCOUNTTYPE_ID ELSE ?1 END "
			+ "and GENERALRATETYPE_ID LIKE CASE WHEN ?1 = 0 THEN GENERALRATETYPE_ID ELSE ?1 END "
			+ "and ISACTIVE='Y'", nativeQuery = true)
	List<Account> findByAdvancedSearch(Long accountparent_ID, Long generalratetype_ID, Long accounttype_ID, Long classflowratetype_ID);

	@Query(value = "select * from TBLACCOUNT " 
			+ "where CASEFLOWRATETYPE_ID LIKE CASE WHEN ?1 = 0 THEN CASEFLOWRATETYPE_ID ELSE ?1 END "
			+ "and ACCOUNTPARENT_ID LIKE CASE WHEN ?1 = 0 THEN ACCOUNTPARENT_ID ELSE ?1 END "
			+ "and ACCOUNTTYPE_ID LIKE CASE WHEN ?1 = 0 THEN ACCOUNTTYPE_ID ELSE ?1 END "
			+ "and GENERALRATETYPE_ID LIKE CASE WHEN ?1 = 0 THEN GENERALRATETYPE_ID ELSE ?1 END "
			, nativeQuery = true)
	List<Account> findAllByAdvancedSearch(Long accountparent_ID, Long generalratetype_ID, Long accounttype_ID, Long classflowratetype_ID);
	
}
