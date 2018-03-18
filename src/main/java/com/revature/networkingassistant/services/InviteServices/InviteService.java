package com.revature.networkingassistant.services.InviteServices;

import com.revature.networkingassistant.beans.Invite;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.repositories.AccountRepo;
import com.revature.networkingassistant.repositories.EventRepo;
import com.revature.networkingassistant.repositories.InviteRepo;
import com.revature.networkingassistant.repositories.SessionTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class InviteService {

    private SessionTokenRepo sessionTokenRepo;
    private InviteRepo inviteRepo;
    private AccountRepo accountRepo;
    private EventRepo eventRepo;

    public InviteService() {}

    @Autowired
    public InviteService(SessionTokenRepo sessionTokenRepo, InviteRepo inviteRepo, AccountRepo accountRepo, EventRepo eventRepo) {
        this.sessionTokenRepo = sessionTokenRepo;
        this.inviteRepo = inviteRepo;
        this.accountRepo = accountRepo;
        this.eventRepo = eventRepo;
    }

    public ResponseEntity<Invite> sendInvite(@RequestBody JsonRequestBody<Invite> requestBody) {
        try {
            SessionToken token = requestBody.getToken();
            Invite invite = requestBody.getObject();
            if (sessionTokenRepo.existsById(token.getId())) {
                if (accountRepo.existsById(invite.getInviter()) && accountRepo.existsById(invite.getInvitee()) && eventRepo.existsById(invite.getEventId())) {
                    return new ResponseEntity<>(inviteRepo.save(invite), HttpStatus.OK);
                }
                return new ResponseEntity<>((Invite) null, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>((Invite) null, HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>((Invite) null, HttpStatus.BAD_GATEWAY);
        }
    }
}
