package clabersoftware.politicapp.UserInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import clabersoftware.politicapp.R;
import clabersoftware.politicapp.UserInterface.ActionBar;
import clabersoftware.politicapp.UserInterface.Party.PartiesActivity;
import clabersoftware.politicapp.UserInterface.Politician.PoliticiansActivity;
import clabersoftware.politicapp.UserInterface.VotingObject.VotingObjectsActivity;

public class HomeActivity extends ActionBar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void showPartiesList(View view) {
        Intent intent = new Intent(this, PartiesActivity.class);
        startActivity(intent);
    }
    public void showPoliticiansList(View view) {
        Intent intent = new Intent(this, PoliticiansActivity.class);
        startActivity(intent);
    }

    public void showVotingObjectList(View view) {
        Intent intent = new Intent(this, VotingObjectsActivity.class);
        startActivity(intent);
    }

    public void showOpenVotingObjectList(View view) {
        Intent intent = new Intent(this, VotingObjectsActivity.class);
        startActivity(intent);
    }
}
