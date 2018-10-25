package clabersoftware.politicapp.VotingObject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import clabersoftware.politicapp.ActionBar;
import clabersoftware.politicapp.R;

public class VotingObjectDetailsActivity extends ActionBar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting_object_details);
    }
}
