package ca.uottawa.uvaug070.homerepair;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

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
    DataSnapshot postSnapshot;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dialog_box2, container, false);
        EditText textinput = view.findViewById(R.id.txtinput);
        EditText textinput2 = view.findViewById(R.id.txtinput2);

        final Button button = view.findViewById(R.id.btdone);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        return view;
    }


    public void onStart(){
        super.onStart();

        Bundle extras = getArguments();
        final String extra = extras.getString("user");
        final DatabaseReference database = FirebaseDatabase.getInstance().getReference("accounts").child(extra).child("services");


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Service temp = snapshot.getValue(Service.class);
                    services.add(temp.toString());
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        ListView listView = getView().findViewById(R.id.lv);
        ArrayAdapter servicesAdapter = new ArrayAdapter(getActivity().getApplicationContext(), R.layout.simple_list_item_1, services);
        listView.setAdapter(servicesAdapter);


    }
}


