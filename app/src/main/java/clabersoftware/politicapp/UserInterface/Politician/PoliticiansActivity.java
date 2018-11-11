package clabersoftware.politicapp.UserInterface.Politician;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import clabersoftware.politicapp.UserInterface.ActionBar;
import clabersoftware.politicapp.Adapter.PoliticianAdapter;
import clabersoftware.politicapp.UserInterface.Party.Party;
import clabersoftware.politicapp.R;

public class PoliticiansActivity extends ActionBar {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politicians);

        mListView = (ListView) findViewById(R.id.listView);

        List<PoliticianOld> politicians = genererPolitician();

        PoliticianAdapter adapter = new PoliticianAdapter(PoliticiansActivity.this, politicians);

        mListView.setAdapter(adapter);
    }

    private List<PoliticianOld> genererPolitician(){
        List<PoliticianOld> list = new ArrayList<PoliticianOld>();
        list.add(new PoliticianOld("Mathias Reynard", "Je viens du parti socialiste et j'ai été candidat à la ISS", new Party(1, "PS", "Parti Socialiste")));
        list.add(new PoliticianOld("Mathias Reynard", "Je viens du parti socialiste et j'ai été candidat à la ISS", new Party(1, "PS", "Parti Socialiste")));
        list.add(new PoliticianOld("Mathias Reynard", "Je viens du parti socialiste et j'ai été candidat à la ISS", new Party(1, "PS", "Parti Socialiste")));
        list.add(new PoliticianOld("Mathias Reynard", "Je viens du parti socialiste et j'ai été candidat à la ISS", new Party(1, "PS", "Parti Socialiste")));
        list.add(new PoliticianOld("Mathias Reynard", "Je viens du parti socialiste et j'ai été candidat à la ISS", new Party(1, "PS", "Parti Socialiste")));
        list.add(new PoliticianOld("Mathias Reynard", "Je viens du parti socialiste et j'ai été candidat à la ISS", new Party(1, "PS", "Parti Socialiste")));


        return list;
    }

}
