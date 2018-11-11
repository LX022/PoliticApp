package clabersoftware.politicapp.ViewModel.Politician;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.util.Log;

import clabersoftware.politicapp.DataBase.Entity.PoliticianEntity;
import clabersoftware.politicapp.DataBase.Repository.PoliticianRepository;
import clabersoftware.politicapp.DataBase.async.Politician.CreatePolitician;
import clabersoftware.politicapp.DataBase.async.Politician.UpdatePolitician;
import clabersoftware.politicapp.Util.OnAsyncEventListener;
import clabersoftware.politicapp.ViewModel.BaseApp;

public class PoliticianViewModel extends AndroidViewModel {

    private static final String TAG = "PoliticianViewModel";

    private PoliticianRepository mRepository;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<PoliticianEntity> mObservablePolitician;

    public PoliticianViewModel(@NonNull Application application, final Long idPolitician, PoliticianRepository politicianRepository) {
        super(application);

        mRepository = politicianRepository;

        mObservablePolitician = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        mObservablePolitician.setValue(null);

        LiveData<PoliticianEntity> account = mRepository.getPolitician(idPolitician);

        // observe the changes of the account entity from the database and forward them
        mObservablePolitician.addSource(account, mObservablePolitician::setValue);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;

        private final Long mIdPolitician;

        private final PoliticianRepository mRepository;

        public Factory(@NonNull Application application, Long idPolitician) {
            mApplication = application;
            mIdPolitician = idPolitician;
            mRepository = ((BaseApp) application).getPoliticianRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new PoliticianViewModel(mApplication, mIdPolitician, mRepository);
        }
    }

    public LiveData<PoliticianEntity> getPolitician() {
        return mObservablePolitician;
    }

    public void createPolitician(PoliticianEntity politician) {
        new CreatePolitician(getApplication(), new OnAsyncEventListener() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "createPolitician: success");
            }

            @Override
            public void onFailure(Exception e) {
                Log.d(TAG, "createPolitician: failure", e);
            }
        }).execute(politician);
    }

    public void updatePolitician(PoliticianEntity politician) {
        new UpdatePolitician(getApplication(), new OnAsyncEventListener() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "updatePolitician: success");
            }

            @Override
            public void onFailure(Exception e) {
                Log.d(TAG, "updatePolitician: failure", e);
            }
        }).execute(politician);
    }
}
