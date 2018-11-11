package clabersoftware.politicapp.DataBase.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import clabersoftware.politicapp.Model.ModelPolitician;
import clabersoftware.politicapp.UserInterface.Politician.PoliticianOld;

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
public class PoliticianEntity implements ModelPolitician, Comparable {

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

    public PoliticianEntity(ModelPolitician politician) {
        idPolitician = politician.getIdPolitician();
        firstName = politician.getFirstName();
        lastName = politician.getLastName();
        fkParty = politician.getFkParty();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof PoliticianEntity)) return false;
        PoliticianEntity o = (PoliticianEntity) obj;
        return o.getIdPolitician().equals(this.getIdPolitician());
    }

    @Override
    public String toString() {
        return (firstName + " " + lastName);
    }

    @Override
    public Long getIdPolitician() {
        return null;
    }
    public void setIdPolitician(@NonNull Long idPolitician) {
        this.idPolitician = idPolitician;
    }

    @Override
    public String getFirstName() {
        return null;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return null;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public Long getFkParty() {
        return null;
    }

    public void setFkParty(Long fkParty) {
        this.fkParty = fkParty;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return toString().compareTo(o.toString());
    }
}
