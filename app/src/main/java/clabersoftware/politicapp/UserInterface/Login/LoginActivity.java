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

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import clabersoftware.politicapp.DataBase.AppDatabase;
import clabersoftware.politicapp.DataBase.Entity.PoliticianEntity;
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

    //Boutton Sign up
    public void signUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    //Boutton Login
    public void login(View view) {

        //Récupération des données du formulaire
        EditText loginField = (EditText) findViewById(R.id.login);
        String login = loginField.getText().toString();

        EditText pass = (EditText) findViewById(R.id.password);
        String password = pass.getText().toString();

        String realPassword = "Admin0123456789";

        Long idPoliticianConnected = new Long(0);
        idPoliticianConnected = getIdByLogin(login);

        /*Control si le login existe si oui continue si non s'arrête et demande à l'utilisateur de se reconnecter */
        if (loginExist(login)){
            //noting to do
        }else{
            Context context = getApplicationContext();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            CharSequence text = "Erreur d'identifiant ou de mot de passe";
            int duration = Toast.LENGTH_SHORT;


            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }

        /*
        * On permet à un utilisateur de se connecter avec un Login "Admin" plus facile pour débuger*/
        if(login.equals("Admin")){
            //nothing
        }else{
            realPassword  = getPassByLogin(login);
        }

        /*
        * Contrôl que le pass et le login correspondent aux vrais valeurs*/
        if(realPassword.equals(password) || login.equals("Admin")){
            ((GlobalData) this.getApplication()).setIdConnected(idPoliticianConnected           );
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);

        }else{
            Context context = getApplicationContext();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            CharSequence text = "Erreur d'identifiant ou de mot de passe";
            int duration = Toast.LENGTH_SHORT;


            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    /*
    * Obtient le pass en fonction du login*/
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

    /*
    * Control si le login existe*/
    private boolean loginExist(String login){
       Long Id = null;
        try {
            Id = (Long) new PoliticianAsync(db, "getIdByLogin", login).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (Id == null)
            return false;
        else
            return true;
    }


    /*
    * Permet d'obtenir l'id d'un politicien existe en fonction du login*/
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
