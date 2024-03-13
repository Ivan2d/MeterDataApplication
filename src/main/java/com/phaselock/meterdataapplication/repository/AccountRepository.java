package com.phaselock.meterdataapplication.repository;

import com.phaselock.meterdataapplication.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

}
