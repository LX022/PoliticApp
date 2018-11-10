package clabersoftware.politicapp.DataBase;

import java.util.ArrayList;
import java.util.List;

import clabersoftware.politicapp.DataBase.PartyEntity;

public class DataGenerator {
    public static List<PartyEntity> generateParties() {
        List<PartyEntity> parties = new ArrayList<>();

        PartyEntity party1 = new PartyEntity();
        party1.setColor("bleu");
        party1.setLongName("Parti Socialiste");
        party1.setShortName("PS");

        PartyEntity party2 = new PartyEntity();
        party1.setColor("vert");
        party1.setLongName("PLRLKDFJLFDöSL");
        party1.setShortName("PLR");

        PartyEntity party3 = new PartyEntity();
        party1.setColor("jaune");
        party1.setLongName("PDCKDJLöJSö");
        party1.setShortName("PDC");

        parties.add(party1);
        parties.add(party2);
        parties.add(party3);

        return parties;
    }
}
