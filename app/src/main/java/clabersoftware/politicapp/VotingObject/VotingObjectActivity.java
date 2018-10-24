package clabersoftware.politicapp.VotingObject;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import clabersoftware.politicapp.ActionBar;
import clabersoftware.politicapp.Party.PartiesActivity;
import clabersoftware.politicapp.Party.Party;
import clabersoftware.politicapp.Party.PartyAdapter;
import clabersoftware.politicapp.R;

public class VotingObjectActivity extends ActionBar {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting_object);

        mListView = (ListView) findViewById(R.id.listView);

        List<VotingObject> votingObjectList = genererVotingObject();

        VotingObjectAdapter adapter = new VotingObjectAdapter(VotingObjectActivity.this, votingObjectList);

        mListView.setAdapter(adapter);

    }

    private List<VotingObject> genererVotingObject(){
        List<VotingObject> votingObjectList = new ArrayList<VotingObject>();
        votingObjectList.add(new VotingObject("Lex Weber", "Votation sur les résidences secondaire", "01.01.2018",true ));
        votingObjectList.add(new VotingObject("Lex Weber", "Votation sur les résidences secondaire", "01.01.2018", true ));
        votingObjectList.add(new VotingObject("Lex Weber", "Votation sur les résidences secondaire", "01.01.2018", false ));
        votingObjectList.add(new VotingObject("Lex Weber", "Votation sur les résidences secondaire", "01.01.2018", false ));
        votingObjectList.add(new VotingObject("Lex Weber", "Votation sur les résidences secondaire", "01.01.2018", false ));
        votingObjectList.add(new VotingObject("Lex Weber", "Votation sur les résidences secondaire", "01.01.2018", true ));

        return votingObjectList;
    }

    public void addVotingObject(View view) {
        Intent intent = new Intent(this, AddVotingObjectActivity.class);
        startActivity(intent);
    }
}
