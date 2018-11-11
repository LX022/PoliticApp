package clabersoftware.politicapp.ViewModel;

import android.app.Application;

import clabersoftware.politicapp.DataBase.AppDatabase;
import clabersoftware.politicapp.DataBase.Repository.PartyRepository;
import clabersoftware.politicapp.DataBase.Repository.PoliticianRepository;
import clabersoftware.politicapp.DataBase.Repository.VotingLineRepository;
import clabersoftware.politicapp.DataBase.Repository.VotingObjectRepository;

public class BaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public AppDatabase getDatabase() {
        return AppDatabase.getInstance(this);
    }


    public PartyRepository getPartyRepository() {
        return PartyRepository.getInstance(getDatabase());
    }

    public PoliticianRepository getPoliticianRepository() {
        return PoliticianRepository.getInstance(getDatabase());

    }
    public VotingObjectRepository getVotingObjectRepository(){
        return VotingObjectRepository.getInstance(getDatabase());
    }

    public VotingLineRepository getVotingLineRepository(){
        return VotingLineRepository.getInstance(getDatabase());
    }

}
