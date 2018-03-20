package com.revature.networkingassistant.repositories;

import com.revature.networkingassistant.beans.StarredAccount;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StarredAccountRepo extends CrudRepository<StarredAccount, String> {

    List<StarredAccount> getByIdStartingWith(String ownerId);
}

