package ca.uottawa.uvaug070.homerepair;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseReference databaseAccounts;
    List<Account> accounts;
    Button buttonCreateHomeOwner;
    Button buttonCreateServiceProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accounts = new ArrayList<>();
        databaseAccounts = FirebaseDatabase.getInstance().getReference("accounts");
        buttonCreateHomeOwner = (Button) findViewById(R.id.createAccount);
        buttonCreateHomeOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAccount(Role.HOMEOWNER);
            }
        });
        buttonCreateServiceProvider = (Button) findViewById(R.id.createServiceProvider);
        buttonCreateServiceProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAccount(Role.SERVICEPROVIDER);
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
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Account account = postSnapshot.getValue(Account.class);
                    if(account.getRole() == Role.ADMIN) {
                        adminExists = true;
                    }
                    accounts.add(account);
                }
                if(!adminExists) {
                    String id = databaseAccounts.push().getKey();
                    Account account = new Account("admin", "admin", Role.ADMIN);
                    databaseAccounts.child(id).setValue(account);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void addAccount(Role role) {
        String username = ((EditText)findViewById(R.id.editTextName)).getText().toString().trim();
        String password = ((EditText)findViewById(R.id.editTextPassword)).getText().toString();
        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            String id = databaseAccounts.push().getKey();
            Account account = new Account(username, password, role);
            databaseAccounts.child(id).setValue(account);
            ((EditText)findViewById(R.id.editTextName)).setText("");
            ((EditText)findViewById(R.id.editTextPassword)).setText("");
            Toast.makeText(this, "Account created", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Please enter a username and password", Toast.LENGTH_LONG).show();
        }
    }
}
