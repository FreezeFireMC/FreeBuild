package de.arealzenix.freebuild.utils;

import de.arealzenix.freebuild.FreeBuild;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.UUID;

public class ScoreboardManager {
    public static HashMap<UUID, PlayerScoreboard> playerScorebaordHashMap;
    public static HashMap<UUID, BossBar> bossBarHashMap;
    public ScoreboardManager(Plugin plugin) {
        playerScorebaordHashMap = new HashMap<>();
        startUpdater(plugin);
    }
    public void startUpdater(Plugin plugin) {
        BukkitTask bukkitTask = new BukkitRunnable() {
            @Override
            public void run() {
                for (UUID uuid : playerScorebaordHashMap.keySet()) {
                    PlayerScoreboard playerScorebaord = playerScorebaordHashMap.get(uuid);
                    Objective objective = playerScorebaord.getScoreboard().getObjective("Lobby");
                    Player player = Bukkit.getPlayer(uuid);
                    playerScorebaord.getCoins().setSuffix("§b" + FreeBuild.getInstance().getCoinsInterface().getCoins(player.getUniqueId()));
                    objective.getScore(ChatColor.DARK_RED.toString()).setScore(4);
                    objective.getScore(ChatColor.AQUA.toString()).setScore(1);
                }
            }
        }.runTaskTimer(plugin,20, 1);
    }


    public static Scoreboard getScorebaord(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("Lobby", "2");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("  §bFreeze§cFire  ");
        if (scoreboard.getTeam(player.getName() + ".1") != null) {
            scoreboard.getTeam(player.getName() + ".1").unregister();
        }
        if (scoreboard.getTeam(player.getName() + ".2") != null) {
            scoreboard.getTeam(player.getName() + ".2").unregister();
        }
        if (scoreboard.getTeam(player.getName() + ".3") != null) {
            scoreboard.getTeam(player.getName() + ".3").unregister();
        }

        Team coins = scoreboard.registerNewTeam(player.getName() + ".1");
        Team kills = scoreboard.registerNewTeam(player.getName() + ".2");
        Team deaths = scoreboard.registerNewTeam(player.getName() + ".3");
        coins.setPrefix("§8» §b");
        coins.setSuffix("§b" + FreeBuild.getInstance().getCoinsInterface().getCoins(player.getUniqueId()));
        coins.addEntry(ChatColor.DARK_RED.toString());
        kills.setPrefix("§8» §b");
        kills.setSuffix("§b" + FreeBuild.getInstance().getCoinsInterface().getCoins(player.getUniqueId()));
        kills.addEntry(ChatColor.BLUE.toString());
        deaths.setPrefix("§8» §b");
        deaths.setSuffix("§b" + FreeBuild.getInstance().getCoinsInterface().getCoins(player.getUniqueId()));
        deaths.addEntry(ChatColor.BLACK.toString());
        objective.getScore("§0").setScore(10);
        objective.getScore("§cCoins:").setScore(0);
        objective.getScore(ChatColor.DARK_RED.toString()).setScore(8);
        objective.getScore("§1 ").setScore(7);
        objective.getScore("§cKills:").setScore(6);
        objective.getScore(ChatColor.BLUE.toString()).setScore(5);
        objective.getScore("§1 ").setScore(4);
        objective.getScore("§cDeaths:").setScore(3);
        objective.getScore(ChatColor.BLACK.toString()).setScore(2);
        objective.getScore("§1 ").setScore(1);

        PlayerScoreboard playerScorebaord = PlayerScoreboard.builder()
                .uuid(player.getUniqueId())
                .scoreboard(scoreboard)
                .coins(coins)
                .build();

        playerScorebaordHashMap.put(player.getUniqueId(), playerScorebaord);
        player.setScoreboard(scoreboard);

        return scoreboard;
    }
}
