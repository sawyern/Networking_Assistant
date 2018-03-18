package com.revature.networkingassistant.repositories;

import com.revature.networkingassistant.beans.Invite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InviteRepo extends CrudRepository<Invite, String>{

    Iterable<Invite> findByEventId(int eventId);

    Iterable<Invite> findByInviterId(int inviterId);

    Iterable<Invite> findByInviteeId(int inviteeId);
}
