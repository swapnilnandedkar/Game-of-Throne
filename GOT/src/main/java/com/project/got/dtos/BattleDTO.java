package com.project.got.dtos;

import com.project.got.entities.AttackerArmy;
import com.project.got.entities.BattleType;
import com.project.got.entities.DefenderArmy;

import java.util.ArrayList;
import java.util.List;

public class BattleDTO extends DomainDTO
{
    String battleName;

    String battleYear;

    Long battleNumber;

    List<AttackerArmyDTO> attackerArmies = new ArrayList<>();

    List<DefenderArmyDTO> defenderArmies = new ArrayList<>();

    String attackerOutcome;

    BattleType battleType;

    Boolean majorDeath;

    Boolean majorCapture;

    Integer attackerSize;

    Integer defenderSize;

    Boolean summer;

    RegionDTO regionDTO;

    String note;

    public Long getBattleNumber()
    {
        return battleNumber;
    }

    public void setBattleNumber(Long battleNumber)
    {
        this.battleNumber = battleNumber;
    }

    public String getBattleName()
    {
        return battleName;
    }

    public void setBattleName(String battleName)
    {
        this.battleName = battleName;
    }

    public RegionDTO getRegionDTO()
    {
        return regionDTO;
    }

    public void setRegionDTO(RegionDTO regionDTO)
    {
        this.regionDTO = regionDTO;
    }

    public BattleType getBattleType()
    {
        return battleType;
    }

    public void setBattleType(BattleType battleType)
    {
        this.battleType = battleType;
    }

    public String getBattleYear()
    {
        return battleYear;
    }

    public void setBattleYear(String battleYear)
    {
        this.battleYear = battleYear;
    }

    public Boolean isMajorDeath()
    {
        return majorDeath;
    }

    public void setMajorDeath(Boolean majorDeath)
    {
        this.majorDeath = majorDeath;
    }

    public Boolean isMajorCapture()
    {
        return majorCapture;
    }

    public void setMajorCapture(Boolean majorCapture)
    {
        this.majorCapture = majorCapture;
    }

    public Integer getAttackerSize()
    {
        return attackerSize;
    }

    public void setAttackerSize(Integer attackerSize)
    {
        this.attackerSize = attackerSize;
    }

    public Integer getDefenderSize()
    {
        return defenderSize;
    }

    public void setDefenderSize(Integer defenderSize)
    {
        this.defenderSize = defenderSize;
    }

    public Boolean getSummer()
    {
        return summer;
    }

    public void setSummer(Boolean summer)
    {
        this.summer = summer;
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    public List<AttackerArmyDTO> getAttackerArmies()
    {
        return attackerArmies;
    }

    public void setAttackerArmies(List<AttackerArmyDTO> attackerArmies)
    {
        this.attackerArmies = attackerArmies;
    }

    public List<DefenderArmyDTO> getDefenderArmies()
    {
        return defenderArmies;
    }

    public void setDefenderArmies(List<DefenderArmyDTO> defenderArmies)
    {
        this.defenderArmies = defenderArmies;
    }

    public String getAttackerOutcome()
    {
        return attackerOutcome;
    }

    public void setAttackerOutcome(String attackerOutcome)
    {
        this.attackerOutcome = attackerOutcome;
    }
}
