package clabersoftware.politicapp.UserInterface.VotingObject;

import java.util.Date;

public class VotingObject {
    private String designation;
    private String description;
    private String deadline;
    private boolean state;

    public VotingObject(String designation, String description, String deadline, boolean state){
        this.designation = designation;
        this.description = description;
        this.deadline = deadline;
        this.state = state;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
