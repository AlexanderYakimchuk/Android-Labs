package com.alex.lab3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class StoreActivity extends AppCompatActivity{
    private DBHelper dbHelper;
    private final static String TAG = "myTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        LinearLayout layout = findViewById(R.id.my_list);
        LayoutInflater inflater = getLayoutInflater();
        dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_INPUT, null, null, null, null, null, null);
        Log.i(TAG, "this is activity 2");
        if (cursor.moveToFirst()){
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int inputTextIndex = cursor.getColumnIndex(DBHelper.KEY_INPUT_TEXT);
            int fontIndex = cursor.getColumnIndex(DBHelper.KEY_FONT);

            View itemH = inflater.inflate(R.layout.my_item, layout, false);
            TextView idH = itemH.findViewById(R.id.record_id);
            TextView textH = itemH.findViewById(R.id.record_text);
            TextView fontH = itemH.findViewById(R.id.record_font);

            idH.setText(R.string.idColumn);
            textH.setText(R.string.textColumn);
            fontH.setText(R.string.fontColumn);

            idH.setTypeface(null, Typeface.BOLD);
            textH.setTypeface(null, Typeface.BOLD);
            fontH.setTypeface(null, Typeface.BOLD);

            itemH.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
            layout.addView(itemH);
            do {
                View item = inflater.inflate(R.layout.my_item, layout, false);
                TextView id = item.findViewById(R.id.record_id);
                TextView text = item.findViewById(R.id.record_text);
                TextView font = item.findViewById(R.id.record_font);

                id.setText(Integer.toString(cursor.getInt(idIndex)));
                text.setText(cursor.getString(inputTextIndex));
                font.setText(cursor.getString(fontIndex));

                item.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
                layout.addView(item);
            } while (cursor.moveToNext());
        } else{
            Toast toast = Toast.makeText(StoreActivity.this, "Table has no records", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
        cursor.close();
    }

}
