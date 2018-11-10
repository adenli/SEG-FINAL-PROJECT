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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static android.widget.AdapterView.*;

public class AdminActivity extends AppCompatActivity {
    ListView listView;
    List<Account> accounts;
    List<Service> services;
    DatabaseReference databaseAccounts;
    DatabaseReference databaseServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        accounts = new ArrayList<>();
        services = new ArrayList<>();
        listView = findViewById(R.id.listview);
        databaseAccounts = FirebaseDatabase.getInstance().getReference("accounts");
        databaseServices = FirebaseDatabase.getInstance().getReference("services");
        addService("yolo swaggin", 42069);
        ((TextView) findViewById(R.id.welcome_text)).setText("Welcome admin!");
    }

    protected void onStart() {
        super.onStart();
        databaseAccounts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                accounts.clear();
                boolean adminExists = false;
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Account temp = postSnapshot.getValue(Account.class);
                    if (temp.getRole() == Role.USER) {
                        User account = postSnapshot.getValue(User.class);
                        accounts.add(account);
                    }
                        if (temp.getRole() == Role.SERVICEPROVIDER) {
                        ServiceProvider account = postSnapshot.getValue(ServiceProvider.class);
                        accounts.add(account);
                    }
                        if (temp.getRole() == Role.ADMIN) {
                        Admin account = postSnapshot.getValue(Admin.class);
                        adminExists = true;
                        accounts.add(account);
                    }

                }
                if (!adminExists) {
                    String id = databaseAccounts.push().getKey();
                    Admin account = new Admin("admin", "admin", Role.ADMIN);
                    databaseAccounts.child(id).setValue(account);
                }
                listCreate();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        }
        );
        databaseServices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                services.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Service temp = postSnapshot.getValue(Service.class);
                    services.add(temp);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }

    private void addService(String name, int rate) {
        Service test = new Service(name, rate);
        String id = databaseServices.push().getKey();
        databaseServices.child(id).setValue(test);
    }

    private void listCreate() {
        ArrayList<String> username= new ArrayList<>();
        username.clear();
        for(Account temp:accounts) {
            username.add(temp.getUsername()+"\n"+temp.getPassword()+"\n"+temp.getRole());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.simple_list_item_1, username);
        listView.setAdapter(arrayAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = (String)parent.getItemAtPosition(position);
                //Do something with the string that you justgot!
                Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT).show();
            }
        });
    }

}

