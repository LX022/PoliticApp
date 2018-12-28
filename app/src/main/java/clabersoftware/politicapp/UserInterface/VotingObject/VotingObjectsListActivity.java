package clabersoftware.politicapp.UserInterface.VotingObject;

import android.content.Intent;
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
import clabersoftware.politicapp.DataBase.DatasGenerator;
import clabersoftware.politicapp.DataBase.Entity.VotingObjectFB;
import clabersoftware.politicapp.R;
import clabersoftware.politicapp.UserInterface.BaseActivity;

public class VotingObjectsListActivity extends BaseActivity {

    private ArrayList<VotingObjectFB> data ;
    ListView theListView;
    Intent myIntent;

    //idem que les toVoteList mais pour Editer les objects de vote
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting_objects_list);

        theListView = (ListView) findViewById(R.id.PoliticianListView);
        myIntent = new Intent(this, EditVotingObjectActivity.class);


        //Permet de générer les data si aucune
        Boolean GenerateAll = true;
        if (GenerateAll){
            DatasGenerator d = new DatasGenerator();
            d.GenerateData();
        }

        data = new ArrayList<VotingObjectFB>();


        FirebaseDatabase.getInstance()
                .getReference("votingObjects")
                .addValueEventListener(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange( DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    data.clear();
                                    data = toVotingObject(dataSnapshot);
                                 }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }

                        }


                );


        ArrayAdapter<VotingObjectFB> VotingObjectsAdapter = new ArrayAdapter<VotingObjectFB>(this, android.R.layout.simple_list_item_1, data);
        theListView.setAdapter(VotingObjectsAdapter);
        theListView.setOnItemClickListener( listClick );


    }

    private ArrayList<VotingObjectFB> toVotingObject(DataSnapshot snapshot){
        ArrayList<VotingObjectFB> votingObjects = new ArrayList<>();
        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
            VotingObjectFB entity = childSnapshot.getValue(VotingObjectFB.class);
            entity.setVotingObjectUid(childSnapshot.getKey());
            votingObjects.add(entity);
        }

        return votingObjects;

    }

    private AdapterView.OnItemClickListener listClick = new AdapterView.OnItemClickListener () {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            VotingObjectFB itemValue = (VotingObjectFB) theListView.getItemAtPosition( position );
            itemValue.getVotingObjectUid();
            myIntent.putExtra("VOTING_OBJECT_SELECTED", itemValue.getVotingObjectUid());
            startActivity(myIntent);
        }
    };


    public void addVotingObject (View view) {
        Intent intent = new Intent(this, AddVotingObjectActivity.class);
        startActivity(intent);
    }
}
