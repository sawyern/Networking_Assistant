package com.revature.networkingassistant.repositories;

import com.revature.networkingassistant.beans.StarredAccount;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface StarredAccountRepo extends CrudRepository<StarredAccount, String> {

    ArrayList<StarredAccount> findByOwnerId(int ownerId);
}