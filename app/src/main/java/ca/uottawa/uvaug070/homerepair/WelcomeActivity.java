package ca.uottawa.uvaug070.homerepair;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Intent intent = getIntent();
        setWelcome(intent.getStringExtra("username"),intent.getStringExtra("role"));
    }
    private void setWelcome(String username,String role){
        ((TextView)findViewById(R.id.welcome_text)).setText("Welcome "+username+"!");
        ((TextView)findViewById(R.id.role_message)).setText("You are logged as "+role+".");
    }


}
