package clabersoftware.politicapp.Search;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import clabersoftware.politicapp.ActionBar;
import clabersoftware.politicapp.R;

public class SearchActivity extends ActionBar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    public void result(View view) {
        Intent intent = new Intent(this, SearchResultActivity.class);
        startActivity(intent);
    }
}
