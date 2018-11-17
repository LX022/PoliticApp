package clabersoftware.politicapp.UserInterface.VotingObject;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import clabersoftware.politicapp.DataBase.AppDatabase;
import clabersoftware.politicapp.DataBase.Entity.VotingLineEntity;
import clabersoftware.politicapp.DataBase.Entity.VotingObjectEntity;
import clabersoftware.politicapp.DataBase.GlobalData;
import clabersoftware.politicapp.DataBase.async.VotingLineAsync;
import clabersoftware.politicapp.DataBase.async.VotingObjectAsync;
import clabersoftware.politicapp.R;
import clabersoftware.politicapp.UserInterface.BaseActivity;

import static android.view.View.INVISIBLE;

public class ToVoteActivity extends BaseActivity {

    private AppDatabase db;
    Long idVotingObject;
    Long connected;

    Button yes;
    Button no;
    Button blank;
    TextView voted;
    boolean test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_vote);
        connected =  ((GlobalData) this.getApplication()).getIdConnected();

        Intent Intent = getIntent();
        idVotingObject = Intent.getLongExtra("VOTINGOBJECT_SELECTED",1);

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

        voted =findViewById(R.id.alreadyVoted);
        voted.setEnabled(false);

        yes = findViewById(R.id.voteYesButton);
        no = findViewById(R.id.voteNoButton);
        blank = findViewById(R.id.voteBlankButton);

        System.out.println("auto : " + authorization());
        if(authorization()){
            voted.setVisibility(INVISIBLE);
        }
        else{
            yes.setVisibility(View.INVISIBLE);
            no.setVisibility(View.INVISIBLE);
            blank.setVisibility(View.INVISIBLE);

        }

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

    public void voteYes(View view){
        vote("Yes");
    }

    public void voteNo(View view){
        vote("No");
    }

    public void voteBlank(View view){
        vote("Blank");
    }

    private void vote(String vote){
        VotingLineEntity newVotingLine = new VotingLineEntity(vote,connected , idVotingObject);
        new VotingLineAsync(db,"add",newVotingLine).execute();
        System.out.println("vote : " + newVotingLine.getVote());
        System.out.println("connected : " + newVotingLine.getFkPolitician());
        System.out.println("idVotingObject : " + newVotingLine.getFkVotingObject());
        Intent intent = new Intent(this, VoteListActivity.class);
        startActivity(intent);






    }

    private boolean authorization(){
        ArrayList<VotingLineEntity> toControl = new ArrayList<>();
        try {
            toControl = (ArrayList) new VotingLineAsync(db, "getAll", 0).execute().get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for(VotingLineEntity vle :toControl){
            System.out.println("vote : " + vle.getVote() + "idpoli : " + vle.getFkPolitician() + "idObje : " + vle.getFkVotingObject());
            System.out.println("connected : " + connected + " VS " + vle.getFkPolitician());
            if(vle.getFkPolitician()==connected){
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Je n'autorise pas le vote");
                return false;
            }
        }
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Je autorise la votation");
        return true;
    }
}
