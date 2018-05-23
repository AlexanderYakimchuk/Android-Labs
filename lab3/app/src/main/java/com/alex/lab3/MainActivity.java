package com.alex.lab3;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String[] data = {"serif", "sans-serif", "monospace", "serif-monospace", "casual", "cursive"};
    EditText editText;
    Button okButton;
    Button cancelButton;
    Button readButton;
    Button clearButton;
    Spinner spinner;
    DBHelper dbHelper;
    private final static String TAG = "myTag";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Title");

        editText = findViewById(R.id.editText);

        okButton = findViewById(R.id.okButton);
        readButton = findViewById(R.id.readButton);
        clearButton = findViewById(R.id.clearButton);

        okButton.setOnClickListener(this);
        readButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);

        dbHelper = new DBHelper(this);
    }


    @Override
    public void onClick(View v) {
        String text = editText.getText().toString();
        String font = spinner.getSelectedItem().toString();

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        switch (v.getId()) {
            case R.id.okButton:

                values.put(DBHelper.KEY_INPUT_TEXT, text);
                values.put(DBHelper.KEY_FONT, font);
                database.insert(DBHelper.TABLE_INPUT, null, values);

                Log.i(TAG, "okButton has been clicked!");
                Toast toast = Toast.makeText(MainActivity.this, "Record has been added", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 0, 130);
                toast.show();
                break;

            case R.id.readButton:
                Intent intent = new Intent(this, StoreActivity.class);
                startActivity(intent);
                break;

            case R.id.clearButton:
                Log.i(TAG, "clear button");
                database.delete(DBHelper.TABLE_INPUT, null, null);
                break;
        }
        dbHelper.close();
    }
}
