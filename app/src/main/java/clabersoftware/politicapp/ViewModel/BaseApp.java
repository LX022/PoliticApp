package clabersoftware.politicapp.ViewModel;

import android.app.Application;

import clabersoftware.politicapp.DataBase.AppDatabase;
import clabersoftware.politicapp.DataBase.Repository.PartyRepository;

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

}
