package com.project.got.repositories;

import com.project.got.entities.Soldier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoldierRepo extends JpaRepository<Soldier,String>
{
    Soldier findBySoldierName(String attackerKing);
}
