package clabersoftware.politicapp.UserInterface.Login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import clabersoftware.politicapp.UserInterface.HomeActivity;
import clabersoftware.politicapp.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void signUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
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
            Context context = getApplicationContext();
            CharSequence text = "Erreur d'identifiant ou de mot de passe";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

}
