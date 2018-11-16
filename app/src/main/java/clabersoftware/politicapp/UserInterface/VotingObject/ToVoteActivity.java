package clabersoftware.politicapp.UserInterface.VotingObject;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import clabersoftware.politicapp.DataBase.AppDatabase;
import clabersoftware.politicapp.DataBase.Entity.VotingObjectEntity;
import clabersoftware.politicapp.DataBase.async.VotingObjectAsync;
import clabersoftware.politicapp.R;
import clabersoftware.politicapp.UserInterface.BaseActivity;

public class ToVoteActivity extends BaseActivity {

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_vote);

        Intent Intent = getIntent();
        Long idVotingObject = Intent.getLongExtra("VOTINGOBJECT_SELECTED",1);

        VotingObjectEntity toVote = getById(idVotingObject);

        TextView votingObjectNameField = (TextView) findViewById(R.id.votingObjectNameField);
        votingObjectNameField.setText(toVote.getEntitled());
        votingObjectNameField.setEnabled(false);

        TextView votingObjectdetailsField = (TextView) findViewById(R.id.votingObjectdetailsField);
        votingObjectdetailsField.setText(toVote.getDetails());
        votingObjectdetailsField.setEnabled(false);

        TextView votingObjectDateField = (TextView) findViewById(R.id.votingObjectDateField);
        votingObjectDateField.setText(toVote.getDate());
        votingObjectDateField.setEnabled(false);
    }

    private VotingObjectEntity getById(Long id){
        VotingObjectEntity PartyToEdit = new VotingObjectEntity();
        try {
            PartyToEdit = (VotingObjectEntity) new VotingObjectAsync(db, "getById", id).execute().get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return PartyToEdit;
    }
}
