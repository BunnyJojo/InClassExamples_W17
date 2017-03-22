package com.example.etorunski.inclassexamples_w17;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by etorunski on 2017-03-15.
 */

public class ListDetailsFragment extends Fragment {
    Context parent;
Long id;

    //no matter how you got here, the data is in the getArguments
    @Override
    public void onCreate(Bundle b)
    {
        super.onCreate(b);
        Bundle bun = getArguments();
        id = bun.getLong("ID");

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


        parent = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View gui = inflater.inflate(R.layout.fragment_layout, null);

        TextView tv = (TextView)gui.findViewById(R.id.tv);
        tv.setText("You clicked on ID:" + id);
        return gui;
    }
}
