package com.revature.networkingassistant.repositories;

import com.revature.networkingassistant.beans.SessionToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface SessionTokenRepo extends CrudRepository<SessionToken, String> {
    SessionToken findByAccountId(int id);
    boolean existsByAccountId(int id);
}
