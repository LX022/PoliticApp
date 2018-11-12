package clabersoftware.politicapp.DataBase;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;
import java.util.concurrent.Executors;

import clabersoftware.politicapp.DataBase.DAO.PartyDao;
import clabersoftware.politicapp.DataBase.DAO.PoliticianDao;
import clabersoftware.politicapp.DataBase.DAO.VotingLineDao;
import clabersoftware.politicapp.DataBase.DAO.VotingObjectDao;
import clabersoftware.politicapp.DataBase.Entity.PartyEntity;
import clabersoftware.politicapp.DataBase.Entity.PoliticianEntity;
import clabersoftware.politicapp.DataBase.Entity.VotingLineEntity;
import clabersoftware.politicapp.DataBase.Entity.VotingObjectEntity;

@Database(entities = {PartyEntity.class, PoliticianEntity.class, VotingObjectEntity.class, VotingLineEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "GovernmentDataBase";
    private static AppDatabase INSTANCE;
    public abstract PartyDao partyDao();
    public abstract PoliticianDao politicianDao();
    public abstract VotingObjectDao votingObjectDao();
    public abstract VotingLineDao votingLineDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }
}
