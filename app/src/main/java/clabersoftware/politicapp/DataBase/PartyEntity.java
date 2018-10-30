package clabersoftware.politicapp.DataBase;

import android.support.annotation.NonNull;


public class PartyEntity implements ModelParty, Comparable {


    Long idParty;
    int color;
    String shortName;
    String longName;

    public PartyEntity(){

    }

    public PartyEntity(int color, String shortName, String longName){
        this.color = color;
        this.shortName = shortName;
        this.longName = longName;
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

    @Override
    public int compareTo(@NonNull Object o) {
        return 0;
    }
}
