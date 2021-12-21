package de.arealzenix.freebuild.utils;

import lombok.Builder;
import lombok.Data;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.UUID;

@Data
@Builder
public class PlayerScoreboard {
    public UUID uuid;
    public Scoreboard scoreboard;
    public Team coins;
    public Team kills;
    public Team deaths;
}