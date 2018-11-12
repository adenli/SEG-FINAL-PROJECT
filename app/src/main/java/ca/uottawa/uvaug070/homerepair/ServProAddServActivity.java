/*
Activity where the Service provider can tap on a service and add it to their provided service
 */
package ca.uottawa.uvaug070.homerepair;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ServProAddServActivity extends AppCompatActivity {

    DatabaseReference databaseServices;
    DatabaseReference databaseServPro;
    ListView servaddview;
    List<Service> services;
    protected void onCreate(Bundle savedInstanceState) {
        services = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servproaddserv);
        Intent intent = getIntent();
        servaddview = findViewById(R.id.servaddview);
        databaseServices = FirebaseDatabase.getInstance().getReference("services");
        databaseServPro = FirebaseDatabase.getInstance().getReference("serviceproviders");
    }


    protected void onStart() {
        super.onStart();
        databaseServices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                services.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Service temp = postSnapshot.getValue(Service.class);
                    services.add(temp);
                }

                createList();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void createList() {

        ArrayList<String> service = new ArrayList<>();
        service.clear();
        DecimalFormat df2 = new DecimalFormat(".##");
        for (Service temp1 : services) {
            service.add(temp1.getName() + "\n" + "$" + df2.format(temp1.getRate()) + "/hour");
        }

        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this, R.layout.simple_list_item_1, service);
        servaddview.setAdapter(arrayAdapter2);

        registerForContextMenu(servaddview);
        servaddview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                //Do something with the string that you justgot!
                Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT).show();
            }
        });

        servaddview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }

    private void addServToPro() {

    }
}
