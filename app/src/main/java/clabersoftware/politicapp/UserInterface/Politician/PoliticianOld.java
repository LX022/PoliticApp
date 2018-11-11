package clabersoftware.politicapp.UserInterface.Politician;

import clabersoftware.politicapp.UserInterface.Party.Party;

public class PoliticianOld {
    private String name;
    private String description;
    private Party party;

    public PoliticianOld(String name, String description, Party party){
        this.name = name;
        this.description = description;
        this.party = party;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }
}
