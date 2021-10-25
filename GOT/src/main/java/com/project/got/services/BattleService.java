package com.project.got.services;

import com.project.got.dtos.BattleDTO;
import com.project.got.dtos.RegionDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BattleService
{
    public BattleDTO findBattleByBattleNumber(long battleNumber);
    public List<RegionDTO> getRegionsAndLocationsList();
    public long getTotalBattleCount();
}
