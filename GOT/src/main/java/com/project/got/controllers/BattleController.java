package com.project.got.controllers;

import com.project.got.dtos.BattleDTO;
import com.project.got.dtos.RegionDTO;
import com.project.got.response.BattleResponse;
import com.project.got.services.BattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/battle")
public class BattleController
{
    @Autowired
    BattleService battleService;

    @RequestMapping(value = "/{battleNumber}",method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BattleResponse> getBattleByNumber(@PathVariable long battleNumber)
    {
        BattleDTO battleDTO = battleService.findBattleByBattleNumber(battleNumber);
        if (battleDTO != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new BattleResponse(null, HttpStatus.OK, LocalDateTime.now(), battleDTO));

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new BattleResponse("Battle number " + battleNumber + " not found ", HttpStatus.NOT_FOUND,
                        LocalDateTime.now(), null));
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BattleResponse>getLocationAndRegionList()
    {
        List<RegionDTO> regionsWithLocations = battleService.getRegionsAndLocationsList();
        return ResponseEntity.ok(new BattleResponse(null, HttpStatus.OK, LocalDateTime.now(), regionsWithLocations));
    }

    @RequestMapping(value = "/count",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BattleResponse> getTotalBattleCount()
    {
        Long battleCount = battleService.getTotalBattleCount();
        return ResponseEntity.ok(new BattleResponse(null, HttpStatus.OK, LocalDateTime.now(), battleCount));
    }

}
