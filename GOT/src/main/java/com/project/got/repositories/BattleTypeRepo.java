package com.project.got.repositories;

import com.project.got.entities.BattleType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BattleTypeRepo extends JpaRepository<BattleType,String>
{
    BattleType findByBattleType(String battleTypeFromCsv);
}
