package clabersoftware.politicapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class PartiesActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parties);

        mListView = (ListView) findViewById(R.id.listView);

        List<Partie> parties = genererParties();

        PartieAdapter adapter = new PartieAdapter(PartiesActivity.this, parties);

        mListView.setAdapter(adapter);



    }

    private List<Partie> genererParties(){
        List<Partie> parties = new ArrayList<Partie>();
        parties.add(new Partie(Color.BLACK, "Florent", "Mon premier tweet !"));
        parties.add(new Partie(Color.BLUE, "Kevin", "C'est ici que ça se passe !"));
        parties.add(new Partie(Color.GREEN, "Logan", "Que c'est beau..."));
        parties.add(new Partie(Color.RED, "Mathieu", "Il est quelle heure ??"));
        parties.add(new Partie(Color.GRAY, "Willy", "On y est presque"));
        return parties;
    }
}
