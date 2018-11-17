package clabersoftware.politicapp.DataBase.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import clabersoftware.politicapp.Model.ModelParty;

/*
* Objet Party
* table parties
* Primary key autogénérée
* Constructeur sans id
* Constructeur vide
* Geter
* Seter*/

@Entity(tableName = "parties")
public class PartyEntity {

    @PrimaryKey(autoGenerate = true)
    Long idParty;
    @ColumnInfo(name = "color")
    String color;
    @ColumnInfo(name = "shortName")
    String shortName;
    @ColumnInfo(name = "longName")
    String longName;

    public PartyEntity(){

    }

    public PartyEntity(String color, String shortName, String longName){
        this.color = color;
        this.shortName = shortName;
        this.longName = longName;
    }

    public void setIdParty(@NonNull Long idParty) {
        this.idParty = idParty;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public Long getIdParty() {
        return idParty;
    }

    public String getColor() {
        return color;
    }

    public String getShortName() {
        return shortName;
    }

    public String getLongName() {
        return longName;
    }

    public String toString() {
        return longName;
    }
}
