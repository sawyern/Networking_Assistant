package com.revature.networkingassistant.services.InviteServices;

import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.beans.Attendant.Attendant;
import com.revature.networkingassistant.beans.Attendant.Role;
import com.revature.networkingassistant.beans.Invite;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InviteService {

    private SessionTokenRepo sessionTokenRepo;
    private InviteRepo inviteRepo;
    private AccountRepo accountRepo;
    private EventRepo eventRepo;
    private AttendantRepo attendantRepo;

    public InviteService() {
    }

    @Autowired
    public InviteService(SessionTokenRepo sessionTokenRepo, InviteRepo inviteRepo, AccountRepo accountRepo, EventRepo eventRepo, AttendantRepo attendantRepo) {
        this.sessionTokenRepo = sessionTokenRepo;
        this.inviteRepo = inviteRepo;
        this.accountRepo = accountRepo;
        this.eventRepo = eventRepo;
        this.attendantRepo = attendantRepo;
    }

    @Transactional
    public ResponseEntity<Invite> sendInvite(JsonRequestBody<Invite> requestBody) {
        try {
            SessionToken token = requestBody.getToken();
            Invite invite = requestBody.getObject();
            if (sessionTokenRepo.existsById(token.getId())) {
                if (accountRepo.existsById(invite.getInviter()) && accountRepo.existsById(invite.getInvitee()) && eventRepo.existsById(invite.getEventId())) {
                    return new ResponseEntity<>(inviteRepo.save(invite), HttpStatus.OK);
                }
                return new ResponseEntity<>(new Invite(), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new Invite(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(new Invite(), HttpStatus.BAD_GATEWAY);
        }
    }

    @Transactional
    public ResponseEntity<Account> acceptInvite(JsonRequestBody<Invite> requestBody) {
        try {
            SessionToken token = requestBody.getToken();
            Invite invite = requestBody.getObject();
            //validate token
            if (sessionTokenRepo.existsById(token.getId())) {
                //if invite exists
                if (inviteRepo.findByInviteeAndEventId(invite.getInvitee(), invite.getId()) != null) {
                    //if invitee is not already an attendant
                    if (attendantRepo.findByAccountIdAndEventId(invite.getInvitee(), invite.getEventId()) == null) {
                        attendantRepo.save(new Attendant(invite.getEventId(), invite.getInvitee(), Role.ATTENDANT));
                        inviteRepo.delete(invite);
                        return new ResponseEntity<>(accountRepo.findById(invite.getInvitee()).get(), HttpStatus.OK);
                    }
                    return new ResponseEntity<>(new Account(), HttpStatus.BAD_REQUEST); //if invitee is already an attendant
                }
                return new ResponseEntity<>(new Account(), HttpStatus.BAD_REQUEST); //if invite does not exist
            }
            return new ResponseEntity<>(new Account(), HttpStatus.UNAUTHORIZED); //if token invalid
        } catch (Exception e) {
            return new ResponseEntity<>(new Account(), HttpStatus.BAD_GATEWAY); //other complications
        }
    }

    @Transactional
    public ResponseEntity<Invite> ignoreInvite(JsonRequestBody<Invite> requestBody) {
        try {
            SessionToken token = requestBody.getToken();
            Invite invite = requestBody.getObject();
            //validate token
            if (sessionTokenRepo.existsById(token.getId())) {
                //if invite exists
                if (inviteRepo.existsById(invite.getId())) {
                    invite = inviteRepo.findByInviteeAndEventId(invite.getInvitee(), invite.getId());
                    inviteRepo.delete(invite);
                    return new ResponseEntity<>(invite, HttpStatus.OK);
                }
                return new ResponseEntity<>(invite, HttpStatus.NO_CONTENT); //if invite does not exist
            }
            return new ResponseEntity<>(new Invite(), HttpStatus.UNAUTHORIZED); //if token invalid
        } catch (Exception e) {
            return new ResponseEntity<>(new Invite(), HttpStatus.BAD_GATEWAY); //other complications
        }
    }
}
