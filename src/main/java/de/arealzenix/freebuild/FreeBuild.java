package de.arealzenix.freebuild;

import de.arealzenix.freebuild.commands.SetSpawnCommand;
import de.arealzenix.freebuild.commands.FlyCommand;
import de.arealzenix.freebuild.locations.LocationInterface;
import de.arealzenix.freebuild.locations.LocationRepository;
import de.chaos.mc.serverapi.api.ServerAPI;
import de.chaos.mc.serverapi.utils.coinslibary.CoinsInterface;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class FreeBuild extends JavaPlugin {

    @Getter private ServerAPI serverAPI;
    @Getter private CoinsInterface coinsInterface;
    @Getter private LocationInterface locationInterface;


    private static FreeBuild instance;

    @Override
    public void onEnable() {
        serverAPI = new ServerAPI();
        coinsInterface = serverAPI.getCoinsInterface();

        locationInterface = new LocationRepository();

        instance = this;

        init();

    }

    private void init(){
        getCommand("setspawn").setExecutor(new SetSpawnCommand(locationInterface));
        getCommand("fly").setExecutor(new FlyCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public static FreeBuild getInstance() {
        return instance;
    }
}
