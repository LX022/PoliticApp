package clabersoftware.politicapp.Party;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import clabersoftware.politicapp.R;

public class AddPartyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_party);
    }

    public void add(View view) {
        Intent intent = new Intent(this, PartiesActivity.class);
        startActivity(intent);
    }
}
