package com.example.pokedex;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.List;

/**
 * DataBaseManager là hàm tổng cầm quyền kiểm soát tất cả các class sử dụng đến database khác.
 * Việc tạo ra hàm này có mục đích điều khiển luồng dữ liệu data giữa các class sington khác trong cơ sở dữ liệu, tránh cho việc xung đột chương trình trong lúc hoạt động.
 * Nếu có thêm sửa, xoá hoặc thay đổi cơ sở dữ liệu thì điều này là cần thiết.
 * Viết trước rồi sau này tính.
 * DatabaseManager nên được phát triển theo hướng singleton
 * */
public class DataBaseManager {
    String Tag = "PkmDataMng";
    /* Singleton Object */
    private static DataBaseManager singleObject = null;
    /* Local data but i don't know what that mean :D */
    Context context = null;
//    PokemonStatDataBaseManager mPkmStatDb = null;
    PokemonStatDataBaseManager.PokemonInfo current_pkm = null;

    public static DataBaseManager getInstance(Context ctx)
    {
        if (singleObject == null)
        {
            singleObject = new DataBaseManager(ctx);
        }
        return singleObject;
    }
    public static DataBaseManager getInstance()
    {
        return singleObject;
    }

    private DataBaseManager(Context ctx)
    {
        DataBaseManager.this.context = ctx;
        PokemonStatDataBaseManager.getInstance(ctx);
    }

    public Cursor getAllPokemonName()
    {
        if(PokemonStatDataBaseManager.getInstance() != null) {
            Cursor cursor = PokemonStatDataBaseManager.getInstance().getAllPokemonName();
            if (cursor != null) {
                return cursor;
            } else {
                Log.d(Tag, "Error: data base had been init or created");
            }
        } else {
            Log.d(Tag, "Class missing! Pls run create new");
        }
        return null;
    }

    public void initTestDatabase() {
        if(PokemonStatDataBaseManager.getInstance() != null) {
            PokemonStatDataBaseManager.getInstance().initTestDatabase();
        }
    }

    public List<Integer> getListStatsByIndex(int nIdx) {
        List<Integer> listStats = PokemonStatDataBaseManager.getInstance().getListStatsByIndex(nIdx);
        return listStats;
    }

    public String getPokemonNameByIndex(int nIdx) {
        String name = PokemonStatDataBaseManager.getInstance().getPokemonNameByIndex(nIdx);
        return name;
    }
}
