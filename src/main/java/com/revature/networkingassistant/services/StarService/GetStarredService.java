package com.revature.networkingassistant.services.StarService;

import com.revature.networkingassistant.beans.StarredAccount;
import com.revature.networkingassistant.repositories.AccountRepo;
import com.revature.networkingassistant.repositories.StarredAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class GetStarredService {

    @Autowired
    private StarredAccountRepo starredAccountRepo;

    @Autowired
    private AccountRepo accountRepo;

    @Transactional
    public ResponseEntity<StarredAccount> starAccount(int ownerId, int starredId) {
        StarredAccount starredAccount = null;
        if (accountRepo.existsById(ownerId) && accountRepo.existsById(starredId)) {
            starredAccount = new StarredAccount(ownerId, starredId);
            starredAccountRepo.save(starredAccount);
            return new ResponseEntity<>(starredAccount, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(starredAccount, HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseEntity<ArrayList<StarredAccount>> getStarredAcounts(int id) {
        ArrayList<StarredAccount> starredAccounts = starredAccountRepo.findByOwnerId(id);
        if (starredAccounts.size() > 0) {
            return new ResponseEntity<>(starredAccounts, HttpStatus.OK);
        } else return new ResponseEntity<>(starredAccounts, HttpStatus.NO_CONTENT);
    }
}
