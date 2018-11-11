package clabersoftware.politicapp.UserInterface.Party;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import clabersoftware.politicapp.DataBase.Entity.PartyEntity;
import clabersoftware.politicapp.R;
import clabersoftware.politicapp.ViewModel.Party.PartyViewModel;

public class EditPartyActivity extends AppCompatActivity {


    private final String TAG = "EditAccountActivity";

    private PartyEntity mParty;
    private String mLongname;
    private String mShortname;
    private String mColor;
    private boolean mEditMode;
    private Toast mToast;
    private EditText mEtAccountName;

    private PartyViewModel mViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_party);
    }

    public void save(View view){
        Intent intent = new Intent(this, PartiesActivity.class);
        startActivity(intent);
    }

    private void saveChanges(Long PartyId) {
        //if (mEditMode) {
          //  if(!"".equals(PartyId)) {
            //    mParty.setName(accountName);
              //  mViewModel.updateAccount(mAccount);
            //}
        //} else {
            PartyEntity newParty = new PartyEntity();
            newParty.setShortName(mLongname);
            newParty.setShortName(mShortname);
            newParty.setColor(mColor);
            mViewModel.createParty(newParty);
        //}
    }
}
