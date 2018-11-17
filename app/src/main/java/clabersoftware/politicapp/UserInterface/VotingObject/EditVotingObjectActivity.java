package clabersoftware.politicapp.UserInterface.VotingObject;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import java.util.List;

import java.util.concurrent.ExecutionException;

import clabersoftware.politicapp.DataBase.AppDatabase;
import clabersoftware.politicapp.DataBase.Entity.VotingLineEntity;

import clabersoftware.politicapp.DataBase.async.VotingLineAsync;

import clabersoftware.politicapp.DataBase.Entity.VotingObjectEntity;
import clabersoftware.politicapp.DataBase.async.VotingLineAsync;
import clabersoftware.politicapp.DataBase.async.VotingObjectAsync;

import clabersoftware.politicapp.R;
import clabersoftware.politicapp.UserInterface.BaseActivity;

public class EditVotingObjectActivity extends BaseActivity {

    private AppDatabase db;
    private Toast mToastVotingObjectNotDeletable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_voting_object);

        Intent Intent = getIntent();
        Long idVotingObject = Intent.getLongExtra("VOTING_OBJECT_SELECTED", 1);

    }

    private List<VotingLineEntity> getVotingLineById(Long id) {
        List<VotingLineEntity> votingLines = new ArrayList<>();

        try {
            votingLines = (ArrayList) new VotingLineAsync(db, "getVotingLineByIdVotingObject", id).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Intent Intent = getIntent();
        Long idVotingObjectToEdit = Intent.getLongExtra("VOTINGOBJECT_SELECTED", 1);

        VotingObjectEntity votingObjectToEdit = getById(idVotingObjectToEdit);

        TextView name = (TextView) findViewById(R.id.votingObjectNameFieldEdit);
        name.setText(votingObjectToEdit.getEntitled());

        TextView details = (TextView) findViewById(R.id.votingObjectdetailsFieldEdit);
        details.setText(votingObjectToEdit.getDetails());

        TextView date = (TextView) findViewById(R.id.votingObjectDateFieldEdit);
        date.setText(votingObjectToEdit.getDate());

        mToastVotingObjectNotDeletable = Toast.makeText(this, getString(R.string.votingObjectNoDelete), Toast.LENGTH_LONG);

        return votingLines;

    }

    /*
    * Control si on peut deleter un voting object.
    * */
    private boolean DeleteAuthorization (Long idVotingObject){
        ArrayList<VotingLineEntity> toControl = new ArrayList<>();
        try {
            toControl = (ArrayList) new VotingLineAsync(db, "getAll", 0).execute().get();


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for (VotingLineEntity vl : toControl) {
            if (vl.getFkVotingObject() == idVotingObject)
                return false;
        }
        return true;

    }

    /*méthode de suppression d'un voting object*/
    public void deleteVotingObject (View view) throws ExecutionException, InterruptedException {
        Intent Intent = getIntent();
        Long idVotingObjectToDelete = Intent.getLongExtra("VOTINGOBJECT_SELECTED", 1);

        if (DeleteAuthorization(idVotingObjectToDelete)) {
            VotingObjectEntity votingObjectToDelete = new VotingObjectEntity();

            TextView name = (TextView) findViewById(R.id.votingObjectNameFieldEdit);
            votingObjectToDelete.setEntitled(name.getText().toString());

            TextView details = (TextView) findViewById(R.id.votingObjectdetailsFieldEdit);
            votingObjectToDelete.setDetails(details.getText().toString());

            TextView date = (TextView) findViewById(R.id.votingObjectDateFieldEdit);
            votingObjectToDelete.setDate(date.getText().toString());

            Long idVotingObject = Intent.getLongExtra("VOTINGOBJECT_SELECTED", 1);
            votingObjectToDelete.setIdVotingObject(idVotingObject);

            new VotingObjectAsync(db, "delete", votingObjectToDelete).execute().get();

            Intent intent = new Intent(this, VotingObjectsListActivity.class);
            startActivity(intent);
        } else {
            mToastVotingObjectNotDeletable.show();
        }
    }

    /*
    * Obtient une voting object en fonction d'un id
    * */
    private VotingObjectEntity getById (Long id){
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

    public void saveVotingObject (View view) throws ExecutionException, InterruptedException {
        VotingObjectEntity votingObjectUpdated = new VotingObjectEntity();

        TextView name = (TextView) findViewById(R.id.votingObjectNameFieldEdit);
        votingObjectUpdated.setEntitled(name.getText().toString());

        TextView details = (TextView) findViewById(R.id.votingObjectdetailsFieldEdit);
        votingObjectUpdated.setDetails(details.getText().toString());

        TextView date = (TextView) findViewById(R.id.votingObjectDateFieldEdit);
        votingObjectUpdated.setDate(date.getText().toString());

        Intent Intent = getIntent();
        Long idVotingObject = Intent.getLongExtra("VOTINGOBJECT_SELECTED", 1);
        votingObjectUpdated.setIdVotingObject(idVotingObject);

        new VotingObjectAsync(db, "update", votingObjectUpdated).execute().get();

        System.out.println(votingObjectUpdated.getEntitled());
        System.out.println(votingObjectUpdated.getDetails());
        System.out.println(votingObjectUpdated.getDate());

        Intent intent = new Intent(this, VotingObjectsListActivity.class);
        startActivity(intent);
    }
}

