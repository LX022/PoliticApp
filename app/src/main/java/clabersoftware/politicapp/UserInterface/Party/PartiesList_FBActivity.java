package clabersoftware.politicapp.UserInterface.Party;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import clabersoftware.politicapp.DataBase.DatasGenerator;
import clabersoftware.politicapp.DataBase.Entity.PartyFB;
import clabersoftware.politicapp.R;
import clabersoftware.politicapp.UserInterface.HomeActivity;

public class PartiesList_FBActivity extends AppCompatActivity {

    private ArrayList<PartyFB> data ;
    ListView theListView;
    Intent myIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parties_list__fb);
        theListView = (ListView) findViewById(R.id.PoliticianListView);
        myIntent = new Intent(this, EditPartyActivity.class);


        DatasGenerator d = new DatasGenerator();
        d.GenerateData();

        data = new ArrayList<PartyFB>();


        FirebaseDatabase.getInstance()
                .getReference("parties")
                .addValueEventListener(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange( DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    data.clear();
                                    data = toParties(dataSnapshot);
                                    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

                                    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }

                        }


                );


        ArrayAdapter<PartyFB> PartiesAdapter = new ArrayAdapter<PartyFB>(this, android.R.layout.simple_list_item_1, data);
        theListView.setAdapter(PartiesAdapter);
        theListView.setOnItemClickListener( listClick );



    }

    private ArrayList<PartyFB> toParties(DataSnapshot snapshot){
        ArrayList<PartyFB> parties = new ArrayList<>();
        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
            PartyFB entity = childSnapshot.getValue(PartyFB.class);
            entity.setPartyUid(childSnapshot.getKey());
            parties.add(entity);
        }

        return parties;

    }

    //Envoi de l'id du parti cliqué
    private AdapterView.OnItemClickListener listClick = new AdapterView.OnItemClickListener () {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            PartyFB itemValue = (PartyFB) theListView.getItemAtPosition( position );
            itemValue.getPartyUid();
            myIntent.putExtra("PARTY_SELECTED", itemValue.getPartyUid());
            System.out.println(itemValue.getPartyUid());
            startActivity(myIntent);
        }
    };

    @Override
    public void onBackPressed() {
        this.startActivity(new Intent(this,HomeActivity.class));
    }
}
