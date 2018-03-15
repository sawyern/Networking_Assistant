package com.revature.networkingassistant.repositories;

import com.revature.networkingassistant.beans.Announcement;
import org.springframework.data.repository.CrudRepository;

public interface AnnouncementRepo extends CrudRepository<Announcement, String>{

    Iterable<Announcement> getByIdStartingWith (String eventId);

    Iterable<Announcement> getByIdEndingWith (String accountId);
}