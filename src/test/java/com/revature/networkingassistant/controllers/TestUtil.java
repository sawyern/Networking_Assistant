package com.revature.networkingassistant.controllers;

import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.beans.Attendant.Attendant;
import com.revature.networkingassistant.beans.Event.Event;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.repositories.*;
import com.revature.networkingassistant.services.AccountServices.LoginService;
import com.revature.networkingassistant.services.AccountServices.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestUtil {

    private AccountRepo accountRepo;
    private SessionTokenRepo sessionTokenRepo;
    private EventRepo eventRepo;
    private AttendantRepo attendantRepo;
    private AnnouncementRepo announcementRepo;
    private InviteRepo inviteRepo;
    private StarredAccountRepo starredAccountRepo;

    public TestUtil(){}

    @Autowired
    public TestUtil(AccountRepo accountRepo,
                    SessionTokenRepo sessionTokenRepo,
                    EventRepo eventRepo,
                    AttendantRepo attendantRepo,
                    AnnouncementRepo announcementRepo,
                    InviteRepo inviteRepo,
                    StarredAccountRepo starredAccountRepo
    ) {
        this.accountRepo = accountRepo;
        this.sessionTokenRepo = sessionTokenRepo;
        this.eventRepo = eventRepo;
        this.attendantRepo = attendantRepo;
        this.announcementRepo = announcementRepo;
        this.inviteRepo = inviteRepo;
        this.starredAccountRepo = starredAccountRepo;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public SessionToken loginTestUser(Account account) {
        SessionToken sessionToken = new SessionToken();

        sessionToken.setAccountId(account.getId());
        return sessionTokenRepo.save(sessionToken);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
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

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void removeTestAccount(Account account) {
        accountRepo.deleteAccountByEmail(account.getEmail());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void removeTestEvent(Event event) {
        if (eventRepo.existsByName(event.getName()))
            eventRepo.deleteById(eventRepo.findByName(event.getName()).getId());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void removeAttendant(Attendant attendant) {
        //attendantRepo.deleteById(eventRepo.findByName(event.getName()).getId());
    }

}
