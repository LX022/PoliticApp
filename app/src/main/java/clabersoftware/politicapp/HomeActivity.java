package clabersoftware.politicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
    public void showPartiesList(View view) {
        Intent intent = new Intent(this, PartiesActivity.class);
        startActivity(intent);
    }
    public void showPoliticiansList(View view) {
        Intent intent = new Intent(this, PoliticiansActivity.class);
        startActivity(intent);
    }
}
