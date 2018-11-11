package clabersoftware.politicapp.DataBase.async.VotingObject;

import android.app.Application;
import android.os.AsyncTask;

import clabersoftware.politicapp.DataBase.Entity.VotingObjectEntity;
import clabersoftware.politicapp.Util.OnAsyncEventListener;
import clabersoftware.politicapp.ViewModel.BaseApp;

public class UpdateVotingObject extends AsyncTask<VotingObjectEntity, Void, Void> {

    private static final String TAG = "UpdateVotingObject";

    private Application mApplication;
    private OnAsyncEventListener mCallBack;
    private Exception mException;

    public UpdateVotingObject(Application application, OnAsyncEventListener callback) {
        mApplication = application;
        mCallBack = callback;
    }

    @Override
    protected Void doInBackground(VotingObjectEntity... params) {
        try {
            for (VotingObjectEntity votingObject : params)
                ((BaseApp) mApplication).getVotingObjectRepository()
                        .update(votingObject);
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
