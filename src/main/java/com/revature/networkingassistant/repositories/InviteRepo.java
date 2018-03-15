package com.revature.networkingassistant.repositories;

import com.revature.networkingassistant.beans.Invite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InviteRepo extends CrudRepository<Invite, String>{

    //When searching junction tables, pass a string with event/account id followed/preceded by a "|"
    //e.g "123|" would find invites for event 123 and "|123" would find invites linked to user 123
    Iterable<Invite> findByIdStartingWith(String eventId);

    Iterable<Invite> findByIdEndingWith(String accountId);
}
