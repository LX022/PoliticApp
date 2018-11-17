package clabersoftware.politicapp.UserInterface.VotingObject;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import clabersoftware.politicapp.DataBase.AppDatabase;
import clabersoftware.politicapp.DataBase.Entity.VotingObjectEntity;
import clabersoftware.politicapp.DataBase.async.VotingObjectAsync;
import clabersoftware.politicapp.R;
import clabersoftware.politicapp.UserInterface.BaseActivity;

public class EditVotingObjectActivity extends BaseActivity {

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        db = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_voting_object);

        Intent Intent = getIntent();
        Long idVotingObjectToEdit = Intent.getLongExtra("VOTINGOBJECT_SELECTED",1);

        VotingObjectEntity votingObjectToEdit = getById(idVotingObjectToEdit);

        TextView name = (TextView) findViewById(R.id.votingObjectNameFieldEdit);
        name.setText(votingObjectToEdit.getEntitled());

        TextView details = (TextView) findViewById(R.id.votingObjectdetailsFieldEdit);
        details.setText(votingObjectToEdit.getDetails());

        TextView date = (TextView) findViewById(R.id.votingObjectDateFieldEdit);
        date.setText(votingObjectToEdit.getDate());
    }

    public void saveVotingObject(View view) throws ExecutionException, InterruptedException{
        VotingObjectEntity votingObjectUpdated = new VotingObjectEntity();

        TextView name = (TextView) findViewById(R.id.votingObjectNameFieldEdit);
        votingObjectUpdated.setEntitled(name.getText().toString());

        TextView details = (TextView) findViewById(R.id.votingObjectdetailsFieldEdit);
        votingObjectUpdated.setDetails(details.getText().toString());

        TextView date = (TextView) findViewById(R.id.votingObjectDateFieldEdit);
        votingObjectUpdated.setDate(date.getText().toString());

        Intent Intent = getIntent();
        Long idVotingObject = Intent.getLongExtra("VOTINGOBJECT_SELECTED",1);
        votingObjectUpdated.setIdVotingObject(idVotingObject);

        new VotingObjectAsync(db,"update",votingObjectUpdated).execute().get();

        System.out.println(votingObjectUpdated.getEntitled());
        System.out.println(votingObjectUpdated.getDetails());
        System.out.println(votingObjectUpdated.getDate());

        Intent intent = new Intent(this, VotingObjectsListActivity.class);
        startActivity(intent);
    }

    private VotingObjectEntity getById(Long id){
        VotingObjectEntity votingObjectToEdit = new VotingObjectEntity();
        try {
            votingObjectToEdit = (VotingObjectEntity) new VotingObjectAsync(db, "getById", id).execute().get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return votingObjectToEdit;
    }
}
