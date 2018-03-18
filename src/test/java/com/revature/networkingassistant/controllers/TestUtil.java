package com.revature.networkingassistant.controllers;

import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.repositories.AccountRepo;
import com.revature.networkingassistant.repositories.SessionTokenRepo;
import com.revature.networkingassistant.services.AccountServices.LoginService;
import com.revature.networkingassistant.services.AccountServices.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestUtil {

    private AccountRepo accountRepo;
    private SessionTokenRepo sessionTokenRepo;

    public TestUtil(){}

    @Autowired
    public TestUtil(AccountRepo accountRepo, SessionTokenRepo sessionTokenRepo) {
        this.accountRepo = accountRepo;
        this.sessionTokenRepo = sessionTokenRepo;
    }

    @Transactional
    public SessionToken loginTestUser(Account account) {
        SessionToken sessionToken = new SessionToken();

        String password = account.getPasswordHash();
        account.setPasswordHash(RegisterService.hashPassword(account.getPasswordHash()));
        account = accountRepo.save(account);
        account.setPasswordHash(password);

        sessionToken.setAccountId(account.getId());
        return sessionTokenRepo.save(sessionToken);
    }

    @Transactional
    public Account createTestAccount() {
        Account account = new Account();
        account.setEmail("test@test.com");
        account.setPasswordHash("password");
        account.setFirstName("First");
        account.setLastName("Last");
        account.setBackground("Test background info.");
        account.setPhone("808-222-2222");
        account.setZipCode("96813");
        account.setAttachment(new byte[0]);

        account.setPasswordHash(RegisterService.hashPassword(account.getPasswordHash()));
        account = accountRepo.save(account);
        return account;
    }

    @Transactional
    public void removeTestAccount(Account account) {
        if (accountRepo.existsById(account.getId()))
            accountRepo.deleteAccountById(account.getId());
    }
}
