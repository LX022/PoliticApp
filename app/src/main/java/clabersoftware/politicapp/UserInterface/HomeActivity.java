package clabersoftware.politicapp.UserInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import clabersoftware.politicapp.R;
import clabersoftware.politicapp.UserInterface.Party.PartiesListActivity;
import clabersoftware.politicapp.UserInterface.Politician.PoliticianListActivity;
import clabersoftware.politicapp.UserInterface.Politician.PoliticiansActivity;
import clabersoftware.politicapp.UserInterface.VotingObject.VoteListActivity;
import clabersoftware.politicapp.UserInterface.VotingObject.VotingObjectsListActivity;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void showPartiesList(View view) {
        Intent intent = new Intent(this, PartiesListActivity.class);
        startActivity(intent);
    }
    public void showPoliticiansList(View view) {
        Intent intent = new Intent(this, PoliticianListActivity.class);
        startActivity(intent);
    }

    public void showVotingObjectList(View view) {
        Intent intent = new Intent(this, VotingObjectsListActivity.class);
        startActivity(intent);
    }

    public void vote(View view) {
        Intent intent = new Intent(this, VoteListActivity.class);
        startActivity(intent);
    }

}
