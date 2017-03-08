package com.example.etorunski.inclassexamples_w17;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {

    protected ListView theList;
    protected int numItems = 4;
private static String TAG = "LISTVIEW";


protected String dataItems[]  = new String[] { "Item1", "Item2", "Item 3", "item4", " ","more items"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_view);
        theList = (ListView)findViewById(R.id.theList);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.row_layout, dataItems);
        theList.setAdapter( new MyCustomAdapter());
//              notifyDatasetChanged()
        theList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemClick: " + i + " " + l);

            }
        });

        //adapter.notifyDataSetChanged(); //tells the list to update the data
    }

    private class MyCustomAdapter extends BaseAdapter
    {
        public int getCount()
        {
            return numItems;
        }

        public View getView(int position, View recycled, ViewGroup parent)
        {
            Log.d(TAG, "get view:"+ position);
            View loaded = null;

            if(recycled != null)
            {
                TextView tv = (TextView)recycled.findViewById(R.id.texthere);
                tv.setText(getItem(position));
                return recycled;
            }

            if(position< 2) {

                LayoutInflater loader = getLayoutInflater();
                loaded = loader.inflate(R.layout.row_layout_left, null);
                TextView tv = (TextView)loaded.findViewById(R.id.texthere);
                tv.setText( getItem(position)  );
                CheckBox cb = (CheckBox)loaded.findViewById(R.id.checkbox);
                cb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        numItems += 6; //add more items
                    }
                });
                cb.setChecked( position<1  ); //0 selected, 1 not selected
            }
            else
            {
                LayoutInflater loader = getLayoutInflater();
                loaded = loader.inflate(R.layout.row_layout_right, null);
                TextView tv = (TextView)loaded.findViewById(R.id.texthere);
                tv.setText( getItem(position)  );
                RadioButton cb = (RadioButton)loaded.findViewById(R.id.radioButton);
                cb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MyCustomAdapter.this.notifyDataSetChanged(); //update the list
                    }
                });
                cb.setChecked( position<3  ); //0 selected, 1 not selected
            }

            return loaded;
        }

        public long getItemId(int position)
        {
            return position;
        }

        public String getItem(int position)
        {
            return Integer.toString(position * 5);
        }

    }
}
