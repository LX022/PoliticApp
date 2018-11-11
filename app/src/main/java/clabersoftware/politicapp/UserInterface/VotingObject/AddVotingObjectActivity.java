package clabersoftware.politicapp.UserInterface.VotingObject;

import android.os.Bundle;

import clabersoftware.politicapp.UserInterface.ActionBar;
import clabersoftware.politicapp.R;

public class AddVotingObjectActivity extends ActionBar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_voting_object);
    }
}
