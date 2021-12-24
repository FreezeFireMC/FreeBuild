package de.arealzenix.freebuild.utils.daos;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DatabaseTable(tableName = "Coins")
public class CoinsDAO {
    public static final String PLAYER_UUID_FIELD = "UUID";
    public static final String COINS_FIELD = "COINS";

    @DatabaseField(id = true, columnName = PLAYER_UUID_FIELD)
    public UUID uuid;

    @DatabaseField(columnName = COINS_FIELD)
    public long coins;
}