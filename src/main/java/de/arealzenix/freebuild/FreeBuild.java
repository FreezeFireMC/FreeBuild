package de.arealzenix.freebuild;

import de.arealzenix.freebuild.commands.AddWarpCommand;
import de.arealzenix.freebuild.commands.SpawnCommand;
import de.arealzenix.freebuild.commands.WarpCommand;
import de.arealzenix.freebuild.locations.LocationInterface;
import de.arealzenix.freebuild.locations.LocationRepository;
import de.arealzenix.freebuild.utils.FreeBuildPlayerLanguage;
import de.arealzenix.freebuild.utils.megaUtils.menu.MenuFactory;
import de.chaos.mc.serverapi.api.ServerAPI;
import de.chaos.mc.serverapi.utils.coinslibary.CoinsInterface;
import de.chaos.mc.serverapi.utils.playerlibary.languageLibary.LanguageInterface;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class FreeBuild extends JavaPlugin {

    @Getter private ServerAPI serverAPI;
    @Getter private CoinsInterface coinsInterface;
    @Getter private LocationInterface locationInterface;
    private LanguageInterface languageInterface;
    @Getter private static HashMap<UUID, FreeBuildPlayerLanguage> onlinePlayers;
    private MenuFactory menuFactory;

    @Getter private static FreeBuild instance;

    @Override
    public void onEnable() {
        serverAPI = new ServerAPI();
        coinsInterface = serverAPI.getCoinsInterface();
        menuFactory = MenuFactory.register(this);
        locationInterface = new LocationRepository();

        instance = this;

        getCommand("addwarp").setExecutor(new AddWarpCommand(locationInterface));
        getCommand("spawn").setExecutor(new SpawnCommand(locationInterface));
        getCommand("warp").setExecutor(new WarpCommand(menuFactory, locationInterface));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public static FreeBuild getInstance() {
        return instance;
    }
}
