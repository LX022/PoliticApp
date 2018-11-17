package clabersoftware.politicapp.DataBase.DAO;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import java.util.List;

import clabersoftware.politicapp.DataBase.Entity.PartyEntity;
@Dao
public interface PartyDao {

    @Query("SELECT * FROM parties WHERE idParty = :id")
    PartyEntity getById(Long id);

    /*
    * Permet d'obtenir l'id d'un party en fonction de son nom. utilisé entre autre pour le Spinner
    */
    @Query("SELECT idParty FROM parties WHERE shortName = :shortName")
    Long getIdByName(String shortName);

    @Query("SELECT * FROM parties")
    List<PartyEntity> getAll();

    @Insert
    Long add(PartyEntity party);

    @Update
    void update(PartyEntity party);

    @Delete
    void delete(PartyEntity party);

    @Query("DELETE FROM parties")
    void deleteAll();
}
