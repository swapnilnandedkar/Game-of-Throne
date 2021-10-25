package com.project.got.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@JsonIgnoreProperties({"battle"})
public class DefenderArmy
{
    @Id
    String defenderArmyId;

    String soldierId;

    String defenderType;

    @ManyToOne(targetEntity = Battle.class)
    @JoinColumn(name = "battleId",referencedColumnName = "id")
    Battle battle;

    public String getDefenderArmyId()
    {
        return defenderArmyId;
    }

    public void setDefenderArmyId(String defenderArmyId)
    {
        this.defenderArmyId = defenderArmyId;
    }

    public String getSoldierId()
    {
        return soldierId;
    }

    public void setSoldierId(String soldierId)
    {
        this.soldierId = soldierId;
    }

    public String getDefenderType()
    {
        return defenderType;
    }

    public void setDefenderType(String defenderType)
    {
        this.defenderType = defenderType;
    }

    public Battle getBattle()
    {
        return battle;
    }

    public void setBattle(Battle battle)
    {
        this.battle = battle;
    }
}
