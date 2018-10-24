package clabersoftware.politicapp.VotingObject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import clabersoftware.politicapp.ActionBar;
import clabersoftware.politicapp.R;

public class AddVotingObjectActivity extends ActionBar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_voting_object);
    }
}
