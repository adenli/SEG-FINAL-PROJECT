package ca.uottawa.uvaug070.homerepair;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ServiceActivity extends AppCompatActivity{
    DatabaseReference databaseServices;
    ListView serviceview;
    List<Service> services;
    protected void onCreate(Bundle savedInstanceState) {
        services = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        Intent intent = getIntent();
        serviceview=findViewById(R.id.serviceview);
        databaseServices = FirebaseDatabase.getInstance().getReference("services");

        addService("yolo swaggin", 42069);
    }

    private void addService(String name, int rate) {
        Service test = new Service(name, rate);
        String id = databaseServices.push().getKey();
        databaseServices.child(id).setValue(test);

    }

    @Override
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

    private void createList() {

        ArrayList<String> service=new ArrayList<>();
        service.clear();
        for (Service temp1:services){
            service.add(temp1.getName()+"\n"+temp1.getRate());
        }

        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this, R.layout.simple_list_item_1, service);
        serviceview.setAdapter(arrayAdapter2);

        serviceview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = (String)parent.getItemAtPosition(position);
                //Do something with the string that you justgot!
                Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT).show();
            }
        });
    }


}







