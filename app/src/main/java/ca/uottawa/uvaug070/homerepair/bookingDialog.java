package ca.uottawa.uvaug070.homerepair;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

import java.util.ArrayList;

public class bookingDialog extends DialogFragment {
    ArrayList<String> availability;
    String day;
    String status;
    ArrayList<String> services = new ArrayList<>();
    ArrayList<String> spinnerArray = new ArrayList<String>();
    DataSnapshot postSnapshot;
    ArrayList<String> times= new ArrayList<>();
    int cumulativeRating;
    int numRatings;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dialog_box2, container, false);
        EditText textinput = view.findViewById(R.id.txtinput);
        EditText textinput2 = view.findViewById(R.id.txtinput2);


        Spinner spin = (Spinner) view.findViewById(R.id.rating);
        spinnerArray.add("1");
        spinnerArray.add("2");
        spinnerArray.add("3");
        spinnerArray.add("4");
        spinnerArray.add("5");

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String> (getActivity(), android.R.layout.simple_spinner_item, spinnerArray);

        spin.setAdapter(spinnerArrayAdapter);
        String rating = spin.getSelectedItem().toString();
        final Button button = view.findViewById(R.id.btdone);


        button.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Spinner spin = (Spinner) view.findViewById(R.id.rating);
                String rating = spin.getSelectedItem().toString();
                getDialog().dismiss();
                Bundle extras = getArguments();
                final String extra = extras.getString("user");
                final DatabaseReference user = FirebaseDatabase.getInstance().getReference("accounts").child(extra);
                cumulativeRating += Integer.parseInt(rating);
                numRatings += 1;
                user.child("cumulativeRating").setValue(cumulativeRating);
                user.child("numRatings").setValue(numRatings);
            }
        });

        return view;
    }

    public void onStart(){
        super.onStart();

        Bundle extras = getArguments();
        final String extra = extras.getString("user");
        final DatabaseReference database = FirebaseDatabase.getInstance().getReference("accounts").child(extra).child("services");
        final DatabaseReference user = FirebaseDatabase.getInstance().getReference("accounts").child(extra);
        user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cumulativeRating = dataSnapshot.child("cumulativeRating").getValue(Integer.class);
                numRatings = dataSnapshot.child("numRatings").getValue(Integer.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Service temp = snapshot.getValue(Service.class);
                    services.add(temp.toString());
                }
                ListView listView = getView().findViewById(R.id.lv);
                ArrayAdapter servicesAdapter = new ArrayAdapter(getActivity().getApplicationContext(), R.layout.simple_list_item_1, services);
                listView.setAdapter(servicesAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                        final String item = (String) parent.getItemAtPosition(position);
                        //Do something with the string that you justgot!
                        Toast.makeText(getActivity().getApplicationContext(), item+" selected!", Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        final DatabaseReference database2 = FirebaseDatabase.getInstance().getReference("times").child(extra);
        database2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Object temp = snapshot.getValue();
                    times.add(temp.toString());
                }
                ListView listView2 = getView().findViewById(R.id.timeview);
                ArrayAdapter servicesAdapter2 = new ArrayAdapter(getActivity().getApplicationContext(), R.layout.simple_list_item_1, times);
                listView2.setAdapter(servicesAdapter2);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






    }
}


