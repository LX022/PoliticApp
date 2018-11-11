package clabersoftware.politicapp.DataBase.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import clabersoftware.politicapp.DataBase.Entity.PoliticianEntity;

@Dao
public interface PoliticianDao {

    @Query("SELECT * FROM politicians WHERE idPolitician = :idPolitician")
    public abstract LiveData<PoliticianEntity> getPoliticianById(Long idPolitician);

    @Query("SELECT * FROM politicians")
    public abstract LiveData<List<PoliticianEntity>> getAllPoliticians();

    @Query("SELECT * FROM politicians WHERE fkParty=:idParty")
    public abstract LiveData<List<PoliticianEntity>> getPoliticianByIdParty(Long idParty);

    @Insert
    public abstract long insert(PoliticianEntity politician);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAll(List<PoliticianEntity> politicians);

    @Update
    public abstract void update(PoliticianEntity politician);

    @Delete
    public abstract void delete(PoliticianEntity politician);

    @Query("DELETE FROM politicians")
    public abstract void deleteAllPoliticians();
}
