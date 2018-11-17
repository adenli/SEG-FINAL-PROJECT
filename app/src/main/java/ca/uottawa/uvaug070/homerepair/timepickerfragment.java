package ca.uottawa.uvaug070.homerepair;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class timepickerfragment extends DialogFragment {

    public timepickerfragment()
    {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getDialog().dismiss();
        return inflater.inflate(R.layout.timepicker_fragment, container);
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LinearLayout main = new LinearLayout(getActivity());
        main.setOrientation(LinearLayout.VERTICAL);


        return (new AlertDialog.Builder(getActivity()).create());
    }


}