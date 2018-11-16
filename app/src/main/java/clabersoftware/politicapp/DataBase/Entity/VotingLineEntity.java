package clabersoftware.politicapp.DataBase.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import clabersoftware.politicapp.Model.ModelVotingLine;

@Entity(tableName = "votingLines",
        foreignKeys ={
        @ForeignKey(
                entity = PoliticianEntity.class,
                parentColumns = "idPolitician",
                childColumns = "fkPolitician"

        ),
        @ForeignKey(
                entity = VotingObjectEntity.class,
                parentColumns = "idVotingObject",
               childColumns = "fkVotingObject"
        )
                },
        indices = {
                @Index(
                        value = {"fkPolitician"}
                ),
                @Index(
                     value = {"fkVotingObject"}
                )
        }
)
public class VotingLineEntity {

    @PrimaryKey(autoGenerate = true)
    long idVotingLine;
    @ColumnInfo(name = "vote")
    String vote;
    @ColumnInfo(name = "fkPolitician")
    long fkPolitician;
    @ColumnInfo(name = "fkVotingObject")
    long fkVotingObject;

    public VotingLineEntity() {
    }

    public VotingLineEntity(String vote, long fkPolitician, long fkVotingObject) {
        this.vote=vote;
        this.fkPolitician=fkPolitician;
        this.fkVotingObject=fkVotingObject;
    }

    public Long getIdVotingLine() {
        return idVotingLine;
    }

    public void setIdVotingLine(long idVotingLine){this.idVotingLine = idVotingLine;}


    public Long getFkVotingObject() {
        return fkVotingObject;
    }

    public void setFkVotingObject(long fkVotingObject){this.fkVotingObject = fkVotingObject;}


    public Long getFkPolitician() {
        return fkPolitician;
    }

    public void setFkPolitician(long fkPolitician){this.fkPolitician = fkPolitician;}


    public String getVote() {
        return vote;
    }

    public void setVote(String vote){this.vote = vote;}

}
