package clabersoftware.politicapp.UserInterface.Party;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import clabersoftware.politicapp.DataBase.AppDatabase;
import clabersoftware.politicapp.DataBase.Entity.PartyEntity;
import clabersoftware.politicapp.DataBase.async.PartyAsync;
import clabersoftware.politicapp.R;


public class EditPartyActivity extends AppCompatActivity {

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_party);
        Intent Intent = getIntent();
        Long idPartyToEdit = Intent.getLongExtra("PARTY_SELECTED",1);

        PartyEntity PartyToEdit = getById(idPartyToEdit);

        TextView partyNameField = (TextView) findViewById(R.id.partyNameField);
        partyNameField.setText(PartyToEdit.getShortName());


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
}
