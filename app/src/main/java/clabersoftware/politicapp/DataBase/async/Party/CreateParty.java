package clabersoftware.politicapp.DataBase.async.Party;

import android.app.Application;
import android.os.AsyncTask;


import clabersoftware.politicapp.DataBase.PartyEntity;
import clabersoftware.politicapp.Util.OnAsyncEventListener;
import clabersoftware.politicapp.ViewModel.BaseApp;

public class CreateParty extends AsyncTask<PartyEntity, Void, Void> {

    private static final String TAG = "CreateParty";

    private Application mApplication;
    private OnAsyncEventListener mCallBack;
    private Exception mException;

    public CreateParty(Application application, OnAsyncEventListener callback) {
        mApplication = application;
        mCallBack = callback;

    }

    @Override
    protected Void doInBackground(PartyEntity... partyEntities) {
        try {
            for (PartyEntity party : partyEntities){
                ((BaseApp) mApplication).getPartyRepository()
                        .insert(party);
            }


        } catch (Exception e) {
            mException = e;
        }
        return null;
    }
}
