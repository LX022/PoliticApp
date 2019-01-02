package clabersoftware.politicapp.UserInterface.Party;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import clabersoftware.politicapp.DataBase.AppDatabase;
import clabersoftware.politicapp.DataBase.Entity.PartyFB;
import clabersoftware.politicapp.DataBase.async.PartyAsync;
import clabersoftware.politicapp.UserInterface.BaseActivity;
import clabersoftware.politicapp.DataBase.Entity.PartyEntity;
import clabersoftware.politicapp.R;
import clabersoftware.politicapp.UserInterface.HomeActivity;

public class AddPartyActivity extends BaseActivity {

    private DatabaseReference ref;
    private FirebaseDatabase database;

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

        ref = database.getInstance().getReference();
    }
    // initialisation du formulaire
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

    //ajout d'un parti d'après les informations reçues dans le formulaire
    private void saveChanges(String color, String shortName, String longName){
        PartyFB newParty = new PartyFB();
        String pUuid = UUID.randomUUID().toString();

        newParty.setPartyUid(pUuid);
        newParty.setShortName(shortName);
        newParty.setLongName(longName);

        System.out.println("olééééé");
        mToast.show();
        Intent intent = new Intent(this, PartiesListActivity.class);
        startActivity(intent);

        ref.child("parties").child(newParty.getPartyUid()).setValue(newParty);
    }

    @Override
    public void onBackPressed() {
        this.startActivity(new Intent(this, HomeActivity.class));
    }
}

