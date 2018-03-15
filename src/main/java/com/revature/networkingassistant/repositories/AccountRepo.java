package com.revature.networkingassistant.repositories;

import com.revature.networkingassistant.beans.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface AccountRepo extends CrudRepository<Account, Integer> {
    Account findByEmail(String username);
    boolean existsByEmail(String email);
}
