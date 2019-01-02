package clabersoftware.politicapp.UserInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import clabersoftware.politicapp.DataBase.DatasGenerator;
import clabersoftware.politicapp.DataBase.GlobalData;
import clabersoftware.politicapp.R;
import clabersoftware.politicapp.UserInterface.Party.PartiesListActivity;
import clabersoftware.politicapp.UserInterface.Politician.PoliticianListActivity;
import clabersoftware.politicapp.UserInterface.Politician.PoliticiansActivity;
import clabersoftware.politicapp.UserInterface.VotingObject.ResultsListActivity;
import clabersoftware.politicapp.UserInterface.VotingObject.VoteListActivity;
import clabersoftware.politicapp.UserInterface.VotingObject.VotingObjectsListActivity;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Id Connecté : "+((GlobalData) this.getApplication()).getIdConnected());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Permet de générer les data si aucune
        Boolean GenerateAll = false;
        if (GenerateAll){
            DatasGenerator d = new DatasGenerator();
            d.GenerateData();
        }
    }
    //liste des partis
    public void showPartiesList(View view) {
        Intent intent = new Intent(this, PartiesListActivity.class);
        startActivity(intent);
    }
    //liste des politiciens
    public void showPoliticiansList(View view) {
        Intent intent = new Intent(this, PoliticianListActivity.class);
        startActivity(intent);
    }
    //liste des object de vote
    public void showVotingObjectList(View view) {
        Intent intent = new Intent(this, VotingObjectsListActivity.class);
        startActivity(intent);
    }
    //possibilité de voter
    public void vote(View view) {
        Intent intent = new Intent(this, VoteListActivity.class);
        startActivity(intent);
    }
    //aller aux résultats
    public void results(View view) {
        Intent intent = new Intent(this, ResultsListActivity.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        this.startActivity(new Intent(this,HomeActivity.class));
    }
}
