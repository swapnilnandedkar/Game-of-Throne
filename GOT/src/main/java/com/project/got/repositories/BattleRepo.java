package com.project.got.repositories;

import com.project.got.entities.Battle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BattleRepo extends JpaRepository<Battle,Long>
{
    Battle findByBattleNumber(long i);
}
