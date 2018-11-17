package ca.uottawa.uvaug070.homerepair;


import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.Calendar;

public class availabilityMenu extends Fragment {


   private Boolean switchState;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View view = inflater.inflate(R.layout.fragment_availability, container, false);
        super.onCreate(savedInstanceState);

        return view;
        //this will initialize the layout of the activity servproaddserv
        // you must add the functionality of the servproaddserv here ex: pulling list from firebase etc
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Calendar");


        Switch simpleSwitch = (Switch) getActivity().findViewById(R.id.monday);
        Switch simpleSwitch1 = (Switch) getActivity().findViewById(R.id.tuesday);
        Switch simpleSwitch2 = (Switch) getActivity().findViewById(R.id.wednesday);
        Switch simpleSwitch3 = (Switch) getActivity().findViewById(R.id.thursday);
        Switch simpleSwitch4 = (Switch) getActivity().findViewById(R.id.friday);
        Switch simpleSwitch5 = (Switch) getActivity().findViewById(R.id.saturday);
        Switch simpleSwitch6 = (Switch) getActivity().findViewById(R.id.sunday);

//        final Boolean switchState = simpleSwitch.isChecked(); //0 being monday and 6 being sunday
//        Boolean switchState1 = simpleSwitch1.isChecked();
//        Boolean switchState2 = simpleSwitch2.isChecked();
//        Boolean switchState3 = simpleSwitch3.isChecked();
//        Boolean switchState4 = simpleSwitch4.isChecked();
//        Boolean switchState5 = simpleSwitch5.isChecked();
//        Boolean switchState6 = simpleSwitch6.isChecked();
//        simpleSwitch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (switchState==false){
//
//                    Toast.makeText(getActivity().getApplicationContext(), "niggers", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });

        simpleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean on){
                if(on)
                {
                    Calendar mcurrentTime = Calendar.getInstance();
                    int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                    int minute = mcurrentTime.get(Calendar.MINUTE);
                    TimePickerDialog mTimePicker = null;



                    DialogFragment dialog = (DialogFragment) DialogFragment.instantiate(getActivity(), "timepickerfragment");
                    dialog.show(getActivity().getSupportFragmentManager(), "dialog");
//
//                    timepickerfragment dialog = new timepickerfragment();
//                    dialog.show(getChildFragmentManager(), "timepicker_fragment");
                }
                else
                {
                    //Do something when Switch is off/unchecked

                }
            }
        });

    }



}