package ca.uottawa.uvaug070.homerepair;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

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

        EditText name = (EditText) view.findViewById(R.id.name);
        EditText address = (EditText) view.findViewById(R.id.address);
        EditText description = (EditText) view.findViewById(R.id.description);
        EditText phone = (EditText) view.findViewById(R.id.phone);








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