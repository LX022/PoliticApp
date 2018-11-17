package clabersoftware.politicapp.Model;

/*
Modélisation de VotingLine
 */

public interface ModelVotingLine {
    Long getIdVotingLine();
    Long getFkVotingObject();
    Long getFkPolitician();
    boolean getVote();
}
