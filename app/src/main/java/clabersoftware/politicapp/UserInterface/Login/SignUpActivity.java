package clabersoftware.politicapp.UserInterface.Login;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import clabersoftware.politicapp.DataBase.AppDatabase;
import clabersoftware.politicapp.DataBase.Entity.PartyEntity;
import clabersoftware.politicapp.DataBase.async.PartyAsync;
import clabersoftware.politicapp.R;

public class SignUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    ListView theListView;
    Intent myIntent;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

    }

    public void confirmSignUp(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
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
        List<PartyEntity> parties = new ArrayList<>();
        List<String> partiesToSend = new ArrayList<>();

        try {
            parties = (ArrayList) new PartyAsync(db, "getAll", 0).execute().get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for(PartyEntity p: parties){
            partiesToSend.add(p.getShortName());
        }


        return partiesToSend;
    }
}
