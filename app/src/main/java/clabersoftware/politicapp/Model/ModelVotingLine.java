package clabersoftware.politicapp.Model;

public interface ModelVotingLine {
    Long getIdVotingLine();
    Long getFkVotingObject();
    Long getFkPolitician();
    boolean getVote();
}
