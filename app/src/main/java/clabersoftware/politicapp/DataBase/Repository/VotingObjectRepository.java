package clabersoftware.politicapp.DataBase.Repository;

import android.arch.lifecycle.LiveData;

import java.util.List;

import clabersoftware.politicapp.DataBase.AppDatabase;
import clabersoftware.politicapp.DataBase.Entity.VotingObjectEntity;

public class VotingObjectRepository {

    private static VotingObjectRepository sInstance;

    private final AppDatabase mDatabase;

    private VotingObjectRepository(final AppDatabase database) {
        mDatabase = database;
    }

    public static VotingObjectRepository getInstance(final AppDatabase database) {
        if (sInstance == null) {
            synchronized (VotingObjectRepository.class) {
                if (sInstance == null) {
                    sInstance = new VotingObjectRepository(database);
                }
            }
        }
        return sInstance;
    }

    public LiveData<VotingObjectEntity> getVotingObject(final Long idVotingObject) {
        return mDatabase.votingObjectDao().getVotingObjectById(idVotingObject);
    }

    public LiveData<List<VotingObjectEntity>> getAllVotingObjects() {
        return mDatabase.votingObjectDao().getAllVotingObjects();
    }

    public void insert(final VotingObjectEntity votingObject) {
        mDatabase.votingObjectDao().insert(votingObject);
    }

    public void update(final VotingObjectEntity votingObject) {
        mDatabase.votingObjectDao().update(votingObject);
    }

    public void delete(final VotingObjectEntity votingObject) {
        mDatabase.votingObjectDao().delete(votingObject);
    }
}
