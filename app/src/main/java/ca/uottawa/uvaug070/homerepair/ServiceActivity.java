package ca.uottawa.uvaug070.homerepair;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
        final EditText servicename=(EditText)findViewById(R.id.servicename);
        final EditText rateamount=(EditText)findViewById(R.id.rateamount);
        View addservice = findViewById(R.id.addservice);

        addservice.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                if(servicename.getText().equals("")) {
                    Toast.makeText(getApplicationContext(), "No name specified", Toast.LENGTH_SHORT).show();
                } else if (rateamount.getText().equals("")) {
                    Toast.makeText(getApplicationContext(), "No rate specified", Toast.LENGTH_SHORT).show();
                } else {
                    addService((servicename.getText().toString()),Integer.parseInt(rateamount.getText().toString()));
                }
            }
        });




    }


    private void addService(String name, int rate) {
        if(name.equals(null)) {
            Toast.makeText(getApplicationContext(), "No name specified", Toast.LENGTH_SHORT).show();
        } else if (rate < 0) {
            Toast.makeText(getApplicationContext(), "Invalid rate", Toast.LENGTH_SHORT).show();
        } else {
            Service test = new Service(name, rate);
            String id = databaseServices.push().getKey();
            databaseServices.child(id).setValue(test);
        }
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

    @SuppressLint("ClickableViewAccessibility")
    private void createList() {

        ArrayList<String> service=new ArrayList<>();
        service.clear();
        for (Service temp1:services){
            service.add(temp1.getName()+"\n"+temp1.getRate());
        }

        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this, R.layout.simple_list_item_1, service);
        serviceview.setAdapter(arrayAdapter2);

        registerForContextMenu(serviceview);
        serviceview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = (String)parent.getItemAtPosition(position);
                //Do something with the string that you justgot!
                Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT).show();
            }
        });

        serviceview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });


    }
    @SuppressLint("ResourceType")
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);

        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.layout.main_context_menu,menu);
    }
    public void showInputBox(final int position){
        final Dialog dialog=new Dialog(ServiceActivity.this);
        dialog.setTitle("Input Box");
        dialog.setContentView(R.layout.dialog_box);
        TextView txtMessage=(TextView)dialog.findViewById(R.id.txtmessage);
        txtMessage.setText("Update item");

        final EditText editText=(EditText)dialog.findViewById(R.id.txtinput);
        final EditText editText2=(EditText)dialog.findViewById(R.id.txtinput2);

        Button bt=(Button)dialog.findViewById(R.id.btdone);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addService(editText.getText().toString(),Integer.parseInt(editText2.getText().toString()));
                createList();

                services.remove(position);
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()){

            case R.id.edit_id:
                showInputBox(info.position);

            case R.id.delete_id:
                services.remove(info.position);


                createList();

                return true;

                default:
                    return super.onContextItemSelected(item);
        }
    }

}







