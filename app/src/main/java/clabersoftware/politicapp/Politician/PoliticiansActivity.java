package clabersoftware.politicapp.Politician;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import clabersoftware.politicapp.ActionBar;
import clabersoftware.politicapp.R;

public class PoliticiansActivity extends ActionBar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politicians);
    }
}