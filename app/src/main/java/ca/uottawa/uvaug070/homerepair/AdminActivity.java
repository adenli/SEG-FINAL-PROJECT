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
    DatabaseReference databaseAccounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        accounts = new ArrayList<>();
        listView = findViewById(R.id.listview);
        databaseAccounts = FirebaseDatabase.getInstance().getReference("accounts");

        ((TextView) findViewById(R.id.welcome_text)).setText("Welcome admin!");
        ListCreate();
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
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        }
        );
    }


    private void ListCreate() {
        ArrayList<String> username= new ArrayList<>();
        for(Account temp:accounts) {
            username.add(temp.getUsername().toString()+" "+temp.getPassword().toString());
        }
        username.add("a"+"\n"+"a");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.simple_list_item_1, username);
        listView.setAdapter(arrayAdapter);
    }
}

