package com.revature.networkingassistant.repositories;

import com.revature.networkingassistant.beans.Attendant;
import org.springframework.data.repository.CrudRepository;

public interface AttendantRepo extends CrudRepository<Attendant, Integer> {
}
