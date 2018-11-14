package clabersoftware.politicapp.UserInterface;

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

import clabersoftware.politicapp.R;
import clabersoftware.politicapp.Search.SearchActivity;

public abstract class BaseActivity extends AppCompatActivity {



    public boolean onCreateOptionsMenu(Menu menu){
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
                intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }

    }

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