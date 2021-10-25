package com.project.got.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.got.dtos.*;
import com.project.got.entities.*;
import com.project.got.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class BattleServiceImpl implements BattleService
{
    @Autowired
    BattlePlaceRepo battlePlaceRepo;

    @Autowired
    RegionRepo regionRepo;

    @Autowired
    LocationRepo locationRepo;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    BattleRepo battleRepo;

    @Autowired
    BattleTypeRepo battleTypeRepo;

    @Autowired
    SoldierRepo soldierRepo;

    @Override
    public BattleDTO findBattleByBattleNumber(long battleNumber)
    {
        Battle battle = battleRepo.findByBattleNumber(battleNumber);
        BattleDTO battleDTO = null;
        if(battle != null)
        {
            battleDTO = objectMapper.convertValue(battle, BattleDTO.class);

            List<BattlePlace> battlePlaces = battle.getBattlePlaceList();
            Optional<Region> region = regionRepo.findById(battlePlaces.get(0).getRegionId());

            List<String> locationIds = battlePlaces.stream().map(BattlePlace::getLocationId).collect(Collectors.toList());

            List<Location> locations = locationRepo.findAllById(locationIds);

            List<LocationDTO> locationDTOList = objectMapper.convertValue(locations, new TypeReference<List<LocationDTO>>()
            {
            });

            RegionDTO regionDTO = objectMapper.convertValue(region.get(), RegionDTO.class);

            regionDTO.setLocationDTOList(locationDTOList);
            battleDTO.setRegionDTO(regionDTO);

            if (battle.getBattleTypeId() != null)
            {
                Optional<BattleType> battleType = battleTypeRepo.findById(battle.getBattleTypeId());
                battleDTO.setBattleType(battleType.get());
            }

            List<AttackerArmy> attackerArmies = battle.getAttackerArmies();
            List<AttackerArmyDTO> attackerArmyDTOS = populateAttackerArmies(attackerArmies);

            List<DefenderArmy> defenderArmies = battle.getDefenderArmies();
            List<DefenderArmyDTO> defenderArmyDTOS = populateDefenderArmies(defenderArmies);

            battleDTO.setAttackerArmies(attackerArmyDTOS);
            battleDTO.setDefenderArmies(defenderArmyDTOS);
        }
        return  battleDTO;
    }

    private List<DefenderArmyDTO> populateDefenderArmies(List<DefenderArmy> defenderArmies)
    {
        List<DefenderArmyDTO> defenderArmyDTOS = new ArrayList<>();

        for(DefenderArmy defenderArmy : defenderArmies)
        {
            DefenderArmyDTO defenderArmyDTO = new DefenderArmyDTO();
            defenderArmyDTO.setDefenderType(defenderArmy.getDefenderType());
            defenderArmyDTO.setId(defenderArmy.getDefenderArmyId());
            defenderArmyDTO.setSoldiers(populateSoldier(defenderArmy.getSoldierId()));
            defenderArmyDTOS.add(defenderArmyDTO);
        }
        return defenderArmyDTOS;
    }

    public List<AttackerArmyDTO> populateAttackerArmies(List<AttackerArmy> attackerArmies)
    {
        List<AttackerArmyDTO> attackerArmyDTOS = new ArrayList<>();

        for(AttackerArmy attackerArmy : attackerArmies)
        {
            AttackerArmyDTO attackerArmyDTO = new AttackerArmyDTO();
            attackerArmyDTO.setAttackerType(attackerArmy.getAttackerType());
            attackerArmyDTO.setId(attackerArmy.getAttackerArmyId());
            attackerArmyDTO.setSoldiers(populateSoldier(attackerArmy.getSoldierId()));
            attackerArmyDTOS.add(attackerArmyDTO);
        }
        return attackerArmyDTOS;
    }

    public SoldierDTO populateSoldier(String soldierId)
    {
        Optional<Soldier> soldier = soldierRepo.findById(soldierId);
        SoldierDTO soldierDTO = objectMapper.convertValue(soldier.get(),SoldierDTO.class);
        return soldierDTO;
    }

    @Override
    public List<RegionDTO> getRegionsAndLocationsList()
    {
        List<BattlePlace> battlePlaces = battlePlaceRepo.findAll();
        Map<String, List<String>> groupByRegionsAndLocations = populateRegionsWithLocations(battlePlaces);

        List<RegionDTO> regions = new ArrayList<>();

        groupByRegionsAndLocations.forEach((k, v) ->
        {
            Optional<Region> region = regionRepo.findById(k);
            RegionDTO regionDTO = objectMapper.convertValue(region.get(), RegionDTO.class);
            List<Location> locations = locationRepo.findAllById(v);

            List<LocationDTO> locationDTOS = objectMapper.convertValue(locations, new TypeReference<List<LocationDTO>>()
            {});
            regionDTO.setLocationDTOList(locationDTOS);
            regions.add(regionDTO);

        });
        return regions;
    }

    @Override
    public long getTotalBattleCount()
    {
        return battleRepo.count();
    }

    public Map<String,List<String>> populateRegionsWithLocations(List<BattlePlace> battlePlaces)
    {
        Map<String, List<String>> groupByRegionsAndLocations = new HashMap<>();
        for (BattlePlace battlePlace : battlePlaces)
        {
            List<String> locations = groupByRegionsAndLocations
                    .getOrDefault(battlePlace.getRegionId(), new ArrayList<>());

            if (battlePlace.getLocationId() != null)
                locations.add(battlePlace.getLocationId());

            groupByRegionsAndLocations.put(battlePlace.getRegionId(), locations);
        }
        return groupByRegionsAndLocations;
    }

}
