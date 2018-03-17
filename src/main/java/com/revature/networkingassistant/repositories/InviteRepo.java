package com.revature.networkingassistant.repositories;

import com.revature.networkingassistant.beans.Invite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InviteRepo extends CrudRepository<Invite, Integer>{
    Invite findByEventId(int eventId);
    Invite findByInviterId(int inviterId);
    Invite findByInviteeId(int inviteeId);
}
