package clabersoftware.politicapp.DataBase.async.Party;

import android.app.Application;
import android.os.AsyncTask;

import clabersoftware.politicapp.DataBase.Entity.PartyEntity;
import clabersoftware.politicapp.Util.OnAsyncEventListener;
import clabersoftware.politicapp.ViewModel.BaseApp;

public class UpdateParty extends AsyncTask<PartyEntity, Void, Void> {

    private static final String TAG = "UpdateClient";

    private Application mApplication;
    private OnAsyncEventListener mCallBack;
    private Exception mException;

    public UpdateParty(Application application, OnAsyncEventListener callback) {
        mApplication = application;
        mCallBack = callback;
    }

    @Override
    protected Void doInBackground(PartyEntity... params) {
        try {
            for (PartyEntity client : params)
                ((BaseApp) mApplication).getPartyRepository()
                        .update(client);
        } catch (Exception e) {
            mException = e;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (mCallBack != null) {
            if (mException == null) {
                mCallBack.onSuccess();
            } else {
                mCallBack.onFailure(mException);
            }
        }
    }
}
