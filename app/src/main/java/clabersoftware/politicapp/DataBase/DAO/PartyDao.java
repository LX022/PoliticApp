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
    LiveData<PartyEntity> getById(Long id);

    @Query("SELECT * FROM parties")
    LiveData<List<PartyEntity>> getAll();

    /**
     * This method is used to populate the transaction activity.
     * It returns all OTHER users and their accounts.
     * @param id Id of the client who should be excluded from the list.
     * @return A live data object containing a list of ClientAccounts with
     * containing all clients but the @id.
     */

    /** a voir quoi en faire
    @Transaction
    @Query("SELECT * FROM parties WHERE idParty != :id")
    LiveData<List<ClientWithAccounts>> getOtherClientsWithAccounts(String id);
    **/

    @Insert
    Long insert(PartyEntity party) throws SQLiteConstraintException;

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<PartyEntity> parties);

    @Update
    void update(PartyEntity party);

    @Delete
    void delete(PartyEntity party);

    @Query("DELETE FROM parties")
    void deleteAll();
}
