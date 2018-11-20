package ca.uottawa.uvaug070.homerepair;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;

/**
 * Created by Belal on 18/09/16.
 */


public class myaccountMenu extends Fragment {
    DatabaseReference databaseServPro;
    ListView servaddview;
    List<Service> services;
    String uid;
    EditText name;
    EditText address;
    EditText phone;
    EditText descr;
    Spinner spin;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view=inflater.inflate(R.layout.fragment_serviceproviderinfo, container, false);
        Bundle myBundle=getArguments();
        //getting uid to get proper database reference
        uid=myBundle.getString("uid");
        databaseServPro = FirebaseDatabase.getInstance().getReference("accounts").child(uid);
        //gets all valueus from edittext
        name=(EditText) view.findViewById(R.id.name);
        address=(EditText) view.findViewById(R.id.address);
        phone=(EditText) view.findViewById(R.id.phone);
        descr=(EditText) view.findViewById(R.id.description);
        spin = (Spinner) view.findViewById(R.id.select);
        String[] Spinnerlist={"YES","NO"};
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,Spinnerlist);
        spin.setAdapter(arrayAdapter);



        Button confirm = (Button) view.findViewById(R.id.confirm);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean addressHasNum = Pattern.compile( "[0-9]" ).matcher(address.getText().toString()).find();
                boolean addressHasAlp = Pattern.compile( "[a-zA-z]" ).matcher(address.getText().toString()).find();

                if (name.getText().toString().equals("") || address.getText().toString().equals("") ||
                        phone.getText().toString().equals("")) {
                    Toast.makeText(getActivity().getApplicationContext(), "Form not complete", Toast.LENGTH_SHORT).show();
                } else if ((!(Pattern.matches("[0-9]+", phone.getText().toString()))) || phone.getText().toString().length() < 10) {
                    Toast.makeText(getActivity().getApplicationContext(), "Phone number invalid", Toast.LENGTH_SHORT).show();
                } else if (!(addressHasNum && addressHasAlp)) {
                    Toast.makeText(getActivity().getApplicationContext(), "Address invalid", Toast.LENGTH_SHORT).show();
                } else {
                    //update or add to database
                    confirm();
                }
            }
        });

        return view;

        //this will initialize the layout of the activity servproaddserv
        // you must add the functionality of the servproaddserv here ex: pulling list from firebase etc
    }

    private void confirm() {
        boolean spinnerThing=false;
        if (spin.getSelectedItem().toString().equals("YES")){
            spinnerThing=true;
        }
        Profile myProfile= new Profile(name.getText().toString(),address.getText().toString(),phone.getText().toString(),descr.getText().toString(),spinnerThing);
        databaseServPro.child("Profile").setValue(myProfile);
        Toast.makeText(getActivity().getApplicationContext(), "Profile updated!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Edit information");


    }
    @Override
    public void onStart() {
        super.onStart();
        databaseServPro.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Profile updated = dataSnapshot.child("Profile").getValue(Profile.class);
                if(updated != null) {
                    name.setText(updated.getCompanyName());
                    address.setText(updated.getAddress());
                    descr.setText(updated.getDescription());
                    phone.setText(updated.getPhoneNumber());
                    if(updated.getLicensed()) {
                        spin.setSelection(0, true);
                    } else {
                        spin.setSelection(1, true);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
