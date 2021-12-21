package de.arealzenix.freebuild.commands;

import de.arealzenix.freebuild.locations.LocationInterface;
import de.chaos.mc.serverapi.utils.stringLibary.AbstractMessages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    private LocationInterface locationInterface;
    public SpawnCommand(LocationInterface locationInterface) {
        this.locationInterface = locationInterface;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            player.teleport(locationInterface.getLocation("spawn"));
            return true;
        } else {
            sender.sendMessage(AbstractMessages.BEAPLAYER);
            return false;
        }
    }
}
