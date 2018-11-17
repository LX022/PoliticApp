package clabersoftware.politicapp.UserInterface.VotingObject;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import clabersoftware.politicapp.DataBase.AppDatabase;
import clabersoftware.politicapp.DataBase.Entity.VotingObjectEntity;
import clabersoftware.politicapp.DataBase.async.VotingObjectAsync;
import clabersoftware.politicapp.UserInterface.BaseActivity;
import clabersoftware.politicapp.R;

public class AddVotingObjectActivity extends BaseActivity {

    private AppDatabase db;
    private Toast mToast;

    private EditText entitled;
    private EditText details;
    private EditText date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_voting_object);
        db = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
        mToast = Toast.makeText(this, getString(R.string.partyVotingObjectCreated), Toast.LENGTH_LONG);
        initializeForm();
    }

    /*Récupération des données du formulaire en vu d'un update*/
    private void initializeForm() {
        entitled = findViewById(R.id.votingObjectNameFieldEdit);
        details = findViewById(R.id.votingObjectdetailsFieldEdit);
        date = findViewById(R.id.votingObjectDateFieldEdit);
        Button saveBtn = findViewById(R.id.votingObjectAddButton);
        saveBtn.setOnClickListener(View -> saveChanges(
                entitled.getText().toString(),
                details.getText().toString(),
                date.getText().toString()
        ));
    }
    //Update du voting object
    private void saveChanges(String entitled, String details, String date){
        VotingObjectEntity newVotingObject = new VotingObjectEntity(entitled, details, date);
        new VotingObjectAsync(db,"add", newVotingObject).execute();
        mToast.show();
        Intent intent = new Intent(this, VotingObjectsListActivity.class);
        startActivity(intent);
    }
}
