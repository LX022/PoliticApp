package clabersoftware.politicapp.Party;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import clabersoftware.politicapp.R;

public class EditPartyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_party);
    }

    public void save(View view){
        Intent intent = new Intent(this, PartiesActivity.class);
        startActivity(intent);
    }
}
