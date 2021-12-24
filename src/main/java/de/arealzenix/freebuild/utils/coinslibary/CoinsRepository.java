package de.arealzenix.freebuild.utils.coinslibary;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import de.arealzenix.freebuild.utils.daos.CoinsDAO;
import de.arealzenix.freebuild.utils.daos.DAOManager;

import java.sql.SQLException;
import java.util.UUID;

public class CoinsRepository implements CoinsInterface {
    public JdbcPooledConnectionSource connectionSource;
    public DAOManager<CoinsDAO, UUID> daoManager;
    public CoinsRepository(JdbcPooledConnectionSource jdbcPooledConnectionSource) {
        this.connectionSource = jdbcPooledConnectionSource;
        this.daoManager = new DAOManager<CoinsDAO, UUID>(CoinsDAO.class, connectionSource);
    }



    @Override
    public long getCoins(UUID uuid) {
        CoinsDAO coinsDAO = null;
        try {
            coinsDAO = daoManager.getDAO().queryForId(uuid);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        assert coinsDAO != null;
        return coinsDAO.getCoins();
    }

    @Override
    public long setCoins(UUID uuid, long coins) {
        CoinsDAO coinsDAO = new CoinsDAO(uuid, coins);
        try {
            daoManager.getDAO().update(coinsDAO);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return getCoins(uuid);
    }

    @Override
    public long addCoins(UUID uuid, long coins) {
        long l = Math.addExact(getCoins(uuid), coins);
        CoinsDAO coinsDAO = new CoinsDAO(uuid, l);
        try {
            daoManager.getDAO().update(coinsDAO);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return getCoins(uuid);
    }

    @Override
    public long removeCoins(UUID uuid, long coins) {
        long l = Math.subtractExact(getCoins(uuid), coins);
        CoinsDAO coinsDAO = new CoinsDAO(uuid, l);
        try {
            daoManager.getDAO().update(coinsDAO);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return getCoins(uuid);
    }

    @Override
    public boolean hasEnoughCoins(UUID uuid, long amount) {
        if (getCoins(uuid) >= amount) {
            return true;
        } else {
            return false;
        }
    }
}
