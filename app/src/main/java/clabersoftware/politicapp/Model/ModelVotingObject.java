package clabersoftware.politicapp.Model;

import java.util.Date;

/*
Modélisation de Voting Object
 */

public interface ModelVotingObject {
    Long getIdVotingObject();
    String getEntitled();
    String getDetails();
    String getDate();
    Boolean getState();
}
