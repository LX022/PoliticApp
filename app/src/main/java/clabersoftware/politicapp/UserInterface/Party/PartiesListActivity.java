package clabersoftware.politicapp.UserInterface.Party;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.icu.text.MessagePattern;
import android.support.v7.app.AppCompatActivity;
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
import clabersoftware.politicapp.DataBase.async.Party.PartyAsync;
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

        theListView = (ListView) findViewById(R.id.PartiesListView);

        //List<PartyEntity> datas = genererParties();
        List<PartyEntity> datas = new ArrayList<PartyEntity>();

        PartyEntity p1 = new PartyEntity("blue", "PlR", "parti libéral");
        p1.setIdParty((long) 1);

        PartyEntity p2 = new PartyEntity("Rouge", "PS", "parti socialiste");
        p2.setIdParty((long) 2);

        datas.add(p1);
        datas.add(p2);

        ArrayAdapter<PartyEntity> PartiesAdapter = new ArrayAdapter<PartyEntity>(this, android.R.layout.simple_list_item_1, datas);

        theListView.setAdapter(PartiesAdapter);

        theListView.setOnItemClickListener( listClick );
    }

    private AdapterView.OnItemClickListener listClick = new AdapterView.OnItemClickListener () {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            PartyEntity itemValue = (PartyEntity) theListView.getItemAtPosition( position );
            itemValue.getIdParty();
            myIntent.putExtra("PARTY_SELECTED", itemValue.getIdParty());
            System.out.println(itemValue.getIdParty());
            startActivity(myIntent);
        }
    };

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


}


