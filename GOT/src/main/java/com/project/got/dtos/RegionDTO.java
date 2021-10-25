package com.project.got.dtos;

import java.util.ArrayList;
import java.util.List;

public class RegionDTO extends DomainDTO
{
    String regionName;
    List<LocationDTO> locationDTOList = new ArrayList();

    public String getRegionName()
    {
        return regionName;
    }

    public void setRegionName(String regionName)
    {
        this.regionName = regionName;
    }

    public List<LocationDTO> getLocationDTOList()
    {
        return locationDTOList;
    }

    public void setLocationDTOList(List<LocationDTO> locationDTOList)
    {
        this.locationDTOList = locationDTOList;
    }
}
