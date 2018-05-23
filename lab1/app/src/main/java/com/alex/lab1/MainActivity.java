package com.alex.lab1;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String[] data = {"serif", "sans-serif", "monospace", "serif-monospace", "casual", "cursive"};
    TextView myTextView;
    EditText editText;
    Button okButton;
    Button cancelButton;
    private final static String TAG = "myTag";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Title");
        myTextView = (TextView) findViewById(R.id.myTextView);
        myTextView.setFontFeatureSettings("sans-sherif");
        editText = (EditText) findViewById(R.id.editText);
        okButton = (Button) findViewById(R.id.okButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.okButton:
                        String text = editText.getText().toString();
                        myTextView.setText(text);
                        myTextView.setTypeface(Typeface.create(spinner.getSelectedItem().toString(), Typeface.NORMAL));
                        Log.i(TAG, "okButton has been clicked!");
//                        Toast toast = Toast.makeText(MainActivity.this, "okButton", Toast.LENGTH_LONG);
//                        toast.show();
                        break;
                    case R.id.cancelButton:
                        myTextView.setText("");
                        Log.i(TAG, "cancelButton has been clicked!");
                        break;
                }
            }
        };
        okButton.setOnClickListener(listener);
        cancelButton.setOnClickListener(listener);
    }

}
