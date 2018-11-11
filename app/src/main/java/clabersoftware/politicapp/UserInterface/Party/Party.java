package clabersoftware.politicapp.UserInterface.Party;

public class Party {
    private int color;
    private String shortName;
    private String longName;


    //constructor
    public Party(int color, String shortName, String longName){
        this.color = color;
        this.shortName = shortName;
        this.longName = longName;
    }

    //getter
    public int getColor() {
        return color;
    }

    public String getLongName() {
        return longName;
    }

    public String getShortName() {
        return shortName;
    }

    //setter
    public void setColor(int color) {
        this.color = color;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
