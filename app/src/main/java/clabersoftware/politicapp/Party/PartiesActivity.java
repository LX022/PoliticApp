package clabersoftware.politicapp.Party;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import clabersoftware.politicapp.HomeActivity;
import clabersoftware.politicapp.R;

public class PartiesActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parties);

        mListView = (ListView) findViewById(R.id.listView);

        List<Party> parties = genererParties();

        PartyAdapter adapter = new PartyAdapter(PartiesActivity.this, parties);

        mListView.setAdapter(adapter);

    }

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
                default:
                    return false;
        }

    }

    public void editParty(View view) {
        Intent intent = new Intent(this, AddPartyActivity.class);
        startActivity(intent);
    }

    public void deleteParty(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Suppression du parti")
                .setMessage("Êtes-vous sur de vouloir supprimer le parti ?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }




    private List<Party> genererParties(){
        List<Party> parties = new ArrayList<Party>();
        parties.add(new Party(Color.BLACK, "PDC", "Parti Démocrate Chrétien"));
        parties.add(new Party(Color.BLUE, "PLR", "C'est ici que ça se passe !"));
        parties.add(new Party(Color.GREEN, "PS", "Que c'est beau..."));
        parties.add(new Party(Color.RED, "VERT", "Il est quelle heure ??"));
        parties.add(new Party(Color.GRAY, "UDC", "On y est presque"));
        parties.add(new Party(Color.BLACK, "PDCvr", "Parti Démocrate Chrétien"));
        parties.add(new Party(Color.BLUE, "PLRvr", "Parti Libéral Radical"));
        parties.add(new Party(Color.GREEN, "Logan", "Que c'est beau..."));
        parties.add(new Party(Color.RED, "Mathieu", "Il est quelle heure ??"));
        parties.add(new Party(Color.GRAY, "Willy", "On y est presque"));
        parties.add(new Party(Color.BLACK, "PDC", "Parti Démocrate Chrétien"));
        parties.add(new Party(Color.BLUE, "Kevin", "C'est ici que ça se passe !"));
        parties.add(new Party(Color.GREEN, "Logan", "Que c'est beau..."));
        parties.add(new Party(Color.RED, "Mathieu", "Il est quelle heure ??"));
        parties.add(new Party(Color.GRAY, "Willy", "On y est presque"));
        parties.add(new Party(Color.BLACK, "PDC", "Parti Démocrate Chrétien"));
        parties.add(new Party(Color.BLUE, "Kevin", "C'est ici que ça se passe !"));
        parties.add(new Party(Color.GREEN, "Logan", "Que c'est beau..."));
        parties.add(new Party(Color.RED, "Mathieu", "Il est quelle heure ??"));
        parties.add(new Party(Color.GRAY, "Willy", "On y est presque"));
        return parties;
    }

    public void addParty(View view) {
        Intent intent = new Intent(this, AddPartyActivity.class);
        startActivity(intent);
    }
}
