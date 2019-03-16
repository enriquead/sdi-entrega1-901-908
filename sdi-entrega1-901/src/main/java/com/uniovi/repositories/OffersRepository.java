package com.uniovi.repositories;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.uniovi.entities.Offer;

@Transactional
public interface OffersRepository extends CrudRepository<Offer, Long>{
	
	@Query("SELECT o FROM Offer o WHERE LOWER(o.title) LIKE LOWER(?1) ")
			Page<Offer> searchByTitle(Pageable pageable,String seachtext);
	
	@Query("SELECT o FROM Offer o WHERE o.promoted=true")
	List<Offer> findPromoted();
	
	@Modifying
	@Transactional
	@Query("UPDATE Offer SET promoted = ?1 WHERE id = ?2")
	void updatePromoted(Boolean promoted, Long id);
	
	@Modifying
	@Transactional
	@Query("UPDATE User SET money = ?1 WHERE id = ?2")
	void updatePromotedMoney(double money, Long id);

	Page<Offer> findAll(Pageable pageable);
 
}
