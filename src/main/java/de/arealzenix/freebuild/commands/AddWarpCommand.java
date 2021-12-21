package de.arealzenix.freebuild.commands;

import de.arealzenix.freebuild.FreeBuild;
import de.arealzenix.freebuild.locations.LocationInterface;
import de.chaos.mc.serverapi.utils.stringLibary.AbstractMessages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AddWarpCommand implements CommandExecutor {
    private LocationInterface locationInterface;
    public AddWarpCommand(LocationInterface locationInterface) {
        this.locationInterface = locationInterface;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            locationInterface.addLocation("Warptest", player.getLocation());
            locationInterface.getLocation("Warptest");
            return true;
        } else {
            sender.sendMessage(AbstractMessages.BEAPLAYER);
            return false;
        }
    }
}