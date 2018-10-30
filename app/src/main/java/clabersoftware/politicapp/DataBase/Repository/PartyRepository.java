package clabersoftware.politicapp.DataBase.Repository;

import android.arch.lifecycle.LiveData;

import java.util.List;

import clabersoftware.politicapp.DataBase.AppDatabase;
import clabersoftware.politicapp.DataBase.PartyEntity;

public class PartyRepository {
    private static PartyRepository sInstance;

    private final AppDatabase mDatabase;

    private PartyRepository(final AppDatabase database) {
        mDatabase = database;
    }

    public static PartyRepository getInstance(final AppDatabase database) {
        if (sInstance == null) {
            synchronized (PartyRepository.class) {
                if (sInstance == null) {
                    sInstance = new PartyRepository(database);
                }
            }
        }
        return sInstance;
    }

    public LiveData<PartyEntity> getAccount(final Long partyId) {
        return mDatabase.partyDao().getById(partyId);
    }

    public LiveData<List<PartyEntity>> getAccounts() {
        return mDatabase.partyDao().getAll();
    }


    public void insert(final PartyEntity account) {
        mDatabase.partyDao().insert(account);
    }

    public void update(final PartyEntity account) {
        mDatabase.partyDao().update(account);
    }

    public void delete(final PartyEntity account) {
        mDatabase.partyDao().delete(account);
    }

}
