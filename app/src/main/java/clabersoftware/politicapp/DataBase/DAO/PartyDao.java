package clabersoftware.politicapp.DataBase.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.sqlite.SQLiteConstraintException;

import java.util.List;

import clabersoftware.politicapp.DataBase.Entity.PartyEntity;
@Dao
public interface PartyDao {

    @Query("SELECT * FROM parties WHERE idParty = :id")
    PartyEntity getById(Long id);

    @Query("SELECT * FROM parties")
    List<PartyEntity> getAll();

    @Insert
    Long insert(PartyEntity party);

    @Update
    void update(PartyEntity party);

    @Delete
    void delete(PartyEntity party);

    @Query("DELETE FROM parties")
    void deleteAll();
}
