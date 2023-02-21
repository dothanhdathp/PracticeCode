package com.example.pokedex;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class help get - search and sort data base on pokemon stats.
 * */
public class PokemonStatDataBaseManager extends SQLiteOpenHelper {
    private Context context;
    final String Tag = "PkmStatDbError";

    /* Singleton */
    private static PokemonStatDataBaseManager singleObject;

    private static final String DATABASE_NAME = "PokedexDatabase.db";
    private static final int    DATABASE_VERSION = 1;
    /* Database struct */
    private static final String TABLE_NAME = "PKM_DETAIL";
    private static final String COLUMN_NATIONAL_ID = "pkm_national_id";
    private static final String COLUMN_NAME = "pkm_name";
    private static final String COLUMN_HP = "pkm_hp";
    private static final String COLUMN_ATTACK = "pkm_atk";
    private static final String COLUMN_DEFENSE = "pkm_def";
    private static final String COLUMN_SPECIAL_ATTACK = "pkm_s_atk";
    private static final String COLUMN_SPECIAL_DEFENSE = "pkm_s_def";
    private static final String COLUMN_SPEED = "pkm_speed";
    private static final String COLUMN_TOTAL_STATS = "pkm_stats_total";

    /** Construct class
     * */
    public PokemonStatDataBaseManager(Context context) {
        // super(context, {database name}, null, {version});
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    /** Singleton Object
     * */
    public static PokemonStatDataBaseManager getInstance(Context ctx)
    {
        if(singleObject == null)
        {
            singleObject = new PokemonStatDataBaseManager(ctx);
        }
        return singleObject;
    }
    public static PokemonStatDataBaseManager getInstance()
    {
        return singleObject;
    }
    /** Pokemon local detail struct
     * */
    public class PokemonInfo {
        public String name;
        public int hp = 0;
        public int atk = 0;
        public int def = 0;
        public int sAtk = 0;
        public int sDef = 0;
        public int speed = 0;
        public int Total;
    }

    HashMap<String, String> capitalCities = new HashMap<String, String>();

    /** Local value
     * */
    int current_pokemon_idx = 0;
    PokemonInfo pkm = null; // This should be a map

    @RequiresApi(api = Build.VERSION_CODES.P)
    public PokemonStatDataBaseManager(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    @Override
    /* It just for test, normally, data base will be prepared. That can't config by our side */
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // In here need check database is exist or not. Exg: if(!data.exist()) -> createTable
        String query = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+COLUMN_NATIONAL_ID+" INTERGER PRIMARY KEY, "+
                COLUMN_NAME+" TEXT, "+
                COLUMN_HP+" INTEGER, "+
                COLUMN_ATTACK+" INTEGER, "+
                COLUMN_DEFENSE+" INTEGER, "+
                COLUMN_SPECIAL_ATTACK+" INTEGER, "+
                COLUMN_SPECIAL_DEFENSE+" INTEGER, "+
                COLUMN_SPEED+" INTEGER, "+
                COLUMN_TOTAL_STATS+" INTEGER);";
        /* Executed sql query */
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // do nothing
    }

    /* It just for test, normally, data base will be prepared. That can't config by our side */
    public boolean addPkmToDataBase(int nId, String pkmName, int hp, int atk, int def, int satk, int sdef, int spd)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();

        /* Add pokemon detail follow this content */
        content.put(COLUMN_NATIONAL_ID, nId);
        content.put(COLUMN_NAME, pkmName);
        content.put(COLUMN_HP, hp);
        content.put(COLUMN_ATTACK, atk);
        content.put(COLUMN_DEFENSE, def);
        content.put(COLUMN_SPECIAL_ATTACK, satk);
        content.put(COLUMN_SPECIAL_DEFENSE, sdef);
        content.put(COLUMN_SPEED, spd);
        content.put(COLUMN_TOTAL_STATS, hp+atk+def+satk+sdef+spd);

        long result = db.insert(TABLE_NAME, null, content);
        if(result == -1) // Add false
        {
            Toast.makeText(context, "Add Suggest!", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            Toast.makeText(context, "Add False!", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    /* It just for test, normally, data base will be prepared. That can't config by our side */
    public void initTestDatabase()
    {
        /* This function just for test */
        this.addPkmToDataBase(1, "Bulbasaur", 45,49,49,65,65,45);
        this.addPkmToDataBase(2, "Ivysaur", 60,62,63,80,80,60);
        this.addPkmToDataBase(3, "Venusaur", 80,82,83,100,100,80);
        this.addPkmToDataBase(4, "Charmander", 39,52,43,60,50,65);
        this.addPkmToDataBase(5, "Charmeleon", 58,64,58,80,65,80);
        this.addPkmToDataBase(6, "Charizard", 78,84,78,109,85,100);
        this.addPkmToDataBase(7, "Squirtle", 44,48,65,50,64,43);
        this.addPkmToDataBase(8, "Wartortle", 59,63,80,65,80,58);
        this.addPkmToDataBase(9, "Blastoise", 79,83,100,85,105,78);
    }

    private Cursor runDBQueryCommand(String query)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor new_cursor = null;
        if(db != null)
        {
            new_cursor = db.rawQuery(query, null);
        }
        return new_cursor;
    }

    public Cursor getAllPokemonName()
    {
        String query = "SELECT "+COLUMN_NAME+" FROM "+TABLE_NAME+";";
        Cursor cursor = runDBQueryCommand(query);
        if(cursor.getCount() == 0) {
            initTestDatabase();
        }
        return cursor;
    }
    public String getPokemonNameByIndex(int nIdx)
    {
        if(current_pokemon_idx == nIdx)
        {
            // this pkm had created, return previous data
        } else {
            String query = "SELECT "+COLUMN_NAME+" FROM "+TABLE_NAME+" WHERE "+COLUMN_NATIONAL_ID+"="+String.valueOf(nIdx)+";";
            Cursor cursor = runDBQueryCommand(query);
            cursor.moveToFirst();
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            return name;
        }
        return "";
    }
    public List<Integer> getListStatsByIndex(int nIdx)
    {
        List<Integer> newList = new ArrayList<Integer>();
        if(current_pokemon_idx == nIdx)
        {
            // this pkm had created, return previous data
        } else {
            String query = "SELECT * FROM "+TABLE_NAME+" WHERE "+COLUMN_NATIONAL_ID+"="+String.valueOf(nIdx)+";";
            Cursor cursor = runDBQueryCommand(query);
            PokemonInfo pkmIf = new PokemonInfo();
            if(cursor != null && cursor.getCount() != 0)
            {
                cursor.moveToFirst();
                int hp = cursor.getInt(cursor.getColumnIndex(COLUMN_HP));
                int atk = cursor.getInt(cursor.getColumnIndex(COLUMN_ATTACK));
                int def = cursor.getInt(cursor.getColumnIndex(COLUMN_DEFENSE));
                int satk = cursor.getInt(cursor.getColumnIndex(COLUMN_SPECIAL_ATTACK));
                int sdef = cursor.getInt(cursor.getColumnIndex(COLUMN_SPECIAL_DEFENSE));
                int spd = cursor.getInt(cursor.getColumnIndex(COLUMN_SPEED));
                int total = cursor.getInt(cursor.getColumnIndex(COLUMN_TOTAL_STATS));

                newList.add(hp);
                newList.add(atk);
                newList.add(def);
                newList.add(satk);
                newList.add(sdef);
                newList.add(spd);
                newList.add(total);
            } else {
                Log.d(Tag, "Wrong pkm National id: "+nIdx);
            }
        }
        return newList;
    }
}
