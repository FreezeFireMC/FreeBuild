package de.arealzenix.freebuild;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import de.arealzenix.freebuild.commands.AddWarpCommand;
import de.arealzenix.freebuild.commands.SpawnCommand;
import de.arealzenix.freebuild.commands.WarpCommand;
import de.arealzenix.freebuild.listener.ConnectionListener;
import de.arealzenix.freebuild.locations.LocationInterface;
import de.arealzenix.freebuild.locations.LocationRepository;
import de.arealzenix.freebuild.utils.FreeBuildPlayerLanguage;
import de.arealzenix.freebuild.utils.coinslibary.CoinsInterface;
import de.arealzenix.freebuild.utils.coinslibary.CoinsRepository;
import de.arealzenix.freebuild.utils.languageLibary.LanguageInterface;
import de.arealzenix.freebuild.utils.languageLibary.LanguageRepository;
import de.arealzenix.freebuild.utils.megaUtils.menu.MenuFactory;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public final class FreeBuild extends JavaPlugin {

    @Getter private CoinsInterface coinsInterface;
    @Getter private LocationInterface locationInterface;
    private LanguageInterface languageInterface;
    @Getter private static HashMap<UUID, FreeBuildPlayerLanguage> onlinePlayers;
    private MenuFactory menuFactory;
    private JdbcPooledConnectionSource source;


    @Getter private static FreeBuild instance;

    @Override
    public void onEnable() {
        instance = this;
        source = new JdbcPooledConnectionSource();
        source.setUrl("jdbc:mysql://localhost:3306/ServerAPI");
        source.setUsername("Admin");
        source.setPassword("Orkaan55");
        try {
            source.initialize();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        coinsInterface = new CoinsRepository(source);
        menuFactory = MenuFactory.register(this);
        locationInterface = new LocationRepository();
        languageInterface = new LanguageRepository(source);

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new ConnectionListener(), this);

        getCommand("addwarp").setExecutor(new AddWarpCommand(locationInterface));
        getCommand("spawn").setExecutor(new SpawnCommand(locationInterface));
        getCommand("warp").setExecutor(new WarpCommand(menuFactory, locationInterface));
    }

    @Override
    public void onDisable() {
        try {
            source.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static FreeBuild getInstance() {
        return instance;
    }
}
