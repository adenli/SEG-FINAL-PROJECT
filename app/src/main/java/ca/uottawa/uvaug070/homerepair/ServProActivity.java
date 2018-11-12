/*
Activity that server provider goes to after login.
Provider will see a list of all the services they provide
 */

package ca.uottawa.uvaug070.homerepair;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ServProActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servpro);
        View servproadd = findViewById(R.id.servproaddserv);

        servproadd.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ServProAddServActivity.class));
            }
        });
    }
}
