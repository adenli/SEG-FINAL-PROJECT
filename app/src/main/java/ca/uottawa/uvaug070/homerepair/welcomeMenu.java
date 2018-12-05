package ca.uottawa.uvaug070.homerepair;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Belal on 18/09/16.
 */


public class welcomeMenu extends Fragment {

    DatabaseReference databaseServices;
    DatabaseReference databaseServPro;
    ListView servaddview;
    List<Service> services;
    TextView tv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View view=inflater.inflate(R.layout.activity_welcome_menu, container, false);
        super.onCreate(savedInstanceState);
        String a = getCurrentTime(view);
        Intent intent= getActivity().getIntent();
        tv=(TextView) view.findViewById(R.id.role_message); //add set text
        tv.setText("Welcome " + intent.getStringExtra("username") + ", " + intent.getStringExtra("role"));
        return view;
        //this will initialize the layout of the activity servproaddserv
        // you must add the functionality of the servproaddserv here ex: pulling list from firebase etc
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Welcome!");
    }

    public String getCurrentTime(View view) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
        String strDate = "Current Time : " + mdformat.format(calendar.getTime());

        return strDate;
    }
}