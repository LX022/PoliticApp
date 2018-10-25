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

import clabersoftware.politicapp.ActionBar;
import clabersoftware.politicapp.HomeActivity;
import clabersoftware.politicapp.R;

public class PartiesActivity extends ActionBar {

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

    public void editParty(View view) {
        Intent intent = new Intent(this, AddPartyActivity.class);
        startActivity(intent);
    }

    public void deleteParty(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("@string/deletePartyTitle")
                .setMessage("@string/deletePartyMessage")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // méthode delete
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // on ne fait rien
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

    public void partyDetails(View view) {
        Intent intent = new Intent(this, PartyActivity.class);
        startActivity(intent);
    }
}
