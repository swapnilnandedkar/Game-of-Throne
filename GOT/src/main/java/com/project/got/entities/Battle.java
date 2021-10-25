package com.project.got.entities;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Battle implements Serializable
{
    @Id
    String id;
    String battleName;
    String battleYear;
    long battleNumber;
    String attackerOutcome;
    String battleTypeId;
    Boolean majorDeath;
    Boolean majorCapture;
    Integer attackerSize;
    Integer defenderSize;
    Boolean summer;

    @Column(length = 1500)
    String note;

    @OneToMany(mappedBy = "battle", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    List<AttackerArmy> attackerArmies = new ArrayList<>();

    @OneToMany(mappedBy = "battle", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    List<DefenderArmy> defenderArmies = new ArrayList<>();

    @OneToMany(mappedBy = "battle", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<BattlePlace> battlePlaceList = new ArrayList();

    public String getBattleYear()
    {
        return battleYear;
    }

    public void setBattleYear(String battleYear)
    {
        if(StringUtils.isNotEmpty(battleYear))
            this.battleYear = battleYear;
    }

    public Boolean getMajorDeath()
    {
        return majorDeath;
    }

    public void setMajorDeath(Boolean majorDeath)
    {
        this.majorDeath = majorDeath;
    }

    public Boolean getMajorCapture()
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

    public Boolean isSummer()
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
        if(StringUtils.isNotEmpty(note))
            this.note = note;
    }

    public List<BattlePlace> getBattlePlaceList()
    {
        return battlePlaceList;
    }

    public void addBattlePlace(BattlePlace battlePlace)
    {
        this.battlePlaceList.add(battlePlace);
        battlePlace.setBattle(this);
    }

    public String getAttackerOutcome()
    {
        return attackerOutcome;
    }

    public void setAttackerOutcome(String attackerOutcome)
    {
        if(StringUtils.isNotEmpty(attackerOutcome))
            this.attackerOutcome = attackerOutcome;
    }

    public String getBattleId()
    {
        return id;
    }

    public void setBattleId(String battleId)
    {
        this.id = battleId;
    }

    public long getBattleNumber()
    {
        return battleNumber;
    }

    public void setBattleNumber(long battleNumber)
    {
        this.battleNumber = battleNumber;
    }

    public String getBattleName()
    {
        return battleName;
    }

    public void setBattleName(String battleName)
    {
        if(StringUtils.isNotEmpty(battleName))
            this.battleName = battleName;
    }

    public String getBattleTypeId()
    {
        return battleTypeId;
    }

    public void setBattleTypeId(String battleTypeId)
    {
        this.battleTypeId = battleTypeId;
    }

    public List<AttackerArmy> getAttackerArmies()
    {
        return attackerArmies;
    }

    public void addAttackerArmy(AttackerArmy attackerArmy)
    {
        this.attackerArmies.add(attackerArmy);
        attackerArmy.setBattle(this);
    }

    public List<DefenderArmy> getDefenderArmies()
    {
        return defenderArmies;
    }

    public void addDefenderArmy(DefenderArmy defenderArmy)
    {
        this.defenderArmies.add(defenderArmy);
        defenderArmy.setBattle(this);
    }
}
