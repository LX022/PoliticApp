package clabersoftware.politicapp.UserInterface.Party;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import clabersoftware.politicapp.DataBase.AppDatabase;
import clabersoftware.politicapp.DataBase.Entity.PartyEntity;
import clabersoftware.politicapp.DataBase.Entity.PoliticianEntity;
import clabersoftware.politicapp.DataBase.async.PartyAsync;
import clabersoftware.politicapp.DataBase.async.PoliticianAsync;
import clabersoftware.politicapp.R;
import clabersoftware.politicapp.UserInterface.BaseActivity;


public class EditPartyActivity extends BaseActivity {

    private AppDatabase db;
    private Toast mToastPartyDelete;
    private Toast mToastPartyNotDeletable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_party);
        Intent Intent = getIntent();
        Long idPartyToEdit = Intent.getLongExtra("PARTY_SELECTED",1);
        /*
        * To Delete when string changes is done
        * */
        mToastPartyDelete  = Toast.makeText(this, getString(R.string.partyDeleted), Toast.LENGTH_LONG);
        mToastPartyNotDeletable = Toast.makeText(this, getString(R.string.partyNoDelete), Toast.LENGTH_LONG);

        PartyEntity PartyToEdit = getById(idPartyToEdit);

        TextView partyNameField = (TextView) findViewById(R.id.partyNameField);
        partyNameField.setText(PartyToEdit.getLongName());

        TextView partyAbbreviationField = (TextView) findViewById(R.id.partyAbbreviationField);
        partyAbbreviationField.setText(PartyToEdit.getShortName());


    }

    public void save(View view) throws ExecutionException, InterruptedException{
        PartyEntity partyUpdated = new PartyEntity();

        TextView partyNameField = (TextView) findViewById(R.id.partyNameField);
        partyUpdated.setLongName(partyNameField.getText().toString());

        TextView partyAbbreviationField = (TextView) findViewById(R.id.partyAbbreviationField);
        partyUpdated.setShortName(partyAbbreviationField.getText().toString());

        Intent Intent = getIntent();
        Long idPartyToEdit = Intent.getLongExtra("PARTY_SELECTED",1);
        partyUpdated.setIdParty(idPartyToEdit);

        System.out.println(partyUpdated.getShortName() + "    "+partyUpdated.getLongName());
        new PartyAsync(db,"update",partyUpdated).execute().get();

        Intent intent = new Intent(this, PartiesListActivity.class);
        startActivity(intent);
    }

    private void saveChanges(Long PartyId) {

    }

    private PartyEntity getById(Long id){
        PartyEntity PartyToEdit = new PartyEntity();
        try {
            PartyToEdit = (PartyEntity) new PartyAsync(db, "getById", id).execute().get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return PartyToEdit;
    }

    public void delete(View view) throws ExecutionException, InterruptedException{
        Intent Intent = getIntent();
        Long idPartyToEdit = Intent.getLongExtra("PARTY_SELECTED",1);
        if (DeleteAuthorization(idPartyToEdit)) {
            PartyEntity partyUpdated = new PartyEntity();

            TextView partyNameField = (TextView) findViewById(R.id.partyNameField);
            partyUpdated.setLongName(partyNameField.getText().toString());

            TextView partyAbbreviationField = (TextView) findViewById(R.id.partyAbbreviationField);
            partyUpdated.setShortName(partyAbbreviationField.getText().toString());

            partyUpdated.setIdParty(idPartyToEdit);

            new PartyAsync(db, "delete", partyUpdated).execute().get();

            Intent intent = new Intent(this, PartiesListActivity.class);
            startActivity(intent);
            mToastPartyDelete.show();
        }else{
            mToastPartyNotDeletable.show();
        }
    }

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
}
