package clabersoftware.politicapp.Model;

/*
Modélisation de Politician
 */

public interface ModelPolitician {
    Long getIdPolitician();
    String getFirstName();
    String getLastName();
    Long getFkParty();
}
