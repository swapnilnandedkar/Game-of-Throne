package com.project.got.registry;

import com.project.got.entities.*;
import com.project.got.repositories.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Component
public class BattleDataRegistry implements ApplicationRunner
{
    @Autowired
    BattleRepo battleRepo;

    @Autowired
    RegionRepo regionRepo;

    @Autowired
    LocationRepo locationRepo;

    @Autowired
    BattleTypeRepo battleTypeRepo;

    @Autowired
    SoldierRepo soldierRepo;

    @Autowired
    BattlePlaceRepo battlePlaceRepo;

    Set<String> battleTypeSet = new HashSet();

    Set<String> locationSet = new HashSet<>();

    Set<String> regionSet = new HashSet<>();

    Set<String> soldierSet = new HashSet<>();

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        csvToDB();
    }

    public void csvToDB() throws Exception
    {
        Resource resource = new ClassPathResource("battles.csv");

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(resource.getInputStream(), "UTF-8"));
                CSVParser csvParser = new CSVParser(fileReader,
                        CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());)
        {

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords)
            {
                Battle battle = new Battle();
                battle.setBattleId(UUID.randomUUID().toString());

                populateBattle(csvRecord, battle);
                populateBattleType(csvRecord, battle);
                Region region = populateRegion(csvRecord);
                populateLocations(csvRecord, region, battle);

                populateAttackers(csvRecord, "attacker_king", battle);
                populateAttackers(csvRecord, "attacker_commander", battle);

                populateDefenders(csvRecord, "defender_king", battle);
                populateDefenders(csvRecord, "defender_commander", battle);

                battleRepo.save(battle);
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    private void populateDefenders(CSVRecord csvRecord, String defenderProperty, Battle battle)
    {
        String defenderFromCsv = csvRecord.get(defenderProperty);
        if (StringUtils.isNotEmpty(defenderFromCsv))
        {
            String[] defenders =
                    "defender_king".equals(defenderProperty) ? defenderFromCsv.split("/") : defenderFromCsv.split(",");
            for (String defenderKing : defenders)
            {
                Soldier soldier = null;
                if (!soldierSet.contains(defenderKing))
                {
                    soldier = new Soldier();
                    soldier.setId(UUID.randomUUID().toString());
                    soldier.setSoldierName(defenderKing);
                    soldierRepo.save(soldier);
                    soldierSet.add(defenderKing);
                }
                else
                {
                    soldier = soldierRepo.findBySoldierName(defenderKing);
                }

                DefenderArmy defenderArmy = new DefenderArmy();
                defenderArmy.setDefenderArmyId(UUID.randomUUID().toString());
                defenderArmy.setSoldierId(soldier.getId());
                if ("defender_king".equals(defenderProperty))
                    defenderArmy.setDefenderType("DefenderKing");
                else
                    defenderArmy.setDefenderType("DefenderCommander");

                battle.addDefenderArmy(defenderArmy);
            }
        }
    }

    private void populateAttackers(CSVRecord csvRecord, String attackerProperty, Battle battle)
    {
        String attackerFromCsv = csvRecord.get(attackerProperty);
        if (StringUtils.isNotEmpty(attackerFromCsv))
        {
            String[] attackers =
                    "attacker_king".equals(attackerProperty) ? attackerFromCsv.split("/") : attackerFromCsv.split(",");
            for (String attacker : attackers)
            {
                Soldier soldier = null;
                if (!soldierSet.contains(attacker))
                {
                    soldier = new Soldier();
                    soldier.setId(UUID.randomUUID().toString());
                    soldier.setSoldierName(attacker);
                    soldierRepo.save(soldier);
                    soldierSet.add(attacker);
                }
                else
                {
                    soldier = soldierRepo.findBySoldierName(attacker);
                }

                AttackerArmy attackerArmy = new AttackerArmy();
                attackerArmy.setAttackerArmyId(UUID.randomUUID().toString());
                attackerArmy.setSoldierId(soldier.getId());
                if ("attacker_king".equals(attackerProperty))
                    attackerArmy.setAttackerType("AttackerKing");
                else
                    attackerArmy.setAttackerType("AttackerCommander");

                battle.addAttackerArmy(attackerArmy);
            }
        }
    }

    private void populateLocations(CSVRecord csvRecord, Region region, Battle battle)
    {
        String locationFromCsv = csvRecord.get("location");
        if (StringUtils.isNotEmpty(locationFromCsv))
        {
            String[] locations = locationFromCsv.split(",");
            for (String loc : locations)
            {
                Location location = null;
                if (!locationSet.contains(loc))
                {
                    location = new Location();
                    location.setId(UUID.randomUUID().toString());
                    location.setLocationName(loc);
                    locationSet.add(loc);
                    locationRepo.save(location);
                }
                else
                {
                    location = locationRepo.findByLocationName(loc);
                }
                populateBattlePlace(region, location, battle);
            }
        }
        else
        {
            populateBattlePlace(region, null, battle);
        }
        regionRepo.save(region);
    }

    private void populateBattlePlace(Region region, Location location, Battle battle)
    {
        BattlePlace battlePlace = new BattlePlace();
        battlePlace.setBattlePlaceId(UUID.randomUUID().toString());
        battlePlace.setRegionId(region.getId());
        if (location != null)
            battlePlace.setLocationId(location.getId());
        battle.addBattlePlace(battlePlace);
    }

    private Region populateRegion(CSVRecord csvRecord)
    {
        Region region = null;
        String regionFromCsv = csvRecord.get("region");
        if (StringUtils.isNotEmpty(regionFromCsv) && !regionSet.contains(regionFromCsv))
        {
            region = new Region();
            region.setRegionName(regionFromCsv);
            region.setId(UUID.randomUUID().toString());
            regionSet.add(regionFromCsv);
        }
        else
        {
            region = regionRepo.findByRegionName(regionFromCsv);
        }
        return region;
    }

    private void populateBattleType(CSVRecord csvRecord, Battle battle)
    {
        String battleTypeFromCsv = csvRecord.get("battle_type");
        BattleType battleType = null;
        if (StringUtils.isNotEmpty(battleTypeFromCsv) && !battleTypeSet.contains(battleTypeFromCsv))
        {
            battleType = new BattleType();
            battleType.setBattleType(battleTypeFromCsv);
            battleType.setBattleTypeId(UUID.randomUUID().toString());
            battleTypeRepo.save(battleType);
            battleTypeSet.add(battleTypeFromCsv);
        }
        else
        {
            battleType = battleTypeRepo.findByBattleType(battleTypeFromCsv);
        }

        if (battleType != null)
            battle.setBattleTypeId(battleType.getBattleTypeId());
    }

    private void populateBattle(CSVRecord csvRecord, Battle battle)
    {
        battle.setBattleName(csvRecord.get("name"));
        battle.setBattleYear(csvRecord.get("year"));
        battle.setBattleNumber(Long.parseLong(csvRecord.get("battle_number")));
        battle.setAttackerOutcome(csvRecord.get("attacker_outcome"));

        if (StringUtils.isNotEmpty(csvRecord.get("major_death")))
        {
            Boolean b = csvRecord.get("major_death").equals("1");
            battle.setMajorDeath(b);
        }

        if (StringUtils.isNotEmpty(csvRecord.get("major_capture")))
        {
            Boolean b = csvRecord.get("major_capture").equals("1");
            battle.setMajorCapture(b);
        }

        if (StringUtils.isNotEmpty(csvRecord.get("attacker_size")))
        {
            battle.setAttackerSize(new Integer(csvRecord.get("attacker_size")));
        }

        if (StringUtils.isNotEmpty(csvRecord.get("defender_size")))
        {
            battle.setDefenderSize(new Integer(csvRecord.get("defender_size")));
        }

        if (StringUtils.isNotEmpty(csvRecord.get("summer")))
        {
            Boolean b = csvRecord.get("summer").equals("1");
            battle.setSummer(b);
        }

        battle.setNote(csvRecord.get("note"));
    }
}
