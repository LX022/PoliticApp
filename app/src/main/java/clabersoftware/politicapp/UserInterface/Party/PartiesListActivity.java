package clabersoftware.politicapp.UserInterface.Party;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import clabersoftware.politicapp.DataBase.AppDatabase;
import clabersoftware.politicapp.DataBase.Entity.PartyEntity;
import clabersoftware.politicapp.DataBase.async.PartyAsync;
import clabersoftware.politicapp.R;
import clabersoftware.politicapp.UserInterface.BaseActivity;

public class PartiesListActivity extends BaseActivity {

    ListView theListView;
    Intent myIntent;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parties_list);
        myIntent = new Intent(this, EditPartyActivity.class);

        theListView = (ListView) findViewById(R.id.PoliticianListView);

        List<PartyEntity> datas = genererParties();

        ArrayAdapter<PartyEntity> PartiesAdapter = new ArrayAdapter<PartyEntity>(this, android.R.layout.simple_list_item_1, datas);

        theListView.setAdapter(PartiesAdapter);

        theListView.setOnItemClickListener( listClick );
    }
    //Envoi de l'id du parti cliqué
    private AdapterView.OnItemClickListener listClick = new AdapterView.OnItemClickListener () {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            PartyEntity itemValue = (PartyEntity) theListView.getItemAtPosition( position );
            itemValue.getIdParty();
            myIntent.putExtra("PARTY_SELECTED", itemValue.getIdParty());
            System.out.println(itemValue.getIdParty());
            startActivity(myIntent);
        }
    };
    //génération de la liste des partis
    private List<PartyEntity> genererParties(){
        List<PartyEntity> parties = new ArrayList<>();
        try {
            parties = (ArrayList) new PartyAsync(db, "getAll", 0).execute().get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return parties;
    }
    //envoie de la vue de l'ajout de party
    public void addParty(View view) {
        Intent intent = new Intent(this, AddPartyActivity.class);
        startActivity(intent);
    }


}


