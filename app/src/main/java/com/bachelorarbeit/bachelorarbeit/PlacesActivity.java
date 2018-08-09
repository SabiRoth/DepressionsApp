package com.bachelorarbeit.bachelorarbeit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class PlacesActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);
        Button endButton = (Button)findViewById(R.id.button_places_end);
        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endButtonClicked();
            }
        });;
        LinearLayout layout = (LinearLayout)findViewById(R.id.layout_listentry_places);
        Intent intent = getIntent();
        ArrayList<String> selectedActivites = intent.getStringArrayListExtra("selectedActivities");
        LinearLayout.LayoutParams paramsForTextView = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
         );
        paramsForTextView.setMargins(25,40,25,15);

        LinearLayout.LayoutParams paramsforEditView = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        paramsforEditView.setMargins(25, 0, 25, 35);

        for(int i = 0; i<selectedActivites.size(); i++){
            TextView entry = new TextView(getApplicationContext());
            entry.setLayoutParams(paramsForTextView);
            buildTextView(entry, selectedActivites.get(i));
            entry.setText(selectedActivites.get(i));
            layout.addView(entry);
            EditText editText = new EditText(getApplicationContext());
            editText.setLayoutParams(paramsforEditView);
            buildEditText(editText);
            layout.addView(editText);

        }
    }

    private void buildTextView(TextView entry, String textContent){
        entry.setText(textContent);
        entry.setTextColor(getResources().getColor(android.R.color.black));
        entry.setTextSize(23);
    }

    private void buildEditText(EditText editText){
        editText.setHint("Ort eingeben");
        editText.setInputType(InputType.TYPE_CLASS_PHONE);
    }

    private void endButtonClicked(){
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }
}