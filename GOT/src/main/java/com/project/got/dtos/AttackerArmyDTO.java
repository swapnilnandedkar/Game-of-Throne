package com.project.got.dtos;

public class AttackerArmyDTO extends DomainDTO
{
    SoldierDTO soldiers;
    String attackerType;

    public SoldierDTO getSoldiers()
    {
        return soldiers;
    }

    public void setSoldiers(SoldierDTO soldiers)
    {
        this.soldiers = soldiers;
    }

    public String getAttackerType()
    {
        return attackerType;
    }

    public void setAttackerType(String attackerType)
    {
        this.attackerType = attackerType;
    }
}
