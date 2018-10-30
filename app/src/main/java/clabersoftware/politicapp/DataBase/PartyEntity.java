package clabersoftware.politicapp.DataBase;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.support.annotation.NonNull;


@Entity(tableName = "parties", primaryKeys = {"idParty"})
public class PartyEntity implements ModelParty, Comparable {

    @NonNull
    Long idParty;
    @ColumnInfo(name = "color")
    int color;
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

    public PartyEntity(@NonNull Long idParty, int color, String shortName, String longName){
        this.idParty = idParty;
        this.color = color;
        this.shortName = shortName;
        this.longName = longName;
    }

    public void setIdParty(@NonNull Long idParty) {
        this.idParty = idParty;
    }

    public void setColor(int color) {
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
    public int getColor() {
        return 0;
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
