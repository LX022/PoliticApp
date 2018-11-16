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
    public abstract PoliticianEntity getPoliticianById(Long idPolitician);

    @Query("SELECT * FROM politicians")
    public abstract List<PoliticianEntity> getAllPoliticians();

    @Query("SELECT * FROM politicians WHERE fkParty=:idParty")
    public abstract List<PoliticianEntity> getPoliticianByIdParty(Long idParty);

    @Query("SELECT password FROM politicians WHERE login = :login")
    public String getPassByLogin(String login);

    @Query("SELECT idPolitician FROM politicians WHERE login = :login")
    public Long getIdByLogin(String login);

    @Insert
    public abstract long add(PoliticianEntity politician);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAll(List<PoliticianEntity> politicians);

    @Update
    public abstract void update(PoliticianEntity politician);

    @Delete
    public abstract void delete(PoliticianEntity politician);

    @Query("DELETE FROM politicians")
    public abstract void deleteAllPoliticians();
}
