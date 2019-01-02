package clabersoftware.politicapp.UserInterface.VotingObject;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import clabersoftware.politicapp.DataBase.AppDatabase;
import clabersoftware.politicapp.DataBase.Entity.VotingLineEntity;
import clabersoftware.politicapp.DataBase.Entity.VotingObjectEntity;
import clabersoftware.politicapp.DataBase.async.VotingLineAsync;
import clabersoftware.politicapp.DataBase.async.VotingObjectAsync;
import clabersoftware.politicapp.R;
import clabersoftware.politicapp.UserInterface.BaseActivity;
import clabersoftware.politicapp.UserInterface.HomeActivity;

public class PieChartResultActivity extends BaseActivity {

    private static String TAG="PieChartActivity";

    private float[]yData = new float[3];
    private String[]xData = {"Oui", "Non", "Blanc"};
    PieChart pieChart;
    private AppDatabase db;

    int QtyYes = 0;
    int QtyNo = 0;
    int QtyBlank = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart_result);
        db = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
        Log.d(TAG, "onCreate: starting to create chart");

        /*Décompte du nombre de vote oui, non et blanc*/
        Intent Intent = getIntent();
        Long idVotingObject = Intent.getLongExtra("VOTING_OBJECT_SELECTED", 1);
        System.out.println("IdVotingObject : " + idVotingObject);
        List<VotingLineEntity> votingLines = getVotingLineById(idVotingObject);

        for (VotingLineEntity vle : votingLines) {
            switch (vle.getVote()) {
                case "Yes":
                    QtyYes++;
                    break;
                case "No":
                    QtyNo++;
                    break;
                case "Blank":
                    QtyBlank++;
                    break;
            }
        }

        yData[0] = (float) QtyYes;
        yData[1] = (float) QtyNo;
        yData[2] = (float) QtyBlank;


        /*Création du PieChart*/
        pieChart = (PieChart) findViewById(R.id.resultPieChart);
        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Résultat");
        pieChart.setCenterTextSize(15);

        addDataSet(pieChart);
    }

    /*Ajout des data et des paramètre de visualisation du pie chart*/
    private void addDataSet(PieChart chart) {
        Log.d(TAG, "addDataSet started");
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for(int i = 0; i < yData.length; i++){
            yEntrys.add(new PieEntry(yData[i] , i));
        }

        for(int i = 1; i < xData.length; i++){
            xEntrys.add(xData[i]);
        }

        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Result Votation");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(17);

        //Result color
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GREEN);
        colors.add(Color.RED);
        colors.add(Color.GRAY);

        pieDataSet.setColors(colors);

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();


    }

    private List<VotingLineEntity> getVotingLineById(Long id) {
        List<VotingLineEntity> votingLines = new ArrayList<>();

        try {
            votingLines = (ArrayList) new VotingLineAsync(db, "getVotingLineByIdVotingObject", id).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return votingLines;

    }

    @Override
    public void onBackPressed() {
        this.startActivity(new Intent(this,HomeActivity.class));
    }
}
