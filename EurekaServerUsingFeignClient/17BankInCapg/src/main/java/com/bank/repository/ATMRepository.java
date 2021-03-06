package com.bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.model.ATM;
import com.bank.model.Account;

/**
 * @author ram
 * this interface provide various methods of JPAREPOSITORY
 * to deal with operations
 *
 */
public interface ATMRepository extends JpaRepository<ATM, Long>{

	Optional<ATM> findByaTMId(Long  aTMId);	
	
}
