package clabersoftware.politicapp.UserInterface.Politician;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import clabersoftware.politicapp.DataBase.AppDatabase;
import clabersoftware.politicapp.DataBase.Entity.PoliticianEntity;
import clabersoftware.politicapp.DataBase.async.PoliticianAsync;
import clabersoftware.politicapp.R;
import clabersoftware.politicapp.UserInterface.BaseActivity;

public class EditPoliticianActivity extends BaseActivity {

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_politician);

        Intent Intent = getIntent();
        Long idPoliticianToEdit = Intent.getLongExtra("POLITICIAN_SELECTED", 1);
        System.out.println("On create :  " + idPoliticianToEdit);

        PoliticianEntity politicianToEdit = getById(idPoliticianToEdit);

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
    }

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

        politicianUpdated.setFkParty(new Long(1));

        Intent Intent = getIntent();
        Long idPoliticianToEdit = Intent.getLongExtra("POLITICIAN_SELECTED",1);
        System.out.println("On save :  " + idPoliticianToEdit);
        politicianUpdated.setIdPolitician(idPoliticianToEdit);

        new PoliticianAsync(db,"update",politicianUpdated).execute().get();

        Intent intent = new Intent(this, PoliticianListActivity.class);
        startActivity(intent);
    }

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

    public void deletePolitician(View view) throws ExecutionException, InterruptedException{
        PoliticianEntity politicianToDelete = new PoliticianEntity();

        TextView firstName = (TextView) findViewById(R.id.firstNameField);
        politicianToDelete.setFirstName(firstName.getText().toString());

        TextView name = (TextView) findViewById(R.id.lastNameField);
        politicianToDelete.setFirstName(name.getText().toString());

        TextView login = (TextView) findViewById(R.id.loginField);
        politicianToDelete.setFirstName(login.getText().toString());


        TextView password = (TextView) findViewById(R.id.passField);
        politicianToDelete.setFirstName(password.getText().toString());

        TextView confirmation = (TextView) findViewById(R.id.passConfirmationField);
        politicianToDelete.setFirstName(confirmation.getText().toString());

        new PoliticianAsync(db,"delete",politicianToDelete).execute().get();

        Intent intent = new Intent(this, PoliticianListActivity.class);
        startActivity(intent);
    }
}
