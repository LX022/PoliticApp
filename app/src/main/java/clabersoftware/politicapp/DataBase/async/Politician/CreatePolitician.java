package clabersoftware.politicapp.DataBase.async.Politician;

import android.app.Application;
import android.os.AsyncTask;

import clabersoftware.politicapp.DataBase.Entity.PoliticianEntity;
import clabersoftware.politicapp.Util.OnAsyncEventListener;
import clabersoftware.politicapp.ViewModel.BaseApp;

public class CreatePolitician extends AsyncTask<PoliticianEntity, Void, Void> {

    private static final String TAG = "CreatePolitician";

    private Application mApplication;
    private OnAsyncEventListener mCallBack;
    private Exception mException;

    public CreatePolitician(Application application, OnAsyncEventListener callback) {
        mApplication = application;
        mCallBack = callback;
    }

    @Override
    protected Void doInBackground(PoliticianEntity... params) {
        try {
            for (PoliticianEntity politician : params)
                ((BaseApp) mApplication).getPoliticianRepository()
                        .insert(politician);
        } catch (Exception e) {
            mException = e;
        }
        return null;
    }

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
