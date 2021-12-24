package de.arealzenix.freebuild.utils.languageLibary;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import de.chaos.mc.serverapi.ServerAPIBukkitMain;
import de.chaos.mc.serverapi.utils.daos.DAOManager;
import de.chaos.mc.serverapi.utils.daos.PlayerDAO;
import de.chaos.mc.serverapi.utils.playerlibary.languageLibary.LanguageType;
import de.chaos.mc.serverapi.utils.playerlibary.languageLibary.PlayerLanguage;
import de.chaos.mc.serverapi.utils.playerlibary.languageLibary.translations.EnglishTranslation;
import de.chaos.mc.serverapi.utils.playerlibary.languageLibary.translations.FrenchTranslation;
import de.chaos.mc.serverapi.utils.playerlibary.languageLibary.translations.GermanTranslation;

import java.sql.SQLException;
import java.util.UUID;

public class LanguageRepository implements LanguageInterface {
    public JdbcPooledConnectionSource connectionSource;
    public DAOManager<PlayerDAO, UUID> daoManager;
    public LanguageRepository(JdbcPooledConnectionSource jdbcPooledConnectionSource) {
        this.connectionSource = jdbcPooledConnectionSource;
        this.daoManager = new DAOManager<PlayerDAO, UUID>(PlayerDAO.class, connectionSource);
    }

    @Override
    public LanguageType getLanguageType(UUID uuid) {
        PlayerDAO playerDAO = null;
        try {
            playerDAO = daoManager.getDAO().queryForId(uuid);
            if (playerDAO.getLanguageType().equalsIgnoreCase(LanguageType.DE.getLanguageType())) {
                return LanguageType.DE;
            }
            if (playerDAO.getLanguageType().equalsIgnoreCase(LanguageType.EG.getLanguageType())) {
                return LanguageType.EG;
            }
            if (playerDAO.getLanguageType().equalsIgnoreCase(LanguageType.FR.getLanguageType())) {
                return LanguageType.FR;
            }
            if (playerDAO.getLanguageType().equalsIgnoreCase(LanguageType.OTHER.getLanguageType())) {
                return LanguageType.OTHER;
            }
            if (playerDAO == null) {
                return LanguageType.EG;
            }
            return LanguageType.DE;
        } catch (SQLException exception) {
            return LanguageType.EG;
        }
    }

    @Override
    public LanguageType setLanguageType(UUID uuid, LanguageType type) {
        try {
            PlayerDAO playerDAO = daoManager.getDAO().queryForId(uuid);
            playerDAO.setLanguageType(type.getLanguageType());
            daoManager.getDAO().createOrUpdate(playerDAO);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return type;
    }

    @Override
    public void loadPlayerLanguage(UUID uuid) {
        PlayerLanguage playerLanguage = null;
        switch (this.getLanguageType(uuid)) {
            case EG:
                playerLanguage = PlayerLanguage.builder()
                        .uuid(uuid)
                        .languageType(LanguageType.EG)
                        .NOPERMISSION(EnglishTranslation.NOPERMISSION)
                        .giveAnVailGamemode(EnglishTranslation.giveAnVailGamemode)
                        .commandDoesntExist(EnglishTranslation.commandDoesntExist())
                        .playerNotOnline(EnglishTranslation.playerNotOnline)
                        .plsGiveValidCoinsAmount(EnglishTranslation.plsGiveValidAmount)
                        .notEnoughCoins(EnglishTranslation.notEnoughCoins)
                        .coins(EnglishTranslation.COINS)
                        .build();
                ServerAPIBukkitMain.getOnlinePlayers().put(uuid, playerLanguage);
                break;
            case DE:
                playerLanguage = PlayerLanguage.builder()
                        .uuid(uuid)
                        .languageType(LanguageType.DE)
                        .NOPERMISSION(GermanTranslation.NOPERMISSION)
                        .giveAnVailGamemode(GermanTranslation.giveAnVailGamemode)
                        .commandDoesntExist(GermanTranslation.commandDoesntExist())
                        .playerNotOnline(GermanTranslation.playerNotOnline)
                        .plsGiveValidCoinsAmount(GermanTranslation.plsGiveValidAmount)
                        .notEnoughCoins(GermanTranslation.notEnoughCoins)
                        .coins(GermanTranslation.COINS)
                        .build();
                ServerAPIBukkitMain.getOnlinePlayers().put(uuid, playerLanguage);
                break;
            case FR:
                playerLanguage = PlayerLanguage.builder()
                        .uuid(uuid)
                        .languageType(LanguageType.FR)
                        .NOPERMISSION(FrenchTranslation.NOPERMISSION)
                        .giveAnVailGamemode(FrenchTranslation.giveAnVailGamemode)
                        .commandDoesntExist(FrenchTranslation.commandDoesntExist())
                        .playerNotOnline(FrenchTranslation.playerNotOnline)
                        .plsGiveValidCoinsAmount(FrenchTranslation.plsGiveValidAmount)
                        .notEnoughCoins(FrenchTranslation.notEnoughCoins)
                        .coins(FrenchTranslation.COINS)
                        .build();
                ServerAPIBukkitMain.getOnlinePlayers().put(uuid, playerLanguage);
                break;
            case OTHER:
                playerLanguage = PlayerLanguage.builder()
                        .uuid(uuid)
                        .languageType(LanguageType.OTHER)
                        .NOPERMISSION(EnglishTranslation.NOPERMISSION)
                        .giveAnVailGamemode(EnglishTranslation.giveAnVailGamemode)
                        .commandDoesntExist(EnglishTranslation.commandDoesntExist())
                        .playerNotOnline(EnglishTranslation.playerNotOnline)
                        .plsGiveValidCoinsAmount(EnglishTranslation.plsGiveValidAmount)
                        .notEnoughCoins(EnglishTranslation.notEnoughCoins)
                        .coins(EnglishTranslation.COINS)
                        .build();
                ServerAPIBukkitMain.getOnlinePlayers().put(uuid, playerLanguage);
                break;

        }
    }
}
