package clabersoftware.politicapp.DataBase.async.VotingLine;

import android.app.Application;
import android.os.AsyncTask;

import clabersoftware.politicapp.DataBase.Entity.VotingLineEntity;
import clabersoftware.politicapp.Util.OnAsyncEventListener;
import clabersoftware.politicapp.ViewModel.BaseApp;

public class UpdateVotingLine extends AsyncTask<VotingLineEntity, Void, Void> {

    private static final String TAG = "UpdateVotingLine";

    private Application mApplication;
    private OnAsyncEventListener mCallBack;
    private Exception mException;

    public UpdateVotingLine(Application application, OnAsyncEventListener callback) {
        mApplication = application;
        mCallBack = callback;
    }

    @Override
    protected Void doInBackground(VotingLineEntity... params) {
        try {
            for (VotingLineEntity votingLine : params)
                ((BaseApp) mApplication).getVotingLineRepository()
                        .update(votingLine);
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
