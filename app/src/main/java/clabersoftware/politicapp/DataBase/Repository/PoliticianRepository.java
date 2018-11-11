package clabersoftware.politicapp.DataBase.Repository;

import android.arch.lifecycle.LiveData;

import java.util.List;

import clabersoftware.politicapp.DataBase.AppDatabase;
import clabersoftware.politicapp.DataBase.Entity.PoliticianEntity;

public class PoliticianRepository {
    private static PoliticianRepository sInstance;

    private final AppDatabase mDatabase;

    private PoliticianRepository(final AppDatabase database) {
        mDatabase = database;
    }

    public static PoliticianRepository getInstance(final AppDatabase database) {
        if (sInstance == null) {
            synchronized (PoliticianRepository.class) {
                if (sInstance == null) {
                    sInstance = new PoliticianRepository(database);
                }
            }
        }
        return sInstance;
    }

    public LiveData<PoliticianEntity> getPolitician(final Long idPolitician) {
        return mDatabase.politicianDao().getPoliticianById(idPolitician);
    }

    public LiveData<List<PoliticianEntity>> getAllPoliticians() {
        return mDatabase.politicianDao().getAllPoliticians();
    }

    public LiveData<List<PoliticianEntity>> getPoliticianByIdParty(final Long idParty) {
        return mDatabase.politicianDao().getPoliticianByIdParty(idParty);
    }

    public void insert(final PoliticianEntity politician) {
        mDatabase.politicianDao().insert(politician);
    }

    public void update(final PoliticianEntity politician) {
        mDatabase.politicianDao().update(politician);
    }

    public void delete(final PoliticianEntity politician) {
        mDatabase.politicianDao().delete(politician);
    }

}
