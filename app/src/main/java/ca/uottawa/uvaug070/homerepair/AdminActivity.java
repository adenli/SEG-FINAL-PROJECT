package ca.uottawa.uvaug070.homerepair;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import static android.widget.AdapterView.OnClickListener;

public class AdminActivity extends AppCompatActivity {
    ListView listView;
    List<Account> accounts;
    DatabaseReference databaseAccounts;
    ListView serviceview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        accounts = new ArrayList<>();

        listView = findViewById(R.id.listview);
        databaseAccounts = FirebaseDatabase.getInstance().getReference("accounts");

        ((TextView) findViewById(R.id.welcome_text)).setText("Welcome admin!");

        final Button button= findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ServiceActivity.class);
                startActivity(intent);
            }
        });


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
                    Admin account = new Admin("admin", "admin", Role.ADMIN, id);
                    databaseAccounts.child(id).setValue(account);
                }
                listCreate();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        }
        );
    }


    private void listCreate() {
        ArrayList<String> username= new ArrayList<>();
        username.clear();
        String role = "";
        for(Account temp:accounts) {
            switch(temp.getRole()) {
                case USER:
                    role = "Homeowner";
                    break;
                case ADMIN:
                    role = "Admin";
                    break;
                case SERVICEPROVIDER:
                    role = "Service Provider";
                    break;
            }
            username.add("Username: " + temp.getUsername()+"\n"+"Password: " + temp.getPassword()+"\n"+"Role: " + role);
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

