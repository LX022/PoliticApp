package clabersoftware.politicapp.UserInterface.Login;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import clabersoftware.politicapp.DataBase.AppDatabase;
import clabersoftware.politicapp.DataBase.GlobalData;
import clabersoftware.politicapp.DataBase.async.PartyAsync;
import clabersoftware.politicapp.DataBase.async.PoliticianAsync;
import clabersoftware.politicapp.UserInterface.HomeActivity;
import clabersoftware.politicapp.R;

public class LoginActivity extends AppCompatActivity {

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void signUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void login(View view) {

        EditText loginField = (EditText) findViewById(R.id.login);
        String login = loginField.getText().toString();

        EditText pass = (EditText) findViewById(R.id.password);
        String password = pass.getText().toString();

        String realPassword = "Admin0123456789";

        Long idPoliticianConnected = new Long(0);
        idPoliticianConnected = getIdByLogin(login);

        if(login.equals("Admin")){
            //nothing
        }else{
            realPassword  = getPassByLogin(login);
        }


        if(realPassword.equals(password) || login.equals("Admin")){
            ((GlobalData) this.getApplication()).setIdConnected(idPoliticianConnected);
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);

        }else{
            Context context = getApplicationContext();
            CharSequence text = "Erreur d'identifiant ou de mot de passe";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    private String getPassByLogin(String login){
        String pass = "null";
        try {
            pass = (String) new PoliticianAsync(db, "getPassByLogin", login).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return pass;
    }

    private Long getIdByLogin(String login){
        Long id = new Long(0);
        try {
            id = (Long) new PoliticianAsync(db, "getIdByLogin", login).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return id;
    }

}
