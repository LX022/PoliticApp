package clabersoftware.politicapp.DataBase;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "parties", primaryKeys = {"idParty"})
public class PartyEntity implements ModelParty, Comparable {


    //@PrimaryKey(autoGenerate = true)
    Long idParty;
    @ColumnInfo(name = "color")
    String color;
    @ColumnInfo(name = "shortName")
    String shortName;
    @ColumnInfo(name = "longName")
    String longName;


    public PartyEntity(){
    }

    public PartyEntity(ModelParty party){
        idParty = getIdParty();
        color = getColor();
        shortName = getShortName();
        longName = getLongName();
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

    @Override
    public Long getIdParty() {
        return null;
    }

    @Override
    public String getColor() {
        return null;
    }

    @Override
    public String getShortName() {
        return null;
    }

    @Override
    public String getLongName() {
        return null;
    }

    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof PartyEntity)) return false;
        PartyEntity o = (PartyEntity) obj;
        return o.getIdParty().equals(this.getIdParty());
    }

    @Override
    public String toString() {
        return longName;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return toString().compareTo(o.toString());
    }
}
