package clabersoftware.politicapp.DataBase.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import clabersoftware.politicapp.DataBase.Entity.VotingLineEntity;

@Dao
public interface VotingLineDao {
    @Query("SELECT * FROM votingLines WHERE idVotingLine = :idVotingLine")
    public abstract VotingLineEntity getVotingLineById(Long idVotingLine);

    @Query("SELECT * FROM votingLines WHERE fkVotingObject = :idVotingObject")
    public abstract List<VotingLineEntity> getVotingLineByIdVotingObject(Long idVotingObject);

    @Query("SELECT * FROM votingLines")
    public abstract List<VotingLineEntity> getAllVotingLines();

    @Insert
    public abstract long insert(VotingLineEntity votingLine);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAll(List<VotingLineEntity> votingLines);

    @Update
    public abstract void update(VotingLineEntity votingLine);

    @Delete
    public abstract void delete(VotingLineEntity votingLine);

    @Query("DELETE FROM votingLines")
    public abstract void deleteAll();

    @Query("SELECT * FROM votingLines WHERE fkPolitician = :idPolitician")
    public abstract List<VotingLineEntity> getVotingLineByPolitician(Long idPolitician);
}
