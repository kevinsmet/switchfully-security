package com.cegeka.switchfully.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = ArmyResource.ARMY_RESOURCE_PATH)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ArmyResource {

    public static final String ARMY_RESOURCE_PATH = "/armies";

    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE ,path = "/{country}")
    @PreAuthorize("hasAnyRole('ROLE_PRIVATE','ROLE_GENERAL')")
    public ArmyInfoDto getDeployedArmyInfo(@PathVariable(value = "country") String country){
        return ArmyInfoDto.armyInfoDto()
                .withCountry(country)
                .withNumberOfTroops(2000)
                .withxCoordinateOfBase(15)
                .withyCoordinateOfBase(20);
    }

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_CIVILIAN')")
    public void joinArmy(){
        //TODO
    }

    @RequestMapping(method = RequestMethod.POST, path = "/promote/{name}")
    @PreAuthorize("hasRole('ROLE_HUMAN_RELATIONSHIPS')")
    public void promotePrivate(@PathVariable(value = "name") String name){
        //TODO
    }

    @RequestMapping(method = RequestMethod.POST, path = "/discharge/{name}")
    @PreAuthorize("hasRole('ROLE_HUMAN_RELATIONSHIPS')")
    public void dischargeSoldier(@PathVariable(value = "name") String name){
        //TODO
    }

    @RequestMapping(method = RequestMethod.GET, path = "/nuke")
    @PreAuthorize("hasRole('ROLE_GENERAL')")
    public String launchNukes(){
        return "The world ends. Not with a bang but a whimper";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/armies/tanks")
    public void getTanksInfo(){
        //TODO
    }

    @RequestMapping(method = RequestMethod.POST, path = "/armies/tanks")
    public void addTanks(){
        //TODO
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/armies/tanks")
    public void blowUpTanksAndEnjoyTheFireworks(){
        //TODO
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/armies/tanks")
    public void addNewTanks(){
        //TODO
    }
}
