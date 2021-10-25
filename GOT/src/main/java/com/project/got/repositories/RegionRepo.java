package com.project.got.repositories;

import com.project.got.entities.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepo extends JpaRepository<Region,String>
{
    Region findByRegionName(String regionFromCsv);
}
