package com.example.win.appbarcode20180828;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class main_goods_receipt_Fragment extends DialogFragment{


    public main_goods_receipt_Fragment() {
        // Required empty public constructor
    }

    private Button btnscanner;
    private Spinner branch , batch;
    private ArrayList<String> Branch = new ArrayList<String>();
    private ArrayList<String> Batch = new ArrayList<String>();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(),
                (DatePickerDialog.OnDateSetListener)
        getActivity(),year , month , day);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_goods_receipt_, container, false);

        branch = (Spinner) view.findViewById(R.id.spinner_branch);
        batch = (Spinner) view.findViewById(R.id.spinner_batch);
        btnscanner = (Button) view.findViewById(R.id.btn_startscan);

        //createDataset
        createBranch();
        createBatch();
        //arrayAdapter
        ArrayAdapter<String> adapterBranch = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_dropdown_item_1line,Branch);
        branch.setAdapter(adapterBranch);

        ArrayAdapter<String> adapterBatch = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_dropdown_item_1line,Batch);
        batch.setAdapter(adapterBatch);

        //eventonclick
        btnscanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(getActivity(),scangoods_receipt.class);
                startActivity(start);
            }
        });




        return view;
    }


    private void createBatch() {
        Batch.add("Batch 1");
        Batch.add("Batch 2");
        Batch.add("Batch 3");
        Batch.add("Batch 4");
        Batch.add("Batch 5");
    }

    private void createBranch() {
       Branch.add("Branch 1");
       Branch.add("Branch 2");
       Branch.add("Branch 3");
       Branch.add("Branch 4");
       Branch.add("Branch 5");
   }

}
