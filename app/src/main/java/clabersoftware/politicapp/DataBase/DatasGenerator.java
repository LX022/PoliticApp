package clabersoftware.politicapp.DataBase;

import android.util.Log;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import clabersoftware.politicapp.DataBase.Entity.PartyFB;

import static android.support.constraint.Constraints.TAG;

public class DatasGenerator {

    private DatabaseReference ref;
    private FirebaseDatabase database;


    public DatasGenerator(){
        ref = database.getInstance().getReference();
    }

    public void GenerateData() {
        //Clean all data
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


    }
}
