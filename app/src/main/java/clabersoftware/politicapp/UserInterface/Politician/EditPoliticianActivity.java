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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import clabersoftware.politicapp.DataBase.AppDatabase;
import clabersoftware.politicapp.DataBase.Entity.PartyEntity;
import clabersoftware.politicapp.DataBase.Entity.PoliticianEntity;
import clabersoftware.politicapp.DataBase.async.PartyAsync;
import clabersoftware.politicapp.DataBase.async.PoliticianAsync;
import clabersoftware.politicapp.R;
import clabersoftware.politicapp.UserInterface.BaseActivity;
import clabersoftware.politicapp.UserInterface.HomeActivity;

public class EditPoliticianActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

    private AppDatabase db;
    private Spinner mParty;
    PoliticianEntity politicianToEdit;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_politician);
        //récupération du politicien cliqué
        Intent Intent = getIntent();
        Long idPoliticianToEdit = Intent.getLongExtra("POLITICIAN_SELECTED", 1);

        //initialisation du formulaire
        politicianToEdit = getById(idPoliticianToEdit);

        TextView firstName = (TextView) findViewById(R.id.firstNameField);
        firstName.setText(politicianToEdit.getFirstName());

        TextView name = (TextView) findViewById(R.id.lastNameField);
        name.setText(politicianToEdit.getLastName());

        TextView login = (TextView) findViewById(R.id.loginField);
        login.setText(politicianToEdit.getLogin());

        TextView password = (TextView) findViewById(R.id.passField);
        password.setText(politicianToEdit.getPassword());

        TextView confirmation = (TextView) findViewById(R.id.passConfirmationField);
        confirmation.setText(politicianToEdit.getPassword());

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
        PoliticianEntity politicianUpdated = new PoliticianEntity();

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
        long idParty = getIdByName(partyName);

        politicianUpdated.setFkParty(idParty);

        Intent Intent = getIntent();







        Long idPoliticianToEdit = Intent.getLongExtra("POLITICIAN_SELECTED",1);
        System.out.println("On save :  " + idPoliticianToEdit);
        politicianUpdated.setIdPolitician(idPoliticianToEdit);

        new PoliticianAsync(db,"update",politicianUpdated).execute().get();

        Intent intent = new Intent(this, PoliticianListActivity.class);
        startActivity(intent);
    }
    //initialisation de la liste des politiciens
    private PoliticianEntity getById(Long id){
        PoliticianEntity politicianToEdit = new PoliticianEntity();
        try {
            politicianToEdit = (PoliticianEntity) new PoliticianAsync(db, "getById", id).execute().get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return politicianToEdit;
    }
    //supression du politicien
    public void deletePolitician(View view) throws ExecutionException, InterruptedException{
        PoliticianEntity politicianToDelete = new PoliticianEntity();

        TextView firstName = (TextView) findViewById(R.id.firstNameField);
        politicianToDelete.setFirstName(firstName.getText().toString());

        TextView name = (TextView) findViewById(R.id.lastNameField);
        politicianToDelete.setLastName(name.getText().toString());

        TextView login = (TextView) findViewById(R.id.loginField);
        politicianToDelete.setLogin(login.getText().toString());

        TextView password = (TextView) findViewById(R.id.passField);
        politicianToDelete.setPassword(password.getText().toString());

        Spinner party = (Spinner) findViewById(R.id.partySpinner);
        String partyName = party.getSelectedItem().toString();
        long idParty = getIdByName(partyName);

        politicianToDelete.setFkParty(idParty);


        Intent Intent = getIntent();
        Long idPoliticianToDelete = Intent.getLongExtra("POLITICIAN_SELECTED",1);
        politicianToDelete.setIdPolitician(idPoliticianToDelete);

        new PoliticianAsync(db,"delete",politicianToDelete).execute().get();

        Intent intent = new Intent(this, PoliticianListActivity.class);
        startActivity(intent);
    }
    //récupération des partis pour les affectés aux politiciens
    private List<String> getAllPartiesName(){
        int i=-1;
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
            i++;
            partiesToSend.add(p.getShortName());
            if(p.getIdParty()==politicianToEdit.getFkParty()){
                position=i;
            }
        }


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
    //récupération de l'id du parti pour initialiser l'édition du politicien
    private Long getIdByName(String name){
        Long id = new Long(0);

        try {
            id = (Long) new PartyAsync(db, "getIdByName", name).execute().get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void onBackPressed() {
        this.startActivity(new Intent(this,HomeActivity.class));
    }
}
