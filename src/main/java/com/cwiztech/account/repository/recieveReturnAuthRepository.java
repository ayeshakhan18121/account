package com.cwiztech.account.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cwiztech.account.model.RecieveReturnAuth;

public interface recieveReturnAuthRepository extends JpaRepository<RecieveReturnAuth, Long> {

	@Query(value = "select * from TBLRECIEVERETURNAUTH where ISACTIVE='Y'", nativeQuery = true)
	public List<RecieveReturnAuth> findActive();
	
	@Query(value = "select * from TBLRECIEVERETURNAUTH "
			+ "where RECIEVERETURNAUTH_ID in (:ids) "
			+ "", nativeQuery = true)
	public List<RecieveReturnAuth> findByIDs(@Param("ids") List<Integer> ids);

	@Query(value = "select * from TBLRECIEVERETURNAUTH "
			+ "where RECIEVERETURNAUTH_ID not in (:ids) "
			+ "", nativeQuery = true)
	public List<RecieveReturnAuth> findByNotInIDs(@Param("ids") List<Integer> ids);	

	@Query(value = "select * from TBLRECIEVERETURNAUTH "
			+ "where (RECIEVERETURNAUTH_AMOUNT like ?1 or  RECIEVERETURNAUTH_DATE like ?1 or ISRECIEVED ?1) "
			+ "and ISACTIVE='Y'", nativeQuery = true)
	public List<RecieveReturnAuth> findBySearch(String search);

	@Query(value = "select * from TBLRECIEVERETURNAUTH "
			+ "where (RECIEVERETURNAUTH_AMOUNT like ?1 or  RECIEVERETURNAUTH_DATE like ?1 or ISRECIEVED ?1 ) "
			, nativeQuery = true)
	public List<RecieveReturnAuth> findAllBySearch(String search);
	
	@Query(value = "select * from TBLRECIEVERETURNAUTH " 
			+ "where RETURNAUTH_ID LIKE CASE WHEN ?1 = 0 THEN RETURNAUTH_ID ELSE ?1 END "
			+ "where CURRENCY_ID LIKE CASE WHEN ?1 = 0 THEN CURRENCY_ID ELSE ?1 END "
			+ "and ISACTIVE='Y'", nativeQuery = true)
	List<RecieveReturnAuth> findByAdvancedSearch(Long recieveReturnAuthparent_ID, Long CURRENCY_ID);

	@Query(value = "select * from TBLRECIEVERETURNAUTH " 
			+ "where RETURNAUTH_ID LIKE CASE WHEN ?1 = 0 THEN RETURNAUTH_ID ELSE ?1 END "
			+ "where CURRENCY_ID LIKE CASE WHEN ?1 = 0 THEN CURRENCY_ID ELSE ?1 END "
			, nativeQuery = true)
	List<RecieveReturnAuth> findAllByAdvancedSearch(Long recieveReturnAuthparent_ID, Long CURRENCY_ID);
	
}

