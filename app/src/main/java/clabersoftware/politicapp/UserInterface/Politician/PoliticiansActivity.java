package clabersoftware.politicapp.UserInterface.Politician;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import clabersoftware.politicapp.UserInterface.BaseActivity;
import clabersoftware.politicapp.UserInterface.Party.Party;
import clabersoftware.politicapp.R;

public class PoliticiansActivity extends BaseActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politicians);

        mListView = (ListView) findViewById(R.id.listView);


    }
}
