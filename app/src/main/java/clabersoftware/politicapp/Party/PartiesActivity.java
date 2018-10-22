package clabersoftware.politicapp.Party;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

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

    private List<Party> genererParties(){
        List<Party> parties = new ArrayList<Party>();
        parties.add(new Party(Color.BLACK, "PDC", "Parti Démocrate Chrétien"));
        parties.add(new Party(Color.BLUE, "Kevin", "C'est ici que ça se passe !"));
        parties.add(new Party(Color.GREEN, "Logan", "Que c'est beau..."));
        parties.add(new Party(Color.RED, "Mathieu", "Il est quelle heure ??"));
        parties.add(new Party(Color.GRAY, "Willy", "On y est presque"));
        return parties;
    }
}
