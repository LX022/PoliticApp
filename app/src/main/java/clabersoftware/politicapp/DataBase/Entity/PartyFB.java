package clabersoftware.politicapp.DataBase.Entity;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class PartyFB {

    @Exclude
    String partyUid;
    String shortName;
    String longName;

    public String getPartyUid() {
        return partyUid;
    }

    public void setPartyUid(String uid) {
        this.partyUid = uid;
    }

    public String getShortName() {
        if(shortName==null)
            return "";
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getLongName() {
        if(longName==null)
            return "";
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("shortName", shortName);
        result.put("longName", longName);
        return result;
    }
}
