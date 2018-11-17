package clabersoftware.politicapp.UserInterface.VotingObject;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import clabersoftware.politicapp.DataBase.AppDatabase;
import clabersoftware.politicapp.DataBase.Entity.VotingLineEntity;
import clabersoftware.politicapp.DataBase.async.VotingLineAsync;
import clabersoftware.politicapp.R;

public class EditVotingObjectActivity extends AppCompatActivity {

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_voting_object);
        db = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
        Intent Intent = getIntent();
        Long idVotingObject = Intent.getLongExtra("VOTING_OBJECT_SELECTED",1);
        List<VotingLineEntity> votingLines = getVotingLineById(idVotingObject);
        int QtyYes=0;
        int QtyNo=0;
        int QtyBlank=0;

        for (VotingLineEntity vle: votingLines){
            switch (vle.getVote()){
                case "Yes":
                    QtyYes++;
                    break;
                case "No":
                    QtyNo++;
                    break;
                case "Blank":
                    QtyBlank++;
                    break;
            }
        }

        System.out.println("nombre de Yes" + QtyYes);
        System.out.println("nombre de No" + QtyNo);
        System.out.println("nombre de Blank" + QtyBlank);



    }

    private List<VotingLineEntity> getVotingLineById(Long id){
        List<VotingLineEntity> votingLines = new ArrayList<>();
        try {
            votingLines = (ArrayList)  new VotingLineAsync(db, "getVotingLineByIdVotingObject", id).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return votingLines;
    }
}
