package com.cwiztech.account.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cwiztech.account.model.ExpenseCategory;


public interface expenseCategoryRepository extends JpaRepository<ExpenseCategory, Long> {

	@Query(value = "select * from TBLEXPENSECATEGORY where ISACTIVE='Y'", nativeQuery = true)
	public List<ExpenseCategory> findActive();
	
	@Query(value = "select * from TBLEXPENSECATEGORY "
			+ "where ACCOUNT_ID in (:ids) "
			+ "", nativeQuery = true)
	public List<ExpenseCategory> findByIDs(@Param("ids") List<Integer> ids);

	@Query(value = "select * from TBLEXPENSECATEGORY "
			+ "where ACCOUNT_ID not in (:ids) "
			+ "", nativeQuery = true)
	public List<ExpenseCategory> findByNotInIDs(@Param("ids") List<Integer> ids);	

	@Query(value = "select * from TBLEXPENSECATEGORY "
			+ "where (EXPENSECATEGORY_CODE like ?1 or  EXPENSECATEGORY_DESCRIPTION like ?1 or EXPENSECATEGORY_NAME like ?1 ) "
			+ "and ISACTIVE='Y'", nativeQuery = true)
	public List<ExpenseCategory> findBySearch(String search);

	@Query(value = "select * from TBLEXPENSECATEGORY "
			+ "where (EXPENSECATEGORY_CODE like ?1 or  EXPENSECATEGORY_DESCRIPTION like ?1 or EXPENSECATEGORY_NAME like ?1 ) "
			, nativeQuery = true)
	public List<ExpenseCategory> findAllBySearch(String search);
	
	@Query(value = "select * from TBLEXPENSECATEGORY " 
			+ "where EXPENSECATEGORY_ID LIKE CASE WHEN ?1 = 0 THEN EXPENSECATEGORY_ID ELSE ?1 END "
			+ "and ISACTIVE='Y'", nativeQuery = true)
	List<ExpenseCategory> findByAdvancedSearch(Long expenseaategory_ID);

	@Query(value = "select * from TBLEXPENSECATEGORY " 
			+ "where EXPENSECATEGORY_ID LIKE CASE WHEN ?1 = 0 THEN EXPENSECATEGORY_ID ELSE ?1 END "
			, nativeQuery = true)
	List<ExpenseCategory> findAllByAdvancedSearch(Long expenseaategory_ID);
	
}
