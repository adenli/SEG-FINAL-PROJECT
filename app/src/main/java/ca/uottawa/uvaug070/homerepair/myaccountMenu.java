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
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Belal on 18/09/16.
 */


public class myaccountMenu extends Fragment {

    DatabaseReference databaseServices;
    DatabaseReference databaseServPro;
    ListView servaddview;
    List<Service> services;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View view=inflater.inflate(R.layout.fragment_serviceproviderinfo, container, false);
        super.onCreate(savedInstanceState);

        final EditText name = (EditText) view.findViewById(R.id.name);
        final EditText address = (EditText) view.findViewById(R.id.address);
        EditText description = (EditText) view.findViewById(R.id.description);
        final EditText phone = (EditText) view.findViewById(R.id.phone);
        Spinner spin = (Spinner) view.findViewById(R.id.select);
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
                }
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