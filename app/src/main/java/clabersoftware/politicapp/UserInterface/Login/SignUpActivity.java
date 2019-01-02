package clabersoftware.politicapp.UserInterface.Login;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import clabersoftware.politicapp.DataBase.AppDatabase;
import clabersoftware.politicapp.DataBase.Entity.PartyEntity;
import clabersoftware.politicapp.DataBase.Entity.PartyFB;
import clabersoftware.politicapp.DataBase.Entity.PoliticianEntity;
import clabersoftware.politicapp.DataBase.Entity.PoliticianFB;
import clabersoftware.politicapp.DataBase.async.PartyAsync;
import clabersoftware.politicapp.DataBase.async.PoliticianAsync;
import clabersoftware.politicapp.R;

public class SignUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private AppDatabase db;

    private DatabaseReference ref;
    private FirebaseDatabase database;

    private Toast mToastok;
    private Toast passNotOk;

    private EditText mFistName;
    private EditText mLastName;
    private EditText mlogin;
    private Spinner mParty;
    private EditText mPassword;
    private EditText mConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ref = database.getInstance().getReference();
        db = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Création du spinner
        Spinner spinnerParty = (Spinner) findViewById(R.id.partySpinner);

        //Ajout du listner
        spinnerParty.setOnItemSelectedListener(this);

        //Récupération de la liste des party à mettre dans le spinner
        List<String> allPartyName = getAllPartiesName();

        //Creation de l'adapter du spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allPartyName);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerParty.setAdapter(dataAdapter);
        initializeForm();
        mToastok = Toast.makeText(this, getString(R.string.politicianCreated), Toast.LENGTH_LONG);
        passNotOk = Toast.makeText(this, getString(R.string.mdp), Toast.LENGTH_LONG);

    }

    /*
    * Récupération des données du formulaire en vue d'un update*/
    private void initializeForm() {
        mFistName = findViewById(R.id.firstNameField);
        mLastName = findViewById(R.id.lastNameField);
        mlogin = findViewById(R.id.loginField);
        mParty = findViewById(R.id.partySpinner);
        mPassword = findViewById(R.id.passField);
        mConfirmPassword = findViewById(R.id.passConfirmationField);

        Button saveBtn = findViewById(R.id.signUpButtonSave);
        saveBtn.setOnClickListener(View -> saveChanges(
                mFistName.getText().toString(),
                mLastName.getText().toString(),
                mlogin.getText().toString(),
                mParty.getSelectedItem().toString(),
                mPassword.getText().toString(),
                mConfirmPassword.getText().toString()
        ));
    }

    /*
    * Envoi l'update à la db et fait apparaître un toaste pour indiquer à l'utilisateur que tout fonctionne*/
    private void saveChanges(String firstName, String lastName, String login, String partyName, String pass, String confirmPass){

        if(pass.equals(confirmPass)){
            System.out.println("OK POUR CREATION____________________________________________________________________:::::::::");
            String idParty = getIdByName(partyName);
            System.out.println(idParty);
            PoliticianFB newPolitician = new PoliticianFB();
            newPolitician.setLastName(lastName);
            newPolitician.setFkParty(idParty);
            newPolitician.setFirstName(firstName);
            newPolitician.setLogin(login);
            newPolitician.setPassword(pass);
            newPolitician.setPoliticianUid(UUID.randomUUID().toString());

            ref.child("politicians").child(newPolitician.getPoliticianUid()).setValue(newPolitician);

            mToastok.show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        else{
            passNotOk.show();
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //On fait rien
    }

    private List<String> getAllPartiesName(){
        List<String> partiesToSend = new ArrayList<>();

        partiesToSend.add("PS");
        partiesToSend.add("PLR");
        partiesToSend.add("PDC");
        partiesToSend.add("Sans parti");


        return partiesToSend;
    }

    private String getIdByName(String name){
        return name;
    }

    @Override
    public void onBackPressed() {
        this.startActivity(new Intent(this,LoginActivity.class));
    }

    private ArrayList<PartyFB> toParties(DataSnapshot snapshot){
        ArrayList<PartyFB> parties = new ArrayList<>();
        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
            PartyFB entity = childSnapshot.getValue(PartyFB.class);
            entity.setPartyUid(childSnapshot.getKey());
            parties.add(entity);
        }

        return parties;

    }
}
