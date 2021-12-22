package de.arealzenix.freebuild.utils;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FreeBuildPlayerLanguage {

    // WarpSystem
    public String warpInvName;
    public String spawnItem;
    public String endItem;
    public String netherItem;
    public String succesfullyTeleported;
    public String locationDoesntExist;
}
