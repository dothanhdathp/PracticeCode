package com.example.pokedex;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//public class PokemonEvoDataBaseStorage extends SQLiteOpenHelper {
/** Tổng quan
 * Class này sử dụng để xử lý dữ liệu liên quan đến mục tiến hoá của các loài pokemon.
 * Do cây tiến hoá rất là đa dạng và phức tạp nên cần tách ra riêng một class khác.
 * Vẫn như cũ thì đây cũng nên là một class singleton (duy nhất) và thực hiện việc quản lý dữ liệu SQL về pkm
 * */
public class PokemonEvoDataBaseStorage extends SQLiteOpenHelper {
    private Context context;
    // Create new database information
    private static final String DATABASE_NAME = "PokedexDatabase.db";
    private static final int    DATABASE_VERSION = 1;
    /* Database struct */
    private static final String TABLE_NAME = "PKM_EVOLUTION";
    private static final String COLUMN_NATIONAL_ID = "pkm_national_id";
    private static final String COLUMN_NAME = "pkm_name";
    private static final String COLUMN_HP = "pkm_hp";
    private static final String COLUMN_ATTACK = "pkm_atk";
    private static final String COLUMN_DEFENSE = "pkm_def";
    private static final String COLUMN_SPECIAL_ATTACK = "pkm_s_atk";
    private static final String COLUMN_SPECIAL_DEFENSE = "pkm_s_def";
    private static final String COLUMN_SPEED = "pkm_speed";
    private static final String COLUMN_TOTAL_STATS = "pkm_stats_total";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+COLUMN_NATIONAL_ID+" INTERGER PRIMARY KEY, "+
            COLUMN_NAME+" TEXT, "+
            COLUMN_HP+" INTEGER, "+
            COLUMN_ATTACK+" INTEGER, "+
            COLUMN_DEFENSE+" INTEGER, "+
            COLUMN_SPECIAL_ATTACK+" INTEGER, "+
            COLUMN_SPECIAL_DEFENSE+" INTEGER, "+
            COLUMN_SPEED+" INTEGER, "+
            COLUMN_TOTAL_STATS+" INTEGER);";
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // do nothing in here
    }

    public PokemonEvoDataBaseStorage(Context context) {
        // super(context, {database name}, null, {version});
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
}
