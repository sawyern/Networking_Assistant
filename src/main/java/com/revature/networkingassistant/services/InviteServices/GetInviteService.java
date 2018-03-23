package com.revature.networkingassistant.services.InviteServices;

import com.revature.networkingassistant.beans.Invite;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.repositories.AccountRepo;
import com.revature.networkingassistant.repositories.InviteRepo;
import com.revature.networkingassistant.repositories.SessionTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class GetInviteService {

    private SessionTokenRepo tokenRepo;
    private AccountRepo accountRepo;
    private InviteRepo inviteRepo;

    public GetInviteService(){}

    @Autowired
    public GetInviteService(SessionTokenRepo tokenRepo, AccountRepo accountRepo, InviteRepo inviteRepo) {
        this.tokenRepo = tokenRepo;
        this.accountRepo = accountRepo;
        this.inviteRepo = inviteRepo;
    }

    @Transactional
    public ResponseEntity<ArrayList<Invite>> getSentInvites(JsonRequestBody<Invite> requestBody) {
        SessionToken token = requestBody.getToken();
        Invite invite = requestBody.getObject();
        try {
            if (tokenRepo.existsById(token.getId()) && accountRepo.existsById(invite.getInviter())) {
                ArrayList<Invite> invites = inviteRepo.findByInviterAndEventId(invite.getInviter(), invite.getEventId());
                if (invites.size() > 0) {
                    return new ResponseEntity<>(invites, HttpStatus.OK);
                }
                return new ResponseEntity<>(invites, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(new ArrayList<Invite>(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<Invite>(), HttpStatus.BAD_GATEWAY);
        }
    }

    @Transactional
    public ResponseEntity<ArrayList<Invite>> getRecievedInvites(JsonRequestBody<Invite> requestBody) {
        SessionToken token = requestBody.getToken();
        Invite invite = requestBody.getObject();
        try {
            if (tokenRepo.existsById(token.getId()) && accountRepo.existsById(invite.getInvitee())) {
                ArrayList<Invite> invites = inviteRepo.findByInviteeAndEventId(invite.getInvitee(), invite.getEventId());
                if (invites.size() > 0) {
                    return new ResponseEntity<>(invites, HttpStatus.OK);
                }
                return new ResponseEntity<>(invites, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(new ArrayList<Invite>(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<Invite>(), HttpStatus.BAD_GATEWAY);
        }
    }
}