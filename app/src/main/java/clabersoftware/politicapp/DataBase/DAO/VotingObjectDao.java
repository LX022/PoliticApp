package clabersoftware.politicapp.DataBase.DAO;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import clabersoftware.politicapp.DataBase.Entity.VotingObjectEntity;

@Dao
public interface VotingObjectDao {

    /*
    * Cet DAO contient uniquement les query standard
    * */
    @Query("SELECT * FROM votingObjects WHERE idVotingObject = :id")
    VotingObjectEntity getById(Long id);

    @Query("SELECT * FROM votingObjects")
    List<VotingObjectEntity> getAll();

    @Insert
    Long add(VotingObjectEntity votingObject);

    @Update
    void update(VotingObjectEntity votingObject);

    @Delete
    void delete(VotingObjectEntity votingObject);

    @Query("DELETE FROM votingObjects")
    void deleteAll();
}
