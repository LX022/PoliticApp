package clabersoftware.politicapp.UserInterface.Party;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import clabersoftware.politicapp.DataBase.AppDatabase;
import clabersoftware.politicapp.DataBase.Entity.PartyEntity;
import clabersoftware.politicapp.DataBase.Entity.PartyFB;
import clabersoftware.politicapp.DataBase.Entity.PoliticianEntity;
import clabersoftware.politicapp.DataBase.async.PartyAsync;
import clabersoftware.politicapp.DataBase.async.PoliticianAsync;
import clabersoftware.politicapp.R;
import clabersoftware.politicapp.UserInterface.BaseActivity;
import clabersoftware.politicapp.UserInterface.HomeActivity;
import clabersoftware.politicapp.UserInterface.Politician.PoliticianListActivity;


public class EditPartyActivity extends BaseActivity {

    private AppDatabase db;
    private Toast mToastPartyDelete;
    private Toast mToastPartyNotDeletable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_party);
        //Réception de l'id de l'élément cliqué
        String idPartyToEdit = getIntent().getExtras().getString("PARTY_SELECTED");
        String shortName = getIntent().getExtras().getString("PartyShortName");
        String longName = getIntent().getExtras().getString("PartyLongName");


        /*
        * To Delete when string changes is done
        * */
        mToastPartyDelete  = Toast.makeText(this, getString(R.string.partyDeleted), Toast.LENGTH_LONG);
        mToastPartyNotDeletable = Toast.makeText(this, getString(R.string.partyNoDelete), Toast.LENGTH_LONG);

        TextView partyNameField = (TextView) findViewById(R.id.partyNameField);
        partyNameField.setText(longName);

        TextView partyAbbreviationField = (TextView) findViewById(R.id.partyAbbreviationField);
        partyAbbreviationField.setText(shortName);


    }
    //Enregistrement des modifications
    public void save(View view) throws ExecutionException, InterruptedException{
        PartyFB partyUpdated = new PartyFB();

        TextView partyNameField = (TextView) findViewById(R.id.partyNameField);
        partyUpdated.setLongName(partyNameField.getText().toString());

        TextView partyAbbreviationField = (TextView) findViewById(R.id.partyAbbreviationField);
        partyUpdated.setShortName(partyAbbreviationField.getText().toString());

        String idPartyToEdit = getIntent().getExtras().getString("PARTY_SELECTED");
        partyUpdated.setPartyUid(idPartyToEdit);

        FirebaseDatabase.getInstance()
                .getReference("parties")
                .child(partyUpdated.getPartyUid())
                .updateChildren(partyUpdated.toMap(), new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError != null) {

                        } else {

                        }

                    }
                });

        Intent intent = new Intent(this, PartiesListActivity.class);
        startActivity(intent);
    }

    private void saveChanges(Long PartyId) {

    }

    //supression du party
    public void delete(View view) throws ExecutionException, InterruptedException{
        Intent Intent = getIntent();
        String idPartyToDelete = getIntent().getExtras().getString("PARTY_SELECTED");
        //contrôle de l'affectation du parti
        FirebaseDatabase.getInstance()
                .getReference("parties")
                .child(idPartyToDelete)
                .removeValue(new DatabaseReference.CompletionListener() {

                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError != null) {

                        } else {

                        }
                    }
                });

        Intent intent = new Intent(this, PartiesListActivity.class);
        startActivity(intent);
    }
    //si le parti est affecté à un politicien nous ne pouvons pas le supprimer
    private boolean DeleteAuthorization(Long idParty){
        ArrayList<PoliticianEntity> toControl = new ArrayList<>();
        try {
            toControl = (ArrayList) new PoliticianAsync(db, "getAll", 0).execute().get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for (PoliticianEntity p :toControl){
            if(p.getFkParty()==idParty)
                return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        this.startActivity(new Intent(this,HomeActivity.class));
    }
}
