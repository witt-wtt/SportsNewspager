package com.teamsports.sportsnewspager.column;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.teamsports.sportsnewspager.sportsnewspager.R;

public class ColumnDetail extends ActionBarActivity {

    private String jsonId="";
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_column_detail);
        //冠军时刻的id=cczmvun4647844
        jsonId=getIntent().getExtras().getString("jsonId");

        listView = ((ListView) findViewById(R.id.columnDetail_listView));
    }


}
