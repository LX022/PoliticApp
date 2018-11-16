package clabersoftware.politicapp.DataBase.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import clabersoftware.politicapp.Model.ModelPolitician;

@Entity(tableName = "politicians",
        foreignKeys =
        @ForeignKey(
                entity = PartyEntity.class,
                parentColumns = "idParty",
                childColumns = "fkParty",
                //Nous avons mis no_action voir à le supprimer si ça bug
                onDelete = ForeignKey.NO_ACTION
        ),
        indices = {
                @Index(
                        value = {"fkParty"}
                )}
)
public class PoliticianEntity {

    @PrimaryKey(autoGenerate = true)
    long idPolitician;
    @ColumnInfo(name = "firstName")
    String firstName;
    @ColumnInfo(name = "lastName")
    String lastName;
    @ColumnInfo(name = "fkParty")
    long fkParty;

    public PoliticianEntity() {
    }

    public PoliticianEntity(String firstName, String lastName, long fkParty) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fkParty = fkParty;
    }


    @Override
    public String toString() {
        return (firstName + " " + lastName);
    }


    public Long getIdPolitician() {
        return idPolitician;
    }

    public void setIdPolitician(@NonNull Long idPolitician) {
        this.idPolitician = idPolitician;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public Long getFkParty() {
        return fkParty;
    }

    public void setFkParty(Long fkParty) {
        this.fkParty = fkParty;
    }


}

