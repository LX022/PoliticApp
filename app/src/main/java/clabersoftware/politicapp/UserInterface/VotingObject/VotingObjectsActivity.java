package clabersoftware.politicapp.UserInterface.VotingObject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import clabersoftware.politicapp.UserInterface.BaseActivity;
import clabersoftware.politicapp.R;
import clabersoftware.politicapp.Adapter.VotingObjectAdapter;

public class VotingObjectsActivity extends BaseActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting_objects);

        mListView = (ListView) findViewById(R.id.listView);

        List<VotingObject> votingObjectList = genererVotingObject();

        VotingObjectAdapter adapter = new VotingObjectAdapter(VotingObjectsActivity.this, votingObjectList);

        mListView.setAdapter(adapter);

    }

    private List<VotingObject> genererVotingObject(){
        List<VotingObject> votingObjectList = new ArrayList<VotingObject>();
        votingObjectList.add(new VotingObject("Lex Weber", "Votation sur les résidences secondaire", "01.01.2018",true ));
        votingObjectList.add(new VotingObject("Lex Weber", "Votation sur les résidences secondaire", "01.01.2018", true ));
        votingObjectList.add(new VotingObject("Lex Weber", "Votation sur les résidences secondaire", "01.01.2018", false ));
        votingObjectList.add(new VotingObject("Lex Weber", "Votation sur les résidences secondaire", "01.01.2018", false ));
        votingObjectList.add(new VotingObject("Lex Weber", "Votation sur les résidences secondaire", "01.01.2018", false ));
        votingObjectList.add(new VotingObject("Lex Weber", "Votation sur les résidences secondaire", "01.01.2018", true ));

        return votingObjectList;
    }

    public void addVotingObject(View view) {
        Intent intent = new Intent(this, AddVotingObjectActivity.class);
        startActivity(intent);
    }

    public void votingObjectDetails(View view) {
        Intent intent = new Intent(this, VotingObjectDetailsActivity.class);
        startActivity(intent);
    }

    public void editVotingObject(View view) {
        Intent intent = new Intent(this, AddVotingObjectActivity.class);
        startActivity(intent);
    }

    public void deleteVotingObject(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(R.string.deleteVotingObjectTitle)
                .setMessage(R.string.deleteVotingObjectMessage)
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
}
