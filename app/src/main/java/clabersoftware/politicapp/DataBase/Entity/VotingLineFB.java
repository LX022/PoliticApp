package clabersoftware.politicapp.DataBase.Entity;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class VotingLineFB {

    @Exclude
    String votingObjectLineUid;
    String vote;
    String fkPolitician;
    String fkVotingObject;

    public String getVotingObjectLineUid() {
        return votingObjectLineUid;
    }

    public void setVotingObjectLineUid(String votingObjectLineUid) {
        this.votingObjectLineUid = votingObjectLineUid;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public String getFkPolitician() {
        return fkPolitician;
    }

    public void setFkPolitician(String fkPolitician) {
        this.fkPolitician = fkPolitician;
    }

    public String getFkVotingObject() {
        return fkVotingObject;
    }

    public void setFkVotingObject(String fkVotingObject) {
        this.fkVotingObject = fkVotingObject;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("vote", vote);
        result.put("fkPolitician", fkPolitician);
        result.put("fkVotingObject", fkVotingObject);
        return result;
    }
}
