package com.cegeka.switchfully.security.external.authentication;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.cegeka.switchfully.security.external.authentication.Role.*;
import static java.util.Arrays.asList;

public enum Feature {
    DEPLOY(PRIVATE,GENERAL),
    JOIN_ARMY(CIVILIAN),
    PROMOTE(HUMAN_RELATIONSHIPS),
    DISCHARGE(HUMAN_RELATIONSHIPS),
    LAUNCHNUKE(GENERAL);



    Collection<Role> roles;

    Feature(Role... rollen){
        this.roles = asList(rollen);
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public static List<Feature> getFeatureForRoles(List<String> rolesOfUser) {
        List<Role> roles = rolesOfUser.stream()
                .map(Role::valueOf)
                .collect(Collectors.toList());
        return Arrays.stream(Feature.values())
                .filter(feature -> !Collections.disjoint(feature.getRoles(),roles))
                .collect(Collectors.toList());
    }
}
