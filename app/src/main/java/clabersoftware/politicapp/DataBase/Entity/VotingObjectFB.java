package clabersoftware.politicapp.DataBase.Entity;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class VotingObjectFB {

    @Exclude
    String VotingObjectUid;
    String entitled;
    String details;
    String date;

    public String getVotingObjectUid() {
        return VotingObjectUid;
    }

    public void setVotingObjectUid(String votingObjectUid) {
        VotingObjectUid = votingObjectUid;
    }

    public String getEntitled() {
        return entitled;
    }

    public void setEntitled(String entitled) {
        this.entitled = entitled;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("entitled", entitled);
        result.put("details", details);
        result.put("date", date);
        return result;
    }






}
