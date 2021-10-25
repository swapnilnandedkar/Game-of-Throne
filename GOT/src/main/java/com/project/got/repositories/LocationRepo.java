package com.project.got.repositories;

import com.project.got.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepo extends JpaRepository<Location,String>
{
    Location findByLocationName(String loc);
}
