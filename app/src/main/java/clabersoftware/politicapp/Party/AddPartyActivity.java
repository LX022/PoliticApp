package clabersoftware.politicapp.Party;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import clabersoftware.politicapp.ActionBar;
import clabersoftware.politicapp.DataBase.PartyEntity;
import clabersoftware.politicapp.DataBase.async.CreateParty;
import clabersoftware.politicapp.MainActivity;
import clabersoftware.politicapp.R;
import clabersoftware.politicapp.Util.OnAsyncEventListener;

public class AddPartyActivity extends ActionBar {

    private static final String TAG = "RegisterActivity";

    private Toast mToast;

    private EditText mColor;
    private EditText mShortName;
    private EditText mLongName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_party);
        mToast = Toast.makeText(this, getString(R.string.partyCreated), Toast.LENGTH_LONG);
    }


    private void initializeForm() {
        mColor = findViewById(R.id.partyColorField);
        mShortName = findViewById(R.id.partyAbbreviationField);
        mLongName = findViewById(R.id.partyNameField);
        Button saveBtn = findViewById(R.id.partyAddButton);
        saveBtn.setOnClickListener(view -> saveChanges(
                mColor.getText().toString(),
                mShortName.getText().toString(),
                mLongName.getText().toString()
        ));
    }

    private void saveChanges(String color, String shortName, String longName){

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

