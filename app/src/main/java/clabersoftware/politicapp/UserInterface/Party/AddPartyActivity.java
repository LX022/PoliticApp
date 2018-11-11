package clabersoftware.politicapp.UserInterface.Party;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import clabersoftware.politicapp.UserInterface.ActionBar;
import clabersoftware.politicapp.DataBase.Entity.PartyEntity;
import clabersoftware.politicapp.DataBase.async.Party.CreateParty;
import clabersoftware.politicapp.UserInterface.MainActivity;
import clabersoftware.politicapp.R;
import clabersoftware.politicapp.Util.OnAsyncEventListener;

public class AddPartyActivity extends ActionBar {

    private static final String TAG = "AddPartyActivity";

    private Toast mToast;

    private EditText mColor;
    private EditText mShortName;
    private EditText mLongName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_party);
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
        System.out.println("cliqué");
        System.out.println(color + " " + shortName + " " + longName);
        PartyEntity newParty = new PartyEntity(color, shortName, longName);

        new CreateParty(getApplication(), new OnAsyncEventListener() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "Party creation: success");
                setResponse(true);
            }

            @Override
            public void onFailure(Exception e) {
                Log.d(TAG, "Party creation: failure", e);
                setResponse(false);
            }
        }).execute(newParty);



    }

    private void setResponse(Boolean response) {
        if (response) {
            mToast.show();
            Intent intent = new Intent(AddPartyActivity.this, MainActivity.class);
            startActivity(intent);
        } else {

        }
    }
}

