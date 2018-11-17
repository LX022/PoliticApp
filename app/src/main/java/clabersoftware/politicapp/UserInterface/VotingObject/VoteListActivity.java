package clabersoftware.politicapp.UserInterface.VotingObject;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import clabersoftware.politicapp.DataBase.AppDatabase;
import clabersoftware.politicapp.DataBase.Entity.VotingObjectEntity;
import clabersoftware.politicapp.DataBase.async.VotingObjectAsync;
import clabersoftware.politicapp.R;
import clabersoftware.politicapp.UserInterface.BaseActivity;

public class VoteListActivity extends BaseActivity {

    ListView theListView;
    Intent myIntent;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_list);

        myIntent = new Intent(this, ToVoteActivity.class);

        theListView = (ListView) findViewById(R.id.VotingObjectsListView);

        List<VotingObjectEntity> datas = genererVotingObjects();

        ArrayAdapter<VotingObjectEntity> VotingObjectsAdapter = new ArrayAdapter<VotingObjectEntity>(this, android.R.layout.simple_list_item_1, datas);

        theListView.setAdapter(VotingObjectsAdapter);

        theListView.setOnItemClickListener( listClick );
    }

    private AdapterView.OnItemClickListener listClick = new AdapterView.OnItemClickListener () {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            VotingObjectEntity itemValue = (VotingObjectEntity) theListView.getItemAtPosition( position );
            itemValue.getIdVotingObject();
            myIntent.putExtra("VOTING_OBJECT_SELECTED", itemValue.getIdVotingObject());
            System.out.println("Item sélectionner:      " + itemValue.getIdVotingObject());
            startActivity(myIntent);
        }
    };

    private List<VotingObjectEntity> genererVotingObjects(){
        List<VotingObjectEntity> votingObjects = new ArrayList<>();
        try {
            votingObjects = (ArrayList) new VotingObjectAsync(db,   "getAll", 0).execute().get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return votingObjects;
    }
}
