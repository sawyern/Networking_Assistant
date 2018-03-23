package com.revature.networkingassistant.repositories;

import com.revature.networkingassistant.beans.Invite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface InviteRepo extends CrudRepository<Invite, Integer>{
    ArrayList<Invite> findByEventId(int eventId);
    ArrayList<Invite> findByInviterAndEventId(int inviter, int eventId);
    ArrayList<Invite> findByInviteeAndEventId(int invitee, int eventId);
}
