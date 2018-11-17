package clabersoftware.politicapp.DataBase.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

import clabersoftware.politicapp.Model.ModelVotingObject;

/*
 * Objet VotingObject
 * table votingObjects
 * Primary key autogénérée
 * Constructeur sans id
 * Constructeur vide
 * Geter
 * Seter*/

@Entity(tableName = "votingObjects")
public class VotingObjectEntity {

    @PrimaryKey(autoGenerate = true)
    long idVotingObject;
    @ColumnInfo(name = "entitled")
    String entitled;
    @ColumnInfo(name = "details")
    String details;
    @ColumnInfo(name = "date")
    String date;


    public VotingObjectEntity(){
    }

    public VotingObjectEntity(String entitled, String details, String date){
        this.entitled = entitled;
        this.details = details;
        this.date = date;
    }

    public String toString() {
        return (entitled);
    }


    public Long getIdVotingObject() {
        return idVotingObject;
    }

    public void setIdVotingObject(@NonNull Long idVotingObject) {
        this.idVotingObject = idVotingObject;
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

    
}
