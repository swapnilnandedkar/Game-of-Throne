package com.project.got.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"battle"})
public class BattlePlace
{
    @Id
    String battlePlaceId;

    @ManyToOne(targetEntity = Battle.class)
    @JoinColumn(name = "battleId",referencedColumnName = "id")
    Battle battle;

    String locationId;

    String regionId;

    public String getRegionId()
    {
        return regionId;
    }

    public void setRegionId(String regionId)
    {
        this.regionId = regionId;
    }

    public String getLocationId()
    {
        return locationId;
    }

    public void setLocationId(String locationId)
    {
        this.locationId = locationId;
    }

    public String getBattlePlaceId()
    {
        return battlePlaceId;
    }

    public void setBattlePlaceId(String battlePlaceId)
    {
        this.battlePlaceId = battlePlaceId;
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
