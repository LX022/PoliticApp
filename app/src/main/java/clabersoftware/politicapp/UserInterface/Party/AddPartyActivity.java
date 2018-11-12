package clabersoftware.politicapp.UserInterface.Party;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import clabersoftware.politicapp.DataBase.AppDatabase;
import clabersoftware.politicapp.DataBase.async.Party.PartyAsync;
import clabersoftware.politicapp.UserInterface.BaseActivity;
import clabersoftware.politicapp.DataBase.Entity.PartyEntity;
import clabersoftware.politicapp.DataBase.async.Party.CreateParty;
import clabersoftware.politicapp.UserInterface.MainActivity;
import clabersoftware.politicapp.R;
import clabersoftware.politicapp.Util.OnAsyncEventListener;

public class AddPartyActivity extends BaseActivity {

    private static final String TAG = "AddPartyActivity";
    private AppDatabase db;
    private Toast mToast;

    private EditText mColor;
    private EditText mShortName;
    private EditText mLongName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_party);
        db = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
        mToast = Toast.makeText(this, getString(R.string.partyCreated), Toast.LENGTH_LONG);
        initializeForm();
    }


    private void initializeForm() {
        mColor = findViewById(R.id.partyColorField);
        mShortName = findViewById(R.id.partyAbbreviationField);
        mLongName = findViewById(R.id.partyNameField);
        Button saveBtn = findViewById(R.id.partyAddButton);
        saveBtn.setOnClickListener(View -> saveChanges(
                mColor.getText().toString(),
                mShortName.getText().toString(),
                mLongName.getText().toString()
        ));
    }



    private void saveChanges(String color, String shortName, String longName){

        PartyEntity newParty = new PartyEntity(color, shortName, longName);
        new PartyAsync(db,"add",newParty);
        System.out.println("olééééé");

        ArrayList<PartyEntity> data = null;

        try {
            System.out.println("dans le try");
            data = (ArrayList) new PartyAsync(db, "getAll", 0).execute().get();

            System.out.println((ArrayList) new PartyAsync(db, "getAll", 0).execute().get());
            for(PartyEntity p:data){
                System.out.println(p.getShortName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

}

