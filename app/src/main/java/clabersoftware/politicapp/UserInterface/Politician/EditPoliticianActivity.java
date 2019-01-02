package clabersoftware.politicapp.UserInterface.Politician;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import clabersoftware.politicapp.DataBase.AppDatabase;
import clabersoftware.politicapp.DataBase.Entity.PartyEntity;
import clabersoftware.politicapp.DataBase.Entity.PoliticianEntity;
import clabersoftware.politicapp.DataBase.Entity.PoliticianFB;
import clabersoftware.politicapp.DataBase.async.PartyAsync;

import clabersoftware.politicapp.R;
import clabersoftware.politicapp.UserInterface.BaseActivity;
import clabersoftware.politicapp.UserInterface.HomeActivity;

public class EditPoliticianActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

    private Spinner mParty;
    int position = 0;

    private DatabaseReference ref;
    private FirebaseDatabase database;
    String politicianUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_politician);
        //récupération du politicien cliqué
        Intent Intent = getIntent();



        politicianUid = getIntent().getExtras().getString("PoUuid");
        String firstName = getIntent().getExtras().getString("PoFirstName");
        String password = getIntent().getExtras().getString("PoPwd");
        String lastName = getIntent().getExtras().getString("PoLastName");
        String login = getIntent().getExtras().getString("PoLogin");
        String fkParty = getIntent().getExtras().getString("PoFkParty");

        //initialisation du formulaire

        TextView tfirstName = (TextView) findViewById(R.id.firstNameField);
        tfirstName.setText(firstName);

        TextView tname = (TextView) findViewById(R.id.lastNameField);
        tname.setText(lastName);

        TextView tlogin = (TextView) findViewById(R.id.loginField);
        tlogin.setText(login);

        TextView tpassword = (TextView) findViewById(R.id.passField);
        tpassword.setText(password);

        TextView tconfirmation = (TextView) findViewById(R.id.passConfirmationField);
        tconfirmation.setText(password);

        Spinner spinnerParty = (Spinner) findViewById(R.id.partySpinner);
        spinnerParty.setOnItemSelectedListener(this);
        List<String> allPartyName = getAllPartiesName();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allPartyName);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerParty.setAdapter(dataAdapter);
        spinnerParty.setSelection(position);
    }
    //enregistrement du politicien
    public void savePolitician(View view) throws ExecutionException, InterruptedException{
        PoliticianFB politicianUpdated = new PoliticianFB();

        TextView firstName = (TextView) findViewById(R.id.firstNameField);
        politicianUpdated.setFirstName(firstName.getText().toString());

        TextView name = (TextView) findViewById(R.id.lastNameField);
        politicianUpdated.setLastName(name.getText().toString());

        TextView login = (TextView) findViewById(R.id.loginField);
        politicianUpdated.setLogin(login.getText().toString());

        TextView password = (TextView) findViewById(R.id.passField);
        politicianUpdated.setPassword(password.getText().toString());

        Spinner party = (Spinner) findViewById(R.id.partySpinner);
        String partyName = party.getSelectedItem().toString();



        Intent Intent = getIntent();

        politicianUpdated.setFkParty(partyName);

        FirebaseDatabase.getInstance()
                .getReference("politicians")
                .child(politicianUid)
                .updateChildren(politicianUpdated.toMap(), new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError != null) {

                        } else {

                        }

                    }
                });
        

        Intent intent = new Intent(this, PoliticianListActivity.class);
        startActivity(intent);
    }

    //supression du politicien
    public void deletePolitician(View view){

        FirebaseDatabase.getInstance()
                .getReference("politicians")
                .child(politicianUid)
                .removeValue(new DatabaseReference.CompletionListener() {

                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                    }
                });

        Intent intent = new Intent(this, PoliticianListActivity.class);
        startActivity(intent);
    }
    //récupération des partis pour les affectés aux politiciens
    private List<String> getAllPartiesName(){
        List<String> partiesToSend = new ArrayList<>();

        partiesToSend.add("PS");
        partiesToSend.add("PLR");
        partiesToSend.add("PDC");


        return partiesToSend;
    }
    //récupération de la selection du spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //On fait rien
    }


    @Override
    public void onBackPressed() {
        this.startActivity(new Intent(this,HomeActivity.class));
    }
}
