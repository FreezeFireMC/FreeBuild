package de.arealzenix.freebuild.commands;

import de.arealzenix.freebuild.locations.LocationInterface;
import de.chaos.mc.serverapi.utils.stringLibary.AbstractMessages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {
    private final LocationInterface locationInterface;
    public SetSpawnCommand(LocationInterface locationInterface) {
        this.locationInterface = locationInterface;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            if(player.hasPermission("freebuild.admin")){
                locationInterface.addLocation("spawn", player.getLocation());
                locationInterface.getLocation("spawn");
                return true;
            }
        } else {
            sender.sendMessage(AbstractMessages.BEAPLAYER);
            return false;
        }
        return false;
    }
}