package clabersoftware.politicapp.DataBase.async;

import android.app.Application;
import android.os.AsyncTask;

import clabersoftware.politicapp.DataBase.PartyEntity;
import clabersoftware.politicapp.Util.OnAsyncEventListener;
import clabersoftware.politicapp.ViewModel.BaseApp;

public class DeleteParty extends AsyncTask<PartyEntity, Void, Void> {

    private static final String TAG = "DeleteClient";

    private Application mApplication;
    private OnAsyncEventListener mCallBack;
    private Exception mException;

    public DeleteParty(Application application, OnAsyncEventListener callback) {
        mApplication = application;
        mCallBack = callback;
    }

    @Override
    protected Void doInBackground(PartyEntity... params) {
        try {
            for (PartyEntity party : params)
                ((BaseApp) mApplication).getPartyRepository()
                        .delete(party);
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
