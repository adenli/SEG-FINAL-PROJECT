package ca.uottawa.uvaug070.homerepair;

import android.content.Intent;
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
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseReference databaseAccounts;
    List<Account> accounts;
    Button buttonCreateHomeOwner;
    Button buttonCreateServiceProvider;
    EditText username;
    EditText password;
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
                addUser();
            }
        });
        buttonCreateServiceProvider = (Button) findViewById(R.id.createServiceProvider);
        buttonCreateServiceProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addServiceProvider();
            }
        });


        username= (EditText)findViewById(R.id.editTextName);
        password= (EditText)findViewById(R.id.editTextPassword);
        Button b1 = (Button) findViewById(R.id.login);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login();

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
                    Account temp = postSnapshot.getValue(Account.class);
                    if(temp.getRole() == Role.USER) {
                        User account = postSnapshot.getValue(User.class);
                        accounts.add(account);
                    }
                    if(temp.getRole() == Role.SERVICEPROVIDER) {
                        ServiceProvider account = postSnapshot.getValue(ServiceProvider.class);
                        accounts.add(account);
                    }
                    if(temp.getRole() == Role.ADMIN) {
                        Admin account = postSnapshot.getValue(Admin.class);
                        adminExists = true;
                        accounts.add(account);
                    }
                }
                if(!adminExists) {
                    String id = databaseAccounts.push().getKey();
                    Admin account = new Admin("admin", "admin", Role.ADMIN);
                    databaseAccounts.child(id).setValue(account);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void addServiceProvider() {
        String username = ((EditText)findViewById(R.id.editTextName)).getText().toString().trim();
        String password = ((EditText)findViewById(R.id.editTextPassword)).getText().toString();
        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            String id = databaseAccounts.push().getKey();
            ServiceProvider account = new ServiceProvider(username, password, Role.SERVICEPROVIDER);
            databaseAccounts.child(id).setValue(account);
            ((EditText)findViewById(R.id.editTextName)).setText("");
            ((EditText)findViewById(R.id.editTextPassword)).setText("");
            Toast.makeText(this, "Account created", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Please enter a username and password", Toast.LENGTH_LONG).show();}


        Iterator<Account> iterator = accounts.iterator();
        boolean AccountCreated = false;
        while (iterator.hasNext()) {
            Account temp = iterator.next();

            if (username.equals(temp.getUsername())) {
                Toast.makeText(this, "An account under this username has already been created. Try another Username", Toast.LENGTH_LONG).show();

                AccountCreated = true;
            }
        }

        if (AccountCreated!=true){

            if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                String id = databaseAccounts.push().getKey();
                Account account = new Account(username, password, Role.SERVICEPROVIDER);
                databaseAccounts.child(id).setValue(account);
                ((EditText)findViewById(R.id.editTextName)).setText("");
                ((EditText)findViewById(R.id.editTextPassword)).setText("");
                Toast.makeText(this, "Account created", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Please enter a username and password", Toast.LENGTH_LONG).show();
            }
        }
    }
    private void addUser() {
        String username = ((EditText)findViewById(R.id.editTextName)).getText().toString().trim();
        String password = ((EditText)findViewById(R.id.editTextPassword)).getText().toString();
        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            String id = databaseAccounts.push().getKey();
            User account = new User(username, password, Role.USER);
            databaseAccounts.child(id).setValue(account);
            ((EditText)findViewById(R.id.editTextName)).setText("");
            ((EditText)findViewById(R.id.editTextPassword)).setText("");
            Toast.makeText(this, "Account created", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Please enter a username and password", Toast.LENGTH_LONG).show();}


            Iterator<Account> iterator = accounts.iterator();
            boolean AccountCreated = false;
            while (iterator.hasNext()) {
                Account temp = iterator.next();

                if (username.equals(temp.getUsername())) {
                    Toast.makeText(this, "An account under this username has already been created. Try another Username", Toast.LENGTH_LONG).show();

                    AccountCreated = true;
                }
            }

        if (AccountCreated!=true){

            if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                String id = databaseAccounts.push().getKey();
                Account account = new Account(username, password, Role.USER);
                databaseAccounts.child(id).setValue(account);
                ((EditText)findViewById(R.id.editTextName)).setText("");
                ((EditText)findViewById(R.id.editTextPassword)).setText("");
                Toast.makeText(this, "Account created", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Please enter a username and password", Toast.LENGTH_LONG).show();
            }


        }

    }
    private void login(){
        String value = "A";

        Iterator<Account> iterator = accounts.iterator();

        while (iterator.hasNext()) {
            Account temp= iterator.next();
            //Toast.makeText(getApplicationContext(), temp.getUsername(), Toast.LENGTH_SHORT).show();

            if (((username.getText().toString().equals(temp.getUsername())))&& (password.getText().toString().equals(temp.getPassword()))) {
                value = "Success";
                Intent intent = new Intent(getApplicationContext(),WelcomeActivity.class);
                Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();
                intent.putExtra("username",temp.getUsername());
                String role;
                if (temp instanceof Admin){
                    role="Admin";
                }else if(temp instanceof User){
                    role="HomeOwner";
                }else{
                    role="ServiceProvider";
                }
                intent.putExtra("role",role);
                startActivity(intent);

            }
            if (!iterator.hasNext()){
                value="DNE";
            }
        }


        if (value=="A") {
            Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
            // add the welcome script
        }
        if (value=="DNE"){
            Toast.makeText(getApplicationContext(), "An account does not exist", Toast.LENGTH_SHORT).show();

        }
    }
}
