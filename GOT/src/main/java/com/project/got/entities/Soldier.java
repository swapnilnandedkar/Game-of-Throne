package com.project.got.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Soldier
{
    @Id
    String id;

    @Column(unique = true)
    String soldierName;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getSoldierName()
    {
        return soldierName;
    }

    public void setSoldierName(String soldierName)
    {
        this.soldierName = soldierName;
    }

    @Override public String toString()
    {
        return "Soldier{" + "id='" + id + '\'' + ", soldierName='" + soldierName + '\'' + '}';
    }
}
