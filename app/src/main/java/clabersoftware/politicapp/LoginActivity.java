package clabersoftware.politicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {

        EditText name = (EditText) findViewById(R.id.userName);
        String userName = name.getText().toString();

        EditText pass = (EditText) findViewById(R.id.userName);
        String password = pass.getText().toString();


        if(userName.equals("a")&& password.equals("a")){
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        System.out.println("Nom :" + userName);
        System.out.println("Pass :" + password);


    }
}
