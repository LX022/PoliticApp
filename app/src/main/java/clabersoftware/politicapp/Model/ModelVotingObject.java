package clabersoftware.politicapp.Model;

import java.util.Date;

public interface ModelVotingObject {
    Long getIdVotingObject();
    String getEntitled();
    String GetDetails();
    Date getDate();
    Boolean getState();
}
