package de.arealzenix.freebuild.utils.languageLibary;


import de.chaos.mc.serverapi.utils.playerlibary.languageLibary.LanguageType;

import java.util.UUID;

public interface LanguageInterface {
    public LanguageType getLanguageType(UUID uuid);
    public LanguageType setLanguageType(UUID uuid, LanguageType type);
    public void loadPlayerLanguage(UUID uuid);
}