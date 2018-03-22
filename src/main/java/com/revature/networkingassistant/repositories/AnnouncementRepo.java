package com.revature.networkingassistant.repositories;

import com.revature.networkingassistant.beans.Announcement;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface AnnouncementRepo extends CrudRepository<Announcement, String>{

    ArrayList<Announcement> findByEventId(int eventId);

    ArrayList<Announcement> findByAccountId(int accountId);
}