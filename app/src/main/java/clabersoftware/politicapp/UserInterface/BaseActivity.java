package clabersoftware.politicapp.UserInterface;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import java.util.Locale;

import clabersoftware.politicapp.DataBase.AppDatabase;
import clabersoftware.politicapp.DataBase.DAO.VotingObjectDao;
import clabersoftware.politicapp.DataBase.Entity.PartyEntity;
import clabersoftware.politicapp.DataBase.Entity.PoliticianEntity;
import clabersoftware.politicapp.DataBase.Entity.VotingLineEntity;
import clabersoftware.politicapp.DataBase.Entity.VotingObjectEntity;
import clabersoftware.politicapp.DataBase.async.PartyAsync;
import clabersoftware.politicapp.DataBase.async.PoliticianAsync;
import clabersoftware.politicapp.DataBase.async.VotingLineAsync;
import clabersoftware.politicapp.DataBase.async.VotingObjectAsync;
import clabersoftware.politicapp.R;
import clabersoftware.politicapp.Search.SearchActivity;

public abstract class BaseActivity extends AppCompatActivity {

    private AppDatabase db;


    public boolean onCreateOptionsMenu(Menu menu){
        db = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
        getMenuInflater().inflate(R.menu.main_activity_action, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_home:
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_language:
                changeLanguage();
                    return true;
            case R.id.action_search:
                initializeDatabase();
                intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }

    }

    protected void initializeDatabase(){
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

    };

    private void changeLanguage() {
        String [] languages  = {"Francais", "English"};

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setSingleChoiceItems(languages, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
            switch(i){
                case 0:
                    setLanguage("fr");
                    reaload();
                    break;
                case 1:
                    setLanguage("en");
                    reaload();
                    break;
                }
            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    private void reaload() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    private void setLanguage(String language) {
        Locale myLocale = new Locale(language);//Set Selected Locale
        Locale.setDefault(myLocale);//set new locale as default
        Configuration config = new Configuration();//get Configuration
        config.locale = myLocale;//set config locale as selected locale
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }

}
