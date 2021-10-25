package com.project.got.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@JsonIgnoreProperties({"battle"})
public class AttackerArmy
{
    @Id
    String attackerArmyId;

    String soldierId;

    String attackerType;

    @ManyToOne(targetEntity = Battle.class)
    @JoinColumn(name = "battleId",referencedColumnName = "id")
    Battle battle;

    public String getAttackerArmyId()
    {
        return attackerArmyId;
    }

    public void setAttackerArmyId(String attackerArmyId)
    {
        this.attackerArmyId = attackerArmyId;
    }

    public String getSoldierId()
    {
        return soldierId;
    }

    public void setSoldierId(String soldierId)
    {
        this.soldierId = soldierId;
    }

    public String getAttackerType()
    {
        return attackerType;
    }

    public void setAttackerType(String attackerType)
    {
        this.attackerType = attackerType;
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
