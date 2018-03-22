package com.revature.networkingassistant.repositories;

import com.revature.networkingassistant.beans.Event.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepo extends JpaRepository<Location, Integer> {
}
