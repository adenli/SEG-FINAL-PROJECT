package ca.uottawa.uvaug070.homerepair;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.Calendar;

public class availabilityMenu extends Fragment {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View view = inflater.inflate(R.layout.fragment_availability, container, false);
        super.onCreate(savedInstanceState);
        Spinner spin = (Spinner) view.findViewById(R.id.spinner);
        Spinner spin1= view.findViewById(R.id.spinner1);
        Spinner spin2= view.findViewById(R.id.spinner2);
        Spinner spin3= view.findViewById(R.id.spinner3);
        Spinner spin4= view.findViewById(R.id.spinner4);
        Spinner spin5= view.findViewById(R.id.spinner5);
        Spinner spin6= view.findViewById(R.id.spinner6);
//        String[] Spinnerlist={"Opening time","Closing time"};

//        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,Spinnerlist);
//        spin.setAdapter(arrayAdapter);
        spin.setEnabled(false);
        spin1.setEnabled(false);
        spin2.setEnabled(false);
        spin3.setEnabled(false);
        spin4.setEnabled(false);
        spin5.setEnabled(false);
        spin6.setEnabled(false);

        final EditText ass = (EditText) getActivity().findViewById(R.id.ass);

        return view;
        //this will initialize the layout of the activity servproaddserv
        // you must add the functionality of the servproaddserv here ex: pulling list from firebase etc
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Calendar");

    }

    @Override
    public void onStart() {

        super.onStart();
        Spinner spin = (Spinner) getActivity().findViewById(R.id.spinner);
        Switch simpleSwitch = (Switch) getActivity().findViewById(R.id.monday);

        simpleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            Spinner spin = (Spinner) getActivity().findViewById(R.id.spinner);

            final EditText ass = (EditText) getActivity().findViewById(R.id.ass);
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean on){
                if(on) {

                    spin.setEnabled(true);
                    // Get Current Time
                    final Calendar c = Calendar.getInstance();
                    int mHour = c.get(Calendar.HOUR_OF_DAY);
                    int mMinute = c.get(Calendar.MINUTE);

                    spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Object value = parent.getItemAtPosition(position);
                            switch (position) {
                                case 0:
                                    DialogFragment newFragment = new timepickerfragment();
                                    newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
                                    break;
                                case 1:
                                    newFragment = new timepickerfragment();
                                    newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
                                    break;
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            
                        }

                });
                }

                else{
                    spin.setEnabled(false);
                }
            }
        });
        Spinner spin1 = (Spinner) getActivity().findViewById(R.id.spinner1);
        Switch simpleSwitch1 = (Switch) getActivity().findViewById(R.id.tuesday);
        simpleSwitch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            Spinner spin1 = (Spinner) getActivity().findViewById(R.id.spinner1);

            final EditText ass = (EditText) getActivity().findViewById(R.id.ass);
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean on){
                if(on) {

                    spin1.setEnabled(true);
                    // Get Current Time
                    final Calendar c = Calendar.getInstance();
                    int mHour = c.get(Calendar.HOUR_OF_DAY);
                    int mMinute = c.get(Calendar.MINUTE);

                    spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Object value = parent.getItemAtPosition(position);
                            switch (position) {
                                case 0:
                                    DialogFragment newFragment = new timepickerfragment();
                                    newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
                                    break;
                                case 1:
                                    newFragment = new timepickerfragment();
                                    newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
                                    break;
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }

                    });
                }

                else{
                    spin1.setEnabled(false);
                }
            }
        });

        Spinner spin2 = (Spinner) getActivity().findViewById(R.id.spinner2);
        Switch simpleSwitch2 = (Switch) getActivity().findViewById(R.id.wednesday);
        simpleSwitch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            Spinner spin2 = (Spinner) getActivity().findViewById(R.id.spinner2);

            final EditText ass = (EditText) getActivity().findViewById(R.id.ass);
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean on){
                if(on) {

                    spin2.setEnabled(true);
                    // Get Current Time
                    final Calendar c = Calendar.getInstance();
                    int mHour = c.get(Calendar.HOUR_OF_DAY);
                    int mMinute = c.get(Calendar.MINUTE);

                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Object value = parent.getItemAtPosition(position);
                            switch (position) {
                                case 0:
                                    DialogFragment newFragment = new timepickerfragment();
                                    newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
                                    break;
                                case 1:
                                    newFragment = new timepickerfragment();
                                    newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
                                    break;
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }

                    });
                }

                else{
                    spin2.setEnabled(false);
                }
            }
        });
        Spinner spin3 = (Spinner) getActivity().findViewById(R.id.spinner3);
        Switch simpleSwitch3 = (Switch) getActivity().findViewById(R.id.thursday);
        simpleSwitch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            Spinner spin3 = (Spinner) getActivity().findViewById(R.id.spinner3);

            final EditText ass = (EditText) getActivity().findViewById(R.id.ass);
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean on){
                if(on) {

                    spin3.setEnabled(true);
                    // Get Current Time
                    final Calendar c = Calendar.getInstance();
                    int mHour = c.get(Calendar.HOUR_OF_DAY);
                    int mMinute = c.get(Calendar.MINUTE);

                    spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Object value = parent.getItemAtPosition(position);
                            switch (position) {
                                case 0:
                                    DialogFragment newFragment = new timepickerfragment();
                                    newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
                                    break;
                                case 1:
                                    newFragment = new timepickerfragment();
                                    newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
                                    break;
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }

                    });
                }

                else{
                    spin3.setEnabled(false);
                }
            }
        });

        Spinner spin4 = (Spinner) getActivity().findViewById(R.id.spinner4);
        Switch simpleSwitch4 = (Switch) getActivity().findViewById(R.id.friday);
        simpleSwitch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            Spinner spin4 = (Spinner) getActivity().findViewById(R.id.spinner4);

            final EditText ass = (EditText) getActivity().findViewById(R.id.ass);
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean on){
                if(on) {

                    spin4.setEnabled(true);
                    // Get Current Time
                    final Calendar c = Calendar.getInstance();
                    int mHour = c.get(Calendar.HOUR_OF_DAY);
                    int mMinute = c.get(Calendar.MINUTE);

                    spin4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Object value = parent.getItemAtPosition(position);
                            switch (position) {
                                case 0:
                                    DialogFragment newFragment = new timepickerfragment();
                                    newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
                                    break;
                                case 1:
                                    newFragment = new timepickerfragment();
                                    newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
                                    break;
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }

                    });
                }

                else{
                    spin4.setEnabled(false);
                }
            }
        });

        Spinner spin5 = (Spinner) getActivity().findViewById(R.id.spinner5);
        Switch simpleSwitch5 = (Switch) getActivity().findViewById(R.id.saturday);
        simpleSwitch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            Spinner spin5 = (Spinner) getActivity().findViewById(R.id.spinner5);

            final EditText ass = (EditText) getActivity().findViewById(R.id.ass);
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean on){
                if(on) {

                    spin5.setEnabled(true);
                    // Get Current Time
                    final Calendar c = Calendar.getInstance();
                    int mHour = c.get(Calendar.HOUR_OF_DAY);
                    int mMinute = c.get(Calendar.MINUTE);

                    spin5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Object value = parent.getItemAtPosition(position);
                            switch (position) {
                                case 0:
                                    DialogFragment newFragment = new timepickerfragment();
                                    newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
                                    break;
                                case 1:
                                    newFragment = new timepickerfragment();
                                    newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
                                    break;
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }

                    });
                }

                else{
                    spin5.setEnabled(false);
                }
            }
        });


        Spinner spin6 = (Spinner) getActivity().findViewById(R.id.spinner6);
        Switch simpleSwitch6 = (Switch) getActivity().findViewById(R.id.sunday);
        simpleSwitch6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            Spinner spin6 = (Spinner) getActivity().findViewById(R.id.spinner6);

            final EditText ass = (EditText) getActivity().findViewById(R.id.ass);
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean on){
                if(on) {

                    spin6.setEnabled(true);
                    // Get Current Time
                    final Calendar c = Calendar.getInstance();
                    int mHour = c.get(Calendar.HOUR_OF_DAY);
                    int mMinute = c.get(Calendar.MINUTE);

                    spin6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Object value = parent.getItemAtPosition(position);
                            switch (position) {
                                case 0:
                                    DialogFragment newFragment = new timepickerfragment();
                                    newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
                                    break;
                                case 1:
                                    newFragment = new timepickerfragment();
                                    newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
                                    break;
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }

                    });
                }

                else{
                    spin6.setEnabled(false);
                }
            }
        });
    }
}