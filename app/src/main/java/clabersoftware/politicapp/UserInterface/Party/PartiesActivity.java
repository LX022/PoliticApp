package clabersoftware.politicapp.UserInterface.Party;


import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import clabersoftware.politicapp.DataBase.AppDatabase;
import clabersoftware.politicapp.DataBase.Entity.PartyEntity;
import clabersoftware.politicapp.DataBase.async.Party.PartyAsync;
import clabersoftware.politicapp.UserInterface.BaseActivity;
import clabersoftware.politicapp.Adapter.PartyAdapter;
import clabersoftware.politicapp.R;


public class    PartiesActivity extends BaseActivity {

    private ListView mListView;
    private AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parties);
        db = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
        mListView = (ListView) findViewById(R.id.listView);

        List<PartyEntity> parties = genererParties();

        PartyAdapter adapter = new PartyAdapter(PartiesActivity.this, parties);

        mListView.setAdapter(adapter);

    }

    public void editParty(View view) {
        Intent intent = new Intent(this, EditPartyActivity.class);
        startActivity(intent);
    }

    public void deleteParty(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.deletePartyTitle)
                .setMessage(R.string.deletePartyMessage)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //new PartyAsync(db,"delete",selectedParty).execute();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // on ne fait rien
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private List<PartyEntity> genererParties(){
        List<PartyEntity> parties = new ArrayList<>();
        try {
            parties = (ArrayList) new PartyAsync(db, "getAll", 0).execute().get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return parties;
    }

    public void addParty(View view) {
        Intent intent = new Intent(this, AddPartyActivity.class);
        startActivity(intent);
    }

    public void partyDetails(View view) {
        Intent intent = new Intent(this, PartyActivity.class);
        startActivity(intent);
    }


}
