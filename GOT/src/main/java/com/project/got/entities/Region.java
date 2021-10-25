package com.project.got.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@JsonIgnoreProperties({"battleList"})
public class Region implements Serializable
{
    @Id
    String id;

    @Column(unique = true) String regionName;

    public Region()
    {

    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getRegionName()
    {
        return regionName;
    }

    public void setRegionName(String regionName)
    {
        this.regionName = regionName;
    }

    public String getId()
    {
        return id;
    }
}
