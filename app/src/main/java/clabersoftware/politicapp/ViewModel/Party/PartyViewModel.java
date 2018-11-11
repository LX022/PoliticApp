package clabersoftware.politicapp.ViewModel.Party;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.util.Log;

import clabersoftware.politicapp.DataBase.PartyEntity;
import clabersoftware.politicapp.DataBase.Repository.PartyRepository;
import clabersoftware.politicapp.DataBase.async.Party.CreateParty;
import clabersoftware.politicapp.DataBase.async.Party.DeleteParty;
import clabersoftware.politicapp.DataBase.async.Party.UpdateParty;
import clabersoftware.politicapp.Util.OnAsyncEventListener;
import clabersoftware.politicapp.ViewModel.BaseApp;

public class PartyViewModel extends AndroidViewModel{

    private static final String TAG = "PartyViewModel";

    private PartyRepository mRepository;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<PartyEntity> mObservableParty;

    public PartyViewModel(@NonNull Application application, final Long partyId, PartyRepository partyRepository) {
        super(application);

        mRepository = partyRepository;

        mObservableParty = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        mObservableParty.setValue(null);

        LiveData<PartyEntity> party = mRepository.getParty(partyId);

        // observe the changes of the client entity from the database and forward them
        mObservableParty.addSource(party, mObservableParty::setValue);
    }



    /**
     * A creator is used to inject the account id into the ViewModel
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;

        private final Long mPartyId;

        private final PartyRepository mRepository;

        public Factory(@NonNull Application application, Long partyId) {
            mApplication = application;
            mPartyId = partyId;
            mRepository = ((BaseApp) application).getPartyRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new PartyViewModel(mApplication, mPartyId, mRepository);
        }
    }

    /**
     * Expose the LiveData ClientEntity query so the UI can observe it.
     */
    public LiveData<PartyEntity> getParty() {
        return mObservableParty;
    }

    public void updateClient(PartyEntity party, OnAsyncEventListener callback) {
        new UpdateParty(getApplication(), new OnAsyncEventListener() {
            @Override
            public void onSuccess() {
                callback.onSuccess();
                Log.d(TAG, "updateParty: success");
            }

            @Override
            public void onFailure(Exception e) {
                callback.onFailure(e);
                Log.d(TAG, "updateParty: failure", e);
            }
        });
    }

    public void createParty(PartyEntity party) {
        new CreateParty(getApplication(), new OnAsyncEventListener() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "createParty: success");
            }

            @Override
            public void onFailure(Exception e) {
                Log.d(TAG, "createParty: failure", e);
            }
        }).execute(party);
    }

    public void deleteParty(PartyEntity party, OnAsyncEventListener callback) {
        new DeleteParty(getApplication(), new OnAsyncEventListener() {
            @Override
            public void onSuccess() {
                callback.onSuccess();
                Log.d(TAG, "deleteParty: success");
            }

            @Override
            public void onFailure(Exception e) {
                callback.onFailure(e);
                Log.d(TAG, "deleteParty: failure", e);
            }
        }).execute(party);
    }
}
