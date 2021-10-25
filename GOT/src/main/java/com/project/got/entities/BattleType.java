package com.project.got.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BattleType
{
    @Id
    String battleTypeId;

    @Column(unique = true)
    String battleType;

    public String getBattleTypeId()
    {
        return battleTypeId;
    }

    public void setBattleTypeId(String battleTypeId)
    {
        this.battleTypeId = battleTypeId;
    }

    public String getBattleType()
    {
        return battleType;
    }

    public void setBattleType(String battleType)
    {
        this.battleType = battleType;
    }
}
