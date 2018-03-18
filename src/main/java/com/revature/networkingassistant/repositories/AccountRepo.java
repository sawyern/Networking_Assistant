package com.revature.networkingassistant.repositories;

import com.revature.networkingassistant.beans.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface AccountRepo extends CrudRepository<Account, Integer> {
    Account findByEmail(String username);
    Account findByLastName(String lastName);
    Account findByFirstName(String firstName);

    void deleteAccountById(int id);

    boolean existsByEmail(String email);
    ArrayList<Account> findAccountsByEmailContaining(String partialEmail);
    ArrayList<Account> findAccountsByFirstNameContaining(String partialFirst);
    ArrayList<Account> findAccountsByLastNameContaining(String partialFirst);
}
