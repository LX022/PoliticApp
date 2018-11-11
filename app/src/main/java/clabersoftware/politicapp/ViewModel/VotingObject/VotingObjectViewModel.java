package clabersoftware.politicapp.ViewModel.VotingObject;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.util.Log;

import clabersoftware.politicapp.DataBase.Entity.VotingObjectEntity;
import clabersoftware.politicapp.DataBase.Repository.VotingObjectRepository;
import clabersoftware.politicapp.DataBase.async.VotingObject.CreateVotingObject;
import clabersoftware.politicapp.DataBase.async.VotingObject.UpdateVotingObject;
import clabersoftware.politicapp.Util.OnAsyncEventListener;
import clabersoftware.politicapp.ViewModel.BaseApp;

public class VotingObjectViewModel extends AndroidViewModel {

    private static final String TAG = "VotingObjectViewModel";

    private VotingObjectRepository mRepository;

    private final MediatorLiveData<VotingObjectEntity> mObservableVotingObject;

    public VotingObjectViewModel(@NonNull Application application, final Long idVotingObject, VotingObjectRepository votingObjectRepository) {
        super(application);

        mRepository = votingObjectRepository;

        mObservableVotingObject = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        mObservableVotingObject.setValue(null);

        LiveData<VotingObjectEntity> votingObject = mRepository.getVotingObject(idVotingObject);

        // observe the changes of the account entity from the database and forward them
        mObservableVotingObject.addSource(votingObject, mObservableVotingObject::setValue);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;

        private final Long mIdVotingObject;

        private final VotingObjectRepository mRepository;

        public Factory(@NonNull Application application, Long votingObjectId) {
            mApplication = application;
            mIdVotingObject = votingObjectId;
            mRepository = ((BaseApp) application).getVotingObjectRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new VotingObjectViewModel(mApplication, mIdVotingObject  , mRepository);
        }
    }

    public LiveData<VotingObjectEntity> getVotingObject() {
        return mObservableVotingObject;
    }

    public void createPolitician(VotingObjectEntity votingObject) {
        new CreateVotingObject(getApplication(), new OnAsyncEventListener() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "createVotingObject: success");
            }

            @Override
            public void onFailure(Exception e) {
                Log.d(TAG, "createVotingObject: failure", e);
            }
        }).execute(votingObject);
    }

    public void updatePolitician(VotingObjectEntity votingObject) {
        new UpdateVotingObject(getApplication(), new OnAsyncEventListener() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "updateVotingObject: success");
            }

            @Override
            public void onFailure(Exception e) {
                Log.d(TAG, "updateVotingObject: failure", e);
            }
        }).execute(votingObject);
    }
}
