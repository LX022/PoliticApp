package clabersoftware.politicapp.UserInterface.Politician;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

import clabersoftware.politicapp.DataBase.Entity.PartyFB;
import clabersoftware.politicapp.DataBase.Entity.PoliticianFB;
import clabersoftware.politicapp.R;
import clabersoftware.politicapp.UserInterface.BaseActivity;
import clabersoftware.politicapp.UserInterface.HomeActivity;
import clabersoftware.politicapp.UserInterface.Party.EditPartyActivity;

public class PoliticianListActivity extends BaseActivity {

    ListView theListView;
    Intent myIntent;
    private ArrayList<PoliticianFB> data ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politician_list);

        theListView = (ListView) findViewById(R.id.PoliticianListView);
        myIntent = new Intent(this, EditPoliticianActivity.class);

        data = new ArrayList<PoliticianFB>();

        FirebaseDatabase.getInstance()
                .getReference("politicians")
                .addValueEventListener(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange( DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    data.clear();
                                    data = toPoliticians(dataSnapshot);
                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }

                        }


                );

    }

    private ArrayList<PoliticianFB> toPoliticians(DataSnapshot snapshot){
        ArrayList<PoliticianFB> politicians = new ArrayList<>();
        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
            PoliticianFB entity = childSnapshot.getValue(PoliticianFB.class);
            entity.setPoliticianUid(childSnapshot.getKey());
            politicians.add(entity);
        }

        return politicians;

    }

    //envoi du politicien cliqué
    private AdapterView.OnItemClickListener listClick = new AdapterView.OnItemClickListener () {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            PoliticianFB itemValue = (PoliticianFB) theListView.getItemAtPosition( position );
            itemValue.getPoliticianUid();
            myIntent.putExtra("PoUuid", itemValue.getPoliticianUid());
            myIntent.putExtra("PoFirstName", itemValue.getFirstName());
            myIntent.putExtra("PoLastName", itemValue.getLastName());
            myIntent.putExtra("PoFkParty", itemValue.getFkParty());
            myIntent.putExtra("PoLogin", itemValue.getLogin());
            myIntent.putExtra("PoPwd", itemValue.getPassword());

            FirebaseDatabase.getInstance()
                    .getReference("politicians")
                    .child(itemValue.getPoliticianUid())
                    .updateChildren(itemValue.toMap(), new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if (databaseError != null) {

                            } else {

                            }

                        }
                    });

            startActivity(myIntent);
        }
    };

    //envoie de la vue de l'ajout de party
    public void showDataPolitician(View view) {
        ArrayAdapter<PoliticianFB> PoliticianAdapter = new ArrayAdapter<PoliticianFB>(this, android.R.layout.simple_list_item_1, data);
        theListView.setAdapter(PoliticianAdapter);
        theListView.setOnItemClickListener( listClick );
    }

    @Override
    public void onBackPressed() {
        this.startActivity(new Intent(this,HomeActivity.class));
    }
}
