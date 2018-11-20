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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Belal on 18/09/16.
 */


public class myaccountMenu extends Fragment {

    DatabaseReference databaseServices;
    DatabaseReference databaseServPro;
    ListView servaddview;
    List<Service> services;
    String uid="";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view=inflater.inflate(R.layout.fragment_serviceproviderinfo, container, false);
        databaseServPro = FirebaseDatabase.getInstance().getReference("accounts");
        Bundle myBundle=getArguments();
        //getting uid to get proper database reference
        uid=myBundle.getString("uid");

        //gets all valueus from edittext
        EditText name=(EditText) view.findViewById(R.id.name);
        EditText address=(EditText) view.findViewById(R.id.address);
        EditText phone=(EditText) view.findViewById(R.id.phone);
        EditText descr=(EditText) view.findViewById(R.id.description);
        Spinner spin = (Spinner) view.findViewById(R.id.select);
        String[] Spinnerlist={"YES","NO"};
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,Spinnerlist);
        spin.setAdapter(arrayAdapter);
        Boolean spinnerThing=true;
        if (spin.toString()=="YES"){
            spinnerThing=true;
        }
        //right now when i press the button it assigns all the values to "" (blank string not null)
        //need to change so that it will update to the actual values
        final Profile myProfile= new Profile(name.getText().toString(),address.getText().toString(),phone.getText().toString(),descr.getText().toString(),spinnerThing);

        Button confirmButton = (Button) view.findViewById(R.id.confirm);


        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseServPro.child(uid).child("Profile").setValue(myProfile);
            }
        });
        return view;

        //this will initialize the layout of the activity servproaddserv
        // you must add the functionality of the servproaddserv here ex: pulling list from firebase etc
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Edit information");


    }
}
