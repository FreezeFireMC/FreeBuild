package de.arealzenix.freebuild.commands;

import de.arealzenix.freebuild.FreeBuild;
import de.arealzenix.freebuild.locations.LocationInterface;
import de.arealzenix.freebuild.utils.FreeBuildPlayerLanguage;
import de.arealzenix.freebuild.utils.megaUtils.menu.Menu;
import de.arealzenix.freebuild.utils.megaUtils.menu.MenuFactory;
import de.chaos.mc.serverapi.utils.ItemBuilder;
import de.chaos.mc.serverapi.utils.stringLibary.AbstractMessages;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WarpCommand implements CommandExecutor {
    private MenuFactory menuFactory;
    private LocationInterface locationInterface;
    public WarpCommand(MenuFactory menuFactory, LocationInterface locationInterface) {
        this.menuFactory = menuFactory;
        this.locationInterface = locationInterface;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
       if (commandSender instanceof Player) {
           Player player= (Player) commandSender;
           FreeBuildPlayerLanguage freeBuildPlayerLanguage = FreeBuild.getOnlinePlayers().get(player.getUniqueId());
           if (strings.length == 1) {
                String locationName = strings[0];
                if (locationName.equalsIgnoreCase("spawn")) {
                    player.teleport(locationInterface.getLocation("spawn"));
                    player.sendMessage(freeBuildPlayerLanguage.getSuccesfullyTeleported());
                    return true;
                }
               if (locationName.equalsIgnoreCase("nether")) {
                   player.teleport(locationInterface.getLocation("nether"));
                   player.sendMessage(freeBuildPlayerLanguage.getSuccesfullyTeleported());
                   return true;
               }
               if (locationName.equalsIgnoreCase("end")) {
                   player.teleport(locationInterface.getLocation("end"));
                   player.sendMessage(freeBuildPlayerLanguage.getSuccesfullyTeleported());
                   return true;
               }
               player.sendMessage(freeBuildPlayerLanguage.getLocationDoesntExist());

           } else {
               Menu menu = menuFactory.createMenu(27, freeBuildPlayerLanguage.getWarpInvName());
               menu.additem(10, new ItemBuilder(Material.GRASS_BLOCK).name(freeBuildPlayerLanguage.spawnItem).itemStack(), player1 -> {
                   player1.teleport(locationInterface.getLocation("spawn"));
                   player1.sendMessage(freeBuildPlayerLanguage.getSuccesfullyTeleported());
                   player1.closeInventory();
               });
               menu.additem(12, new ItemBuilder(Material.NETHERRACK).name(freeBuildPlayerLanguage.netherItem).itemStack(), player1 -> {
                   player1.teleport(locationInterface.getLocation("nether"));
                   player1.sendMessage(freeBuildPlayerLanguage.getSuccesfullyTeleported());
                   player1.closeInventory();
               });
               menu.additem(12, new ItemBuilder(Material.END_STONE).name(freeBuildPlayerLanguage.endItem).itemStack(), player1 -> {
                   player1.teleport(locationInterface.getLocation("end"));
                   player1.sendMessage(freeBuildPlayerLanguage.getSuccesfullyTeleported());
                   player1.closeInventory();
               });
               menu.openInventory(player);
               return true;
           }
       } else {
           commandSender.sendMessage(AbstractMessages.BEAPLAYER);
       }
        return false;
    }
}
