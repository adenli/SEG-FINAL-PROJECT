package ca.uottawa.uvaug070.homerepair;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

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
        View view=inflater.inflate(R.layout.fragment_serviceproviderinfo, container, false);
        databaseServPro = FirebaseDatabase.getInstance().getReference("accounts");
        String nameText="";
        String addressText="";
        String phoneText="";
        String descriptionText="";
        uid=savedInstanceState.getString("uid");
        try{
            nameText=databaseServPro.child(uid).child("profile").child("companyName").toString();
            addressText=databaseServPro.child(uid).child("profile").child("address").toString();
            phoneText=databaseServPro.child(uid).child("profile").child("phoneNumber").toString();
            descriptionText=databaseServPro.child(uid).child("profile").child("description").toString();
            EditText name = (EditText) view.findViewById(R.id.name);
            name.setText(nameText);
            EditText address = (EditText) view.findViewById(R.id.address);
            address.setText(addressText);
            EditText description = (EditText) view.findViewById(R.id.description);
            description.setText(descriptionText);
            EditText phone = (EditText) view.findViewById(R.id.phone);
            phone.setText(phoneText);
            Spinner spin = (Spinner) view.findViewById(R.id.select);
            String[] Spinnerlist={"YES","NO"};
            ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,Spinnerlist);
            spin.setAdapter(arrayAdapter);
        }
        catch(Exception e){
            EditText name = (EditText) view.findViewById(R.id.name);
            name.setText("insert serviceprovider company name here");
            EditText address = (EditText) view.findViewById(R.id.address);
            address.setText("insert serviceprovider address here");
            EditText description = (EditText) view.findViewById(R.id.description);
            description.setText("insert serviceprovider description here");
            EditText phone = (EditText) view.findViewById(R.id.phone);
            phone.setText("insert serviceprovider phone number here");
            Spinner spin = (Spinner) view.findViewById(R.id.select);
            String[] Spinnerlist={"YES","NO"};
            ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,Spinnerlist);
            spin.setAdapter(arrayAdapter);
        }

        //databaseServPro.child(uid);
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments


        Button confirmButton = (Button) view.findViewById(R.id.confirm);


        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmInfo(view);
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
    public void confirmInfo(View view) {
        EditText name = (EditText) view.findViewById(R.id.name);
        EditText address = (EditText) view.findViewById(R.id.address);
        EditText description = (EditText) view.findViewById(R.id.description);
        EditText phone = (EditText) view.findViewById(R.id.phone);
        Spinner spin = (Spinner) view.findViewById(R.id.select);
        String[] spinnerList={"YES","NO"};
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,spinnerList);
        spin.setAdapter(arrayAdapter);
        Profile myProfile;
        if(spin.toString()=="YES"){
            myProfile= new Profile(name.toString(),address.toString(),phone.toString(),description.toString(),true);
        }
        else {
            myProfile= new Profile(name.toString(),address.toString(),phone.toString(),description.toString(),false);
        }
        databaseServPro = FirebaseDatabase.getInstance().getReference("accounts");
        databaseServPro.child(uid).child("profile").setValue(myProfile);
        return;

    }
}
