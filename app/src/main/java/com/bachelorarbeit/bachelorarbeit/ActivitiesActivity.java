package com.bachelorarbeit.bachelorarbeit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class ActivitiesActivity extends AppCompatActivity {

    private EditText editTextField;
    private ArrayList<String> allSelectedEntries;
    private Button nextButton;
    private dataSource dataSource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);
        String[] socialActivitiesArrayString = getResources().getStringArray(R.array.activities_social);
        String[] sportActivitiesArrayString = getResources().getStringArray(R.array.activities_sport);
        String[] relaxationActivitiesArrayString = getResources().getStringArray(R.array.activities_relaxation);
        String[] obligationsActivitiesArrayString = getResources().getStringArray(R.array.activities_obligations);
        allSelectedEntries = new ArrayList<String>();
        nextButton = (Button)findViewById(R.id.button_activities_next);
        Button saveButton = (Button)findViewById(R.id.saveButton_activities);
        editTextField = (EditText)findViewById(R.id.editText_activities);
        ListView socialActivitiesListView = (ListView)findViewById(R.id.listView_activities_social);
        ListView sportActivititesListView = (ListView)findViewById(R.id.listView_activities_sport);
        ListView relaxationActivitiesListView = (ListView)findViewById(R.id.listView_activities_relaxation);
        ListView obligationsActivitiesListView = (ListView)findViewById(R.id.listView_activities_obligations);
        ListView ownEntriesActivitiesListView = (ListView) findViewById(R.id.listView_activities_own_entries);
        dataSource = new dataSource(this);
        dataSource.open();

        final ArrayAdapter<String> adapter_socialActivities = new ArrayAdapter<String>(this,
                R.layout.listentry_sensitivities_activities, socialActivitiesArrayString);

        socialActivitiesListView.setAdapter(adapter_socialActivities);
        socialActivitiesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                entryClicked(adapter_socialActivities.getItem(i), view);
            }
        });

        final ArrayAdapter<String> adapter_sportActivities = new ArrayAdapter<String>(this,
                R.layout.listentry_sensitivities_activities, sportActivitiesArrayString);

        sportActivititesListView.setAdapter(adapter_sportActivities);
        sportActivititesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                entryClicked(adapter_sportActivities.getItem(i), view);
            }
        });

        final ArrayAdapter<String> adapter_relaxationActivities = new ArrayAdapter<String>(this,
                R.layout.listentry_sensitivities_activities, relaxationActivitiesArrayString);

        relaxationActivitiesListView.setAdapter(adapter_relaxationActivities);
        relaxationActivitiesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                entryClicked(adapter_relaxationActivities.getItem(i), view);
            }
        });

        final ArrayAdapter<String> adapter_obligationsActivities = new ArrayAdapter<String>(this,
                R.layout.listentry_sensitivities_activities, obligationsActivitiesArrayString);

        obligationsActivitiesListView.setAdapter(adapter_obligationsActivities);
        obligationsActivitiesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                entryClicked(adapter_obligationsActivities.getItem(i), view);
            }
        });

        ArrayList<String> allOwnActivities = dataSource.getAllOwnActivities();
        if(allOwnActivities.size()>0){
            final ArrayAdapter<String> adapter_ownActivities = new ArrayAdapter<String>(this,
                    R.layout.listentry_sensitivities_activities, allOwnActivities);
            ownEntriesActivitiesListView.setAdapter(adapter_ownActivities);
            ownEntriesActivitiesListView.setVisibility(View.VISIBLE);
            ownEntriesActivitiesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    entryClicked(adapter_ownActivities.getItem(i), view);
                }
            });
        }

        if(getIntent().getStringExtra(getResources().getString(R.string.key_intentSource)).equals(getResources().getString(R.string.key_home_value))){
            nextButton.setEnabled(false);
        }
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextButtonClicked();
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveButtonClicked();
            }
        });
    }

    private void entryClicked(String clickedEntry, View viewListEntry){
        if (allSelectedEntries.contains(clickedEntry)) {
            allSelectedEntries.remove(clickedEntry);
        }
        else{
            allSelectedEntries.add(clickedEntry);
            nextButton.setEnabled(true);
        }
        if(allSelectedEntries.size()==0 && getIntent().getStringExtra(getResources().getString(R.string.key_intentSource)).equals(getResources().getString(R.string.key_home_value))){
            nextButton.setEnabled(false);
        }
        CheckedTextView checkedTextView = (CheckedTextView) viewListEntry;
        checkedTextView.toggle();
        checkedTextView.refreshDrawableState();
    }

    private void nextButtonClicked(){
        if(allSelectedEntries.size()!=0){
            dataSource.close();
            Intent i;
            i = new Intent(this, PlacesActivity.class);
            i.putExtra(getResources().getString(R.string.key_selectedActivities), allSelectedEntries);
            i.putExtra(getResources().getString(R.string.key_sensitivitiesString), getIntent().getStringExtra(getResources().getString(R.string.key_sensitivitiesString)));
            startActivity(i);
        }
    }

    /*
      Save own entry in database
    */
    private void saveButtonClicked(){
        if(!(editTextField.getText().toString().equals(""))) {
            allSelectedEntries.add(editTextField.getText().toString());
            dataSource.createActivityEntry(editTextField.getText().toString());
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast), Toast.LENGTH_LONG).show();
            editTextField.setText("");
            nextButton.setEnabled(true);
        }
    }
}