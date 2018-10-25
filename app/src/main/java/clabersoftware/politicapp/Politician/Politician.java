package clabersoftware.politicapp.Politician;

import clabersoftware.politicapp.Party.Party;

public class Politician {
    private String name;
    private String description;
    private Party party;

    public Politician(String name, String description, Party party){
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
