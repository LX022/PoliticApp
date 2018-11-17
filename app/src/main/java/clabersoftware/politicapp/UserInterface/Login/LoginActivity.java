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
import clabersoftware.politicapp.DataBase.Entity.PartyEntity;
import clabersoftware.politicapp.DataBase.Entity.PoliticianEntity;
import clabersoftware.politicapp.DataBase.Entity.VotingLineEntity;
import clabersoftware.politicapp.DataBase.Entity.VotingObjectEntity;
import clabersoftware.politicapp.DataBase.GlobalData;
import clabersoftware.politicapp.DataBase.async.PartyAsync;
import clabersoftware.politicapp.DataBase.async.PoliticianAsync;
import clabersoftware.politicapp.DataBase.async.VotingLineAsync;
import clabersoftware.politicapp.DataBase.async.VotingObjectAsync;
import clabersoftware.politicapp.UserInterface.BaseActivity;
import clabersoftware.politicapp.UserInterface.HomeActivity;
import clabersoftware.politicapp.R;

public class LoginActivity extends AppCompatActivity {

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*
        * Si base vide alors on génère des données de base*/
        List<PoliticianEntity> allPolitician = generatePolitician();
        System.out.println(allPolitician.toString());
        if (allPolitician.isEmpty()){
            generateData();

        }
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

    //liste des politiciens
    private List<PoliticianEntity> generatePolitician(){
        List<PoliticianEntity> politicians = new ArrayList<>();
        try {
            politicians = (ArrayList) new PoliticianAsync(db, "getAll", 0).execute().get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return politicians;
    }

    private void generateData() {
        new PoliticianAsync(db, "deleteAll", 1).execute();
        new VotingLineAsync(db, "deleteAll", 1).execute();
        new PartyAsync(db, "deleteAll", 1).execute();
        new VotingObjectAsync(db, "deleteAll", 1).execute();

        System.out.println("Delete ALL Ok");

        PartyEntity p1 = new PartyEntity("Red", "PS", "Parti Socialiste");
        PartyEntity p2 = new PartyEntity("Blue", "PLR", "Parti Libéral Radical");
        PartyEntity p3 = new PartyEntity("White", "sans parti", "Sans Parti");

        new PartyAsync(db,"add",p1).execute();
        new PartyAsync(db,"add",p2).execute();
        new PartyAsync(db,"add",p3).execute();

        //String firstName, String lastName,String password, long fkParty, String login
        PoliticianEntity po1 = new PoliticianEntity("Grégoire","Clavien","1234",new Long(1),"g.clavien");
        PoliticianEntity po2 = new PoliticianEntity("Paul","Berclaz","1234",new Long(3),"p.berclaz");
        PoliticianEntity po3 = new PoliticianEntity("Manu","Amoos","1234",new Long(1),"m.amoos");
        PoliticianEntity po4 = new PoliticianEntity("Alexandre","Berclaz","1234",new Long(1),"a.berclaz");
        PoliticianEntity po5 = new PoliticianEntity("Yann","Clavien","1234",new Long(1),"y.clavien");
        PoliticianEntity po6 = new PoliticianEntity("Monique","Vuignier","1234",new Long(1),"m.vuignier");
        PoliticianEntity po7 = new PoliticianEntity("Noé","Zufferey","1234",new Long(1),"n.zufferey");

        new PoliticianAsync(db,"add",po1).execute();
        new PoliticianAsync(db,"add",po2).execute();
        new PoliticianAsync(db,"add",po3).execute();
        new PoliticianAsync(db,"add",po4).execute();
        new PoliticianAsync(db,"add",po5).execute();
        new PoliticianAsync(db,"add",po6).execute();
        new PoliticianAsync(db,"add",po7).execute();

        //String entitled, String details, String date
        VotingObjectEntity vo1 = new VotingObjectEntity("Lex Weber", "Votation concernant les résidences secondaires au niveau suisse", "01.01.2018");
        VotingObjectEntity vo2 = new VotingObjectEntity("LAT", "Votation sur l'aménagement du téritoire", "01.02.2018");

        new VotingObjectAsync(db,"add",vo1).execute();
        new VotingObjectAsync(db,"add",vo2).execute();

        //String vote, long fkPolitician, long fkVotingObject
        VotingLineEntity vl1 = new VotingLineEntity("Yes",new Long(1),new Long(1));
        VotingLineEntity vl2 = new VotingLineEntity("Yes",new Long(2),new Long(1));
        VotingLineEntity vl3 = new VotingLineEntity("No",new Long(3),new Long(1));
        VotingLineEntity vl4 = new VotingLineEntity("No",new Long(4),new Long(1));
        VotingLineEntity vl5 = new VotingLineEntity("No",new Long(5),new Long(1));
        VotingLineEntity vl6 = new VotingLineEntity("Blank",new Long(6),new Long(1));
        VotingLineEntity vl7 = new VotingLineEntity("No",new Long(7),new Long(1));

        new VotingLineAsync(db,"add",vl1).execute();
        new VotingLineAsync(db,"add",vl2).execute();
        new VotingLineAsync(db,"add",vl1).execute();
        new VotingLineAsync(db,"add",vl3).execute();
        new VotingLineAsync(db,"add",vl5).execute();
        new VotingLineAsync(db,"add",vl6).execute();
        new VotingLineAsync(db,"add",vl7).execute();

    }

}
