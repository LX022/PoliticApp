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
public class VotingLineEntity implements ModelVotingLine, Comparable {

    @PrimaryKey(autoGenerate = true)
    long idVotingLine;
    @ColumnInfo(name = "vote")
    boolean vote;
    @ColumnInfo(name = "fkPolitician")
    Long fkPolitician;
    @ColumnInfo(name = "fkVotingObject")
    long fkVotingObject;

    public VotingLineEntity() {
    }

    public VotingLineEntity(ModelVotingLine votingLine) {
        idVotingLine = votingLine.getIdVotingLine();
        vote = votingLine.getVote();
        fkPolitician = votingLine.getFkPolitician();
        fkVotingObject = votingLine.getFkVotingObject();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof VotingLineEntity)) return false;
        VotingLineEntity o = (VotingLineEntity) obj;
        return o.getIdVotingLine().equals(this.getIdVotingLine());
    }

    @Override
    public Long getIdVotingLine() {
        return idVotingLine;
    }

    public void setIdVotingLine(long idVotingLine){this.idVotingLine = idVotingLine;}

    @Override
    public Long getFkVotingObject() {
        return fkVotingObject;
    }

    public void setFkVotingObject(long fkVotingObject){this.fkVotingObject = fkVotingObject;}

    @Override
    public Long getFkPolitician() {
        return fkPolitician;
    }

    public void setFkPolitician(long fkPolitician){this.fkPolitician = fkPolitician;}

    @Override
    public boolean getVote() {
        return vote;
    }

    public void setVote(boolean vote){this.vote = vote;}

    @Override
    public int compareTo(@NonNull Object o) {
        return toString().compareTo(o.toString());
    }


}
