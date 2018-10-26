package clabersoftware.politicapp.Politician;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import clabersoftware.politicapp.ActionBar;
import clabersoftware.politicapp.Party.PartiesActivity;
import clabersoftware.politicapp.Party.Party;
import clabersoftware.politicapp.Party.PartyAdapter;
import clabersoftware.politicapp.R;

public class PoliticiansActivity extends ActionBar {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politicians);

        mListView = (ListView) findViewById(R.id.listView);

        List<Politician> politicians = genererPolitician();

        PoliticianAdapter adapter = new PoliticianAdapter(PoliticiansActivity.this, politicians);

        mListView.setAdapter(adapter);
    }

    private List<Politician> genererPolitician(){
        List<Politician> list = new ArrayList<Politician>();
        list.add(new Politician("Mathias Reynard", "Je viens du parti socialiste et j'ai été candidat à la ISS", new Party(1, "PS", "Parti Socialiste")));
        list.add(new Politician("Mathias Reynard", "Je viens du parti socialiste et j'ai été candidat à la ISS", new Party(1, "PS", "Parti Socialiste")));
        list.add(new Politician("Mathias Reynard", "Je viens du parti socialiste et j'ai été candidat à la ISS", new Party(1, "PS", "Parti Socialiste")));
        list.add(new Politician("Mathias Reynard", "Je viens du parti socialiste et j'ai été candidat à la ISS", new Party(1, "PS", "Parti Socialiste")));
        list.add(new Politician("Mathias Reynard", "Je viens du parti socialiste et j'ai été candidat à la ISS", new Party(1, "PS", "Parti Socialiste")));
        list.add(new Politician("Mathias Reynard", "Je viens du parti socialiste et j'ai été candidat à la ISS", new Party(1, "PS", "Parti Socialiste")));


        return list;
    }

}
