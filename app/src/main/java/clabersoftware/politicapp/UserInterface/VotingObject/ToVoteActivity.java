package clabersoftware.politicapp.UserInterface.VotingObject;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import clabersoftware.politicapp.DataBase.AppDatabase;
import clabersoftware.politicapp.DataBase.Entity.VotingLineEntity;
import clabersoftware.politicapp.DataBase.Entity.VotingObjectEntity;
import clabersoftware.politicapp.DataBase.GlobalData;
import clabersoftware.politicapp.DataBase.async.VotingLineAsync;
import clabersoftware.politicapp.DataBase.async.VotingObjectAsync;
import clabersoftware.politicapp.R;
import clabersoftware.politicapp.UserInterface.BaseActivity;
import clabersoftware.politicapp.UserInterface.HomeActivity;

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
        idVotingObject = Intent.getLongExtra("VOTING_OBJECT_SELECTED",1);
        System.out.println("IdVotingObject : " + idVotingObject);

        VotingObjectEntity toVote = getById(idVotingObject);

        TextView votingObjectNameField = (TextView) findViewById(R.id.votingObjectNameFieldEdit);
        votingObjectNameField.setText(toVote.getEntitled());


        TextView votingObjectdetailsField = (TextView) findViewById(R.id.votingObjectdetailsFieldEdit);
        votingObjectdetailsField.setText(toVote.getDetails());


        TextView votingObjectDateField = (TextView) findViewById(R.id.votingObjectDateFieldEdit);
        votingObjectDateField.setText(toVote.getDate());


        voted =findViewById(R.id.alreadyVoted);
        voted.setEnabled(false);

        yes = findViewById(R.id.voteYesButton);
        no = findViewById(R.id.voteNoButton);
        blank = findViewById(R.id.voteBlankButton);

        /*Control qu'un utilisateur n'a pas déjà voté pour l'objet en question si oui on lui
        * masque les bouttons et on lui affiche un message*/
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

    //Boutton oui
    public void voteYes(View view){
        vote("Yes");
    }

    //Boutton non
    public void voteNo(View view){
        vote("No");
    }

    //Boutton blanc
    public void voteBlank(View view){
        vote("Blank");
    }

    //Fonction de vote
    private void vote(String vote){
        VotingLineEntity newVotingLine = new VotingLineEntity(vote,connected , idVotingObject);
        new VotingLineAsync(db,"add",newVotingLine).execute();
        Intent intent = new Intent(this, VoteListActivity.class);
        startActivity(intent);

    }

    //Control qu'un user n'a pas déjà voté
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
            if(vle.getFkPolitician()==connected && vle.getFkVotingObject()==idVotingObject){

                return false;
            }
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        this.startActivity(new Intent(this,HomeActivity.class));
    }
}
