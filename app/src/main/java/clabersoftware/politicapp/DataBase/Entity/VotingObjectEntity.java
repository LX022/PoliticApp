package clabersoftware.politicapp.DataBase.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

import clabersoftware.politicapp.Model.ModelVotingObject;


@Entity(tableName = "votingObjects")
public class VotingObjectEntity implements ModelVotingObject, Comparable{

    @PrimaryKey(autoGenerate = true)
    long idVotingObject;
    @ColumnInfo(name = "entitled")
    String entitled;
    @ColumnInfo(name = "details")
    String details;
    @ColumnInfo(name = "date")
    Date date;
    @ColumnInfo(name = "state")
    Boolean state;

    public VotingObjectEntity(){
    }

    public VotingObjectEntity(ModelVotingObject votingObject){
        idVotingObject = getIdVotingObject();
        entitled = getEntitled();
        details = GetDetails();
        date = getDate();
        state = getState();
    }

    public VotingObjectEntity(String entitled, String details, Date date, Boolean state){
        this.entitled = entitled;
        this.details = details;
        this.date = date;
        this.state=state;
    }

    public String toString() {
        return (entitled);
    }

    @Override
    public Long getIdVotingObject() {
        return null;
    }

    public void setIdVotingObject(@NonNull Long idVotingObject) {
        this.idVotingObject = idVotingObject;
    }

    @Override
    public String getEntitled() {
        return null;
    }

    public void setEntitled(String entitled) {
        this.entitled = entitled;
    }

    @Override
    public String GetDetails() {
        return null;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public Date getDate() {
        return null;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public Boolean getState() {
        return null;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return toString().compareTo(o.toString());
    }

    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof VotingObjectEntity)) return false;
        VotingObjectEntity o = (VotingObjectEntity) obj;
        return o.getIdVotingObject().equals(this.getIdVotingObject());
    }
}
