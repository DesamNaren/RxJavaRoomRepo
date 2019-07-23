package com.example.roomdb;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

@Database(entities = {OtItem.class}, version = 1, exportSchema = true)
public abstract class OTDataBase extends RoomDatabase {

    private static final String ICAD_DB = "ICAD_DATABASE";
    private static OTDataBase instance;

    static synchronized OTDataBase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context, OTDataBase.class, ICAD_DB)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract OTDao getOtDao();

}
