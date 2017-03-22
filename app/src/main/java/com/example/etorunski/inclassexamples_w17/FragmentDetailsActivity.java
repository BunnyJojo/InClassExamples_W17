package com.example.etorunski.inclassexamples_w17;


import android.app.Activity;
import android.os.Bundle;

/**
 * Created by etorunski on 2017-03-15.
 *
 * This activity is only for running on a Phone.
 * It is just a holder for FrameLayout, which will
 * hold the fragment that is normally put on a tablet.
 */

public class FragmentDetailsActivity extends Activity {


    //Immediately create the fragment and insert it in the framelayout
    @Override
    public void onCreate(Bundle b)
    {
        super.onCreate(b);
        setContentView(R.layout.fragment_details);


        //Step 3, create fragment onCreation, pass data from Intent Extras to FragmentTransction
        ListDetailsFragment frag = new ListDetailsFragment();
        Bundle bun =        getIntent().getExtras();
        frag.setArguments( bun );
        getFragmentManager().beginTransaction().add(R.id.fragmentHolder, frag).commit();


    }

}
