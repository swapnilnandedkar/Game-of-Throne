package com.project.got.repositories;

import com.project.got.entities.BattlePlace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BattlePlaceRepo extends JpaRepository<BattlePlace,String>
{
}
