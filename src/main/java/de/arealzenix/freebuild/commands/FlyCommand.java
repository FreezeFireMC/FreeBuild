package de.arealzenix.freebuild.commands;

import de.chaos.mc.serverapi.utils.stringLibary.AbstractMessages;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;


public class FlyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            final Player player = (Player) sender;
            if(player.hasPermission("freebuild.fly")){
                if(args.length == 0){
                    if(!player.getAllowFlight()){
                        player.setAllowFlight(true);
                        player.setFlying(true);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1 ,1));
                        player.playSound(player.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1,1);
                    }else {
                        player.setAllowFlight(false);
                        player.setFlying(false);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1 ,1));
                        player.playSound(player.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, 1,1);
                    }
                }else if(args.length == 1){
                    final Player target = Bukkit.getPlayer(args[0]);
                    if(target == null){
                        player.sendMessage("nope");
                        return true;
                    }
                    if(!target.getAllowFlight()){
                        target.setAllowFlight(true);
                        target.setFlying(true);
                        target.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1 ,1));
                        target.playSound(target.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1,1);
                    }else {
                        target.setAllowFlight(false);
                        target.setFlying(false);
                        target.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1 ,1));
                        target.playSound(target.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, 1,1);
                    }

                }else {
                    player.sendMessage(AbstractMessages.wrongSyntax("/fly [Player]"));
                }
            }

        }
        return false;
    }
}
