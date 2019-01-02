package clabersoftware.politicapp.DataBase;

import android.util.Log;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import clabersoftware.politicapp.DataBase.Entity.PartyFB;
import clabersoftware.politicapp.DataBase.Entity.PoliticianFB;

import static android.support.constraint.Constraints.TAG;

public class DatasGenerator {

    private DatabaseReference ref;
    private FirebaseDatabase database;


    public DatasGenerator(){
        ref = database.getInstance().getReference();
    }

    public void GenerateData() {
        //Clean all data
        System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°Création de données");
        FirebaseDatabase.getInstance()
                .getReference("parties")
                .removeValue(new DatabaseReference.CompletionListener() {

                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError != null) {
                            Log.d(TAG, "Delete failure!", databaseError.toException());
                        } else {
                            Log.d(TAG, "Delete successful!");
                        }
                    }
                });

        FirebaseDatabase.getInstance()
                .getReference("politicians")
                .removeValue(new DatabaseReference.CompletionListener() {

                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError != null) {
                            Log.d(TAG, "Delete failure!", databaseError.toException());
                        } else {
                            Log.d(TAG, "Delete successful!");
                        }
                    }
                });



        List<PartyFB> parties = new ArrayList<>();

        PartyFB p1 = new PartyFB();
        PartyFB p2 = new PartyFB();
        PartyFB p3 = new PartyFB();

        String p1UUID = UUID.randomUUID().toString();
        String p2UUID = UUID.randomUUID().toString();
        String p3UUID = UUID.randomUUID().toString();

        p1.setPartyUid(p1UUID);
        p1.setShortName("PS");
        p1.setLongName("Parti Socialiste");

        p2.setPartyUid(p2UUID);
        p2.setShortName("PLR");
        p2.setLongName("Parti Libéral Radical");

        p3.setPartyUid(p3UUID);
        p3.setShortName("PDC");
        p3.setLongName("Parti Démocrate Chrétien");

        parties.add(p1);
        parties.add(p2);
        parties.add(p3);

        for (PartyFB party : parties)
            ref.child("parties").child(party.getPartyUid()).setValue(party);


        List<PoliticianFB> politicians = new ArrayList<>();

        PoliticianFB po1 = new PoliticianFB();
        po1.setPoliticianUid(UUID.randomUUID().toString());
        po1.setFirstName("admin");
        po1.setLastName("admin");
        po1.setLogin("admin");
        po1.setPassword("admin");
        po1.setFkParty(p1UUID);
        politicians.add(po1);

        PoliticianFB po2 = new PoliticianFB();
        po2.setPoliticianUid(UUID.randomUUID().toString());
        po2.setFirstName("yann");
        po2.setLastName("yann");
        po2.setLogin("yann");
        po2.setPassword("yann");
        po2.setFkParty(p2UUID);
        politicians.add(po2);

        for (PoliticianFB po : politicians)
            ref.child("politicians").child(po.getPoliticianUid()).setValue(po);
    }
}
