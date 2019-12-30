package com.github.lbovolini.lol.controller;

import com.github.lbovolini.lol.model.Summoner;
import com.github.lbovolini.lol.model.SummonerLeague;
import com.github.lbovolini.lol.model.SummonerMatch;
import com.github.lbovolini.lol.service.LeagueService;
import com.github.lbovolini.lol.service.MatchService;
import com.github.lbovolini.lol.service.SummonerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class APIv1Controller {

    @Autowired
    SummonerService summonerService;

    @Autowired
    LeagueService leagueService;

    @Autowired
    MatchService matchService;

    @RequestMapping("/{name}/{platform}/all.json")
    public ResponseEntity getAllInfo(@PathVariable String name, @PathVariable String platform) {

        Map response = new HashMap(3);

        Summoner summoner = summonerService.findByNameAndPlatform(name, platform, true);
        SummonerLeague summonerLeague = leagueService.findAllLeague(summoner.getId(), platform, true);
        SummonerMatch summonerMatch = matchService.findMatchHistory(platform, summoner.getAccountId(), true);

        response.put("summoner", summoner);
        response.put("leagues", summonerLeague);
        response.put("history", summonerMatch);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
