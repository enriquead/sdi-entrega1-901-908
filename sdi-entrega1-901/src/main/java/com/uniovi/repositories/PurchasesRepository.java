package com.uniovi.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.uniovi.entities.Purchase;
import com.uniovi.entities.User;

public interface PurchasesRepository extends CrudRepository<Purchase, Long> {
	@Query("SELECT r FROM Purchase r WHERE r.user = ?1 ORDER BY r.id ASC ")
	List<Purchase> findAllByUser(User user);

}
