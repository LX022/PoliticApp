package clabersoftware.politicapp.DataBase.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import clabersoftware.politicapp.DataBase.Entity.VotingObjectEntity;

@Dao
public interface VotingObjectDao {

    @Query("SELECT * FROM votingObjects WHERE idVotingObject = :idVotingObject")
    public abstract LiveData<VotingObjectEntity> getVotingObjectById(Long idVotingObject);

    @Query("SELECT * FROM votingObjects")
    public abstract LiveData<List<VotingObjectEntity>> getAllVotingObjects();

    @Insert
    public abstract long insert(VotingObjectEntity votingObject);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAll(List<VotingObjectEntity> votingObjects);

    @Update
    public abstract void update(VotingObjectEntity votingObject);

    @Delete
    public abstract void delete(VotingObjectEntity votingObject);

    @Query("DELETE FROM votingObjects")
    public abstract void deleteAllVotingObject();
}
