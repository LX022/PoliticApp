package clabersoftware.politicapp.DataBase.Repository;

import android.arch.lifecycle.LiveData;

import java.util.List;

import clabersoftware.politicapp.DataBase.AppDatabase;
import clabersoftware.politicapp.DataBase.Entity.VotingLineEntity;

public class VotingLineRepository {
    private static VotingLineRepository sInstance;

    private final AppDatabase mDatabase;

    private VotingLineRepository(final AppDatabase database) {
        mDatabase = database;
    }

    public static VotingLineRepository getInstance(final AppDatabase database) {
        if (sInstance == null) {
            synchronized (VotingLineRepository.class) {
                if (sInstance == null) {
                    sInstance = new VotingLineRepository(database);
                }
            }
        }
        return sInstance;
    }

    public LiveData<VotingLineEntity> getVotingLineById(final Long idVotingLine) {
        return mDatabase.votingLineDao().getVotingLineById(idVotingLine);
    }

    public LiveData<List<VotingLineEntity>> getAllVotingLines() {
        return mDatabase.votingLineDao().getAllVotingLines();
    }

    public LiveData<List<VotingLineEntity>> getVotingLineByIdVotingObject(final long idVotingObject) {
        return mDatabase.votingLineDao().getVotingLineByIdVotingObject(idVotingObject);
    }

    public void insert(final VotingLineEntity votingLine) {
        mDatabase.votingLineDao().insert(votingLine);
    }

    public void update(final VotingLineEntity votingLine) {
        mDatabase.votingLineDao().update(votingLine);
    }

    public void delete(final VotingLineEntity votingLine) {
        mDatabase.votingLineDao().delete(votingLine);
    }
}
