package com.uniovi.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.uniovi.entities.Purchase;
import com.uniovi.entities.User;

public interface PurchasesRepository extends CrudRepository<Purchase, Long> {
	@Query("SELECT r FROM Purchase r WHERE r.user = ?1 ORDER BY r.id ASC ")
	List<Purchase> findAllByUser(User user);

	@Transactional
	@Modifying
	@Query("DELETE FROM Purchase r WHERE r.user.id = ?1")
	void deleteByUserId(Long id);

}
