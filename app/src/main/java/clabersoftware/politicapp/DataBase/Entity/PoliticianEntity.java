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
    @ColumnInfo(name = "password")
    String password;
    @ColumnInfo(name = "lastName")
    String lastName;
    @ColumnInfo(name = "login")
    String login;
    @ColumnInfo(name = "fkParty")
    long fkParty;

    public PoliticianEntity() {
    }

    public PoliticianEntity(String firstName, String lastName,String password, long fkParty, String login) {
        this.firstName = firstName;
        this.password = password;
        this.lastName = lastName;
        this.fkParty = fkParty;
        this.login = login;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getFkParty() {
        return fkParty;
    }

    public void setFkParty(Long fkParty) {
        this.fkParty = fkParty;
    }


}

