package com.cwiztech.account.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cwiztech.account.model.ApprovalReturnAuth;

public interface approvalReturnAuthRepository extends JpaRepository<ApprovalReturnAuth, Long> {

	@Query(value = "select * from TBLAPPROVALRETURNAUTH where ISACTIVE='Y'", nativeQuery = true)
	public List<ApprovalReturnAuth> findActive();
	
	@Query(value = "select * from TBLAPPROVALRETURNAUTH "
			+ "where APPROVALRETURNAUTH_ID in (:ids) "
			+ "", nativeQuery = true)
	public List<ApprovalReturnAuth> findByIDs(@Param("ids") List<Integer> ids);

	@Query(value = "select * from TBLAPPROVALRETURNAUTH "
			+ "where APPROVALRETURNAUTH_ID not in (:ids) "
			+ "", nativeQuery = true)
	public List<ApprovalReturnAuth> findByNotInIDs(@Param("ids") List<Integer> ids);	

	@Query(value = "select * from TBLAPPROVALRETURNAUTH "
			+ "where (APPROVALRETURNAUTH_AMOUNT like ?1 or APPROVALRETURNAUTH_DATE like ?1 or ISAPPROVED like ?1) "
			+ "and ISACTIVE='Y'", nativeQuery = true)
	public List<ApprovalReturnAuth> findBySearch(String search);

	@Query(value = "select * from TBLAPPROVALRETURNAUTH "
			+ "where (APPROVALRETURNAUTH_AMOUNT like ?1 or APPROVALRETURNAUTH_DATE like ?1 or ISAPPROVED like ?1) "
			, nativeQuery = true)
	public List<ApprovalReturnAuth> findAllBySearch(String search);
	
	@Query(value = "select * from TBLAPPROVALRETURNAUTH " 
			+ "where RETURNAUTH_ID LIKE CASE WHEN ?1 = 0 THEN RETURNAUTH_ID ELSE ?1 END "
			+ "and CURRENCY_ID LIKE CASE WHEN ?1 = 0 THEN CURRENCY_ID ELSE ?1 END "
			+ "and ISACTIVE='Y'"
			, nativeQuery = true)
	List<ApprovalReturnAuth> findByAdvancedSearch(Long returnauth_ID, Long currency_ID);

	@Query(value = "select * from TBLAPPROVALRETURNAUTH " 
			+ "where RETURNAUTH_ID LIKE CASE WHEN ?1 = 0 THEN RETURNAUTH_ID ELSE ?1 END "
			+ "and CURRENCY_ID LIKE CASE WHEN ?1 = 0 THEN CURRENCY_ID ELSE ?1 END "
			, nativeQuery = true)
	List<ApprovalReturnAuth> findAllByAdvancedSearch(Long returnauth_ID, Long currency_ID);
	
}

