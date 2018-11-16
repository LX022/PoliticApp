package clabersoftware.politicapp.UserInterface.Politician;

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
import clabersoftware.politicapp.DataBase.Entity.PoliticianEntity;
import clabersoftware.politicapp.DataBase.async.PoliticianAsync;
import clabersoftware.politicapp.R;
import clabersoftware.politicapp.UserInterface.BaseActivity;

public class PoliticianListActivity extends BaseActivity {

    ListView theListView;
    Intent myIntent;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politician_list);
        myIntent = new Intent(this, EditPoliticianActivity.class);

        theListView = (ListView) findViewById(R.id.PoliticianListView);

        List<PoliticianEntity> datas = generatePolitician();

        ArrayAdapter<PoliticianEntity> PartiesAdapter = new ArrayAdapter<PoliticianEntity>(this, android.R.layout.simple_list_item_1, datas);

        theListView.setAdapter(PartiesAdapter);

        theListView.setOnItemClickListener( listClick );
    }

    private AdapterView.OnItemClickListener listClick = new AdapterView.OnItemClickListener () {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            PoliticianEntity itemValue = (PoliticianEntity) theListView.getItemAtPosition( position );
            itemValue.getIdPolitician();
            myIntent.putExtra("POLITICIAN_SELECTED", itemValue.getIdPolitician());
            System.out.println(itemValue.getIdPolitician());
            startActivity(myIntent);
        }
    };

    private List<PoliticianEntity> generatePolitician(){
        List<PoliticianEntity> politicians = new ArrayList<>();
        try {
            politicians = (ArrayList) new PoliticianAsync(db, "getAll", 0).execute().get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return politicians;
    }
}
