package com.project.got.dtos;

import java.util.List;

public class BattlePlaceDTO extends DomainDTO
{
    List<LocationDTO> locationDTOList;
    String locationName;
    RegionDTO regionDTO;

    public String getLocationName()
    {
        return locationName;
    }

    public void setLocationName(String locationName)
    {
        this.locationName = locationName;
    }

    public RegionDTO getRegion()
    {
        return regionDTO;
    }

    public void setRegion(RegionDTO regionDTO)
    {
        this.regionDTO = regionDTO;
    }

    public List<LocationDTO> getLocationId()
    {
        return locationDTOList;
    }

    public void setLocationId(List<LocationDTO> locationId)
    {
        this.locationDTOList = locationId;
    }
}
