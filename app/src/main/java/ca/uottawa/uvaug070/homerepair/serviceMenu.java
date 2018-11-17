package ca.uottawa.uvaug070.homerepair;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class serviceMenu extends Fragment {

    DatabaseReference databaseServices;
    DatabaseReference databaseServPro;
    ListView servaddview;
    List<Service> services;
    private String username;
    ListView myservaddview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments

        View view=inflater.inflate(R.layout.activity_servproaddserv, container, false);
        super.onCreate(savedInstanceState);
        servaddview = (ListView) view.findViewById(R.id.servaddview);
        myservaddview = (ListView) view.findViewById(R.id.myservaddview);
        services = new ArrayList<>();

        databaseServices = FirebaseDatabase.getInstance().getReference("services");

        setHasOptionsMenu(true);
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

    public void onStart() {
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

        ArrayList<String> service = new ArrayList<>();
        service.clear();
        DecimalFormat df2 = new DecimalFormat(".##");
        for (Service temp1 : services) {
            service.add(temp1.getName() + "\n" + "$" + df2.format(temp1.getRate()) + "/hour");
        }

        ArrayAdapter arrayAdapter2 = new ArrayAdapter(getActivity().getApplicationContext(), R.layout.simple_list_item_1, service);
        servaddview.setAdapter(arrayAdapter2);

        //change when db updates

        myservaddview.setAdapter(arrayAdapter2);

        registerForContextMenu(servaddview);
        registerForContextMenu(myservaddview);
        servaddview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                //Do something with the string that you justgot!
            }
        });

        servaddview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        myservaddview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                //Do something with the string that you justgot!
            }
        });

        myservaddview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

    }


    @SuppressLint("ResourceType")
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v.getId() == R.id.servaddview) {
            MenuInflater inflater = getActivity().getMenuInflater();
            inflater.inflate(R.menu.service_menu, menu);
        }

        if (v.getId() == R.id.myservaddview) {
            MenuInflater inflater = getActivity().getMenuInflater();
            inflater.inflate(R.menu.myservice_menu, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()){

            case R.id.add:
                Toast.makeText(getActivity().getApplicationContext(), "yo fam that shit got added ayo", Toast.LENGTH_SHORT).show();

                break;

            case R.id.remove:
                Toast.makeText(getActivity().getApplicationContext(), "deleted", Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onContextItemSelected(item);

        }
        createList();
        return true;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}