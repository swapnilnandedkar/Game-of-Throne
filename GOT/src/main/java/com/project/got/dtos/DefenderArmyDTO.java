package com.project.got.dtos;

public class DefenderArmyDTO extends DomainDTO
{
    SoldierDTO soldiers;
    String defenderType;

    public SoldierDTO getSoldiers()
    {
        return soldiers;
    }

    public void setSoldiers(SoldierDTO soldiers)
    {
        this.soldiers = soldiers;
    }

    public String getDefenderType()
    {
        return defenderType;
    }

    public void setDefenderType(String defenderType)
    {
        this.defenderType = defenderType;
    }
}
