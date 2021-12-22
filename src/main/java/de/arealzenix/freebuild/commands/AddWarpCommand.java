package de.arealzenix.freebuild.commands;

import de.arealzenix.freebuild.FreeBuild;
import de.arealzenix.freebuild.locations.LocationInterface;
import de.arealzenix.freebuild.utils.FreeBuildPlayerLanguage;
import de.arealzenix.freebuild.utils.Permissions;
import de.chaos.mc.serverapi.ServerAPIBukkitMain;
import de.chaos.mc.serverapi.utils.playerlibary.languageLibary.PlayerLanguage;
import de.chaos.mc.serverapi.utils.stringLibary.AbstractMessages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AddWarpCommand implements CommandExecutor {
    private final LocationInterface locationInterface;
    public AddWarpCommand(LocationInterface locationInterface) {
        this.locationInterface = locationInterface;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            PlayerLanguage playerLanguage = ServerAPIBukkitMain.getOnlinePlayers().get(player.getUniqueId());
            FreeBuildPlayerLanguage freeBuildPlayerLanguage = FreeBuild.getOnlinePlayers().get(player.getUniqueId());
            if (player.hasPermission(Permissions.setWarpPermission)) {
                String locationName = args[0];
                if (locationName.equalsIgnoreCase("spawn")) {
                    locationInterface.addLocation(locationName, player.getLocation());
                    return true;
                }
                if (locationName.equalsIgnoreCase("nether")) {
                    locationInterface.addLocation(locationName, player.getLocation());
                    return true;
                }
                if (locationName.equalsIgnoreCase("end")) {
                    locationInterface.addLocation(locationName, player.getLocation());
                    return true;
                }
                player.sendMessage(freeBuildPlayerLanguage.getLocationDoesntExist());
            } else {
                player.sendMessage(playerLanguage.getNOPERMISSION());
            }
        } else {
            sender.sendMessage(AbstractMessages.BEAPLAYER);
            return false;
        }
        return false;
    }
}