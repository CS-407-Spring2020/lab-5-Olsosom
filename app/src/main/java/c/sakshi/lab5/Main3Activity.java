package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main3Activity extends AppCompatActivity {

    int noteid = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // 1. Get EditText view
        EditText editText = (EditText) findViewById(R.id.editText);

        // 2. Get intent
        Intent intent = getIntent();

        // 3. Get value from intent
        noteid = intent.getIntExtra("noteid", -1);

        // 4. Init class
        if (noteid != -1) {
            Note note = Main2Activity.notes.get(noteid);
            String noteContent = note.getContent();

            editText.setText(noteContent);
        }
    }

    public void saveMethod(View view) {
        // 1. Get EditText view
        EditText editText = (EditText) findViewById(R.id.editText);

        // 2. Get SQL Database
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE, null);
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);

        // 3. Init db
        dbHelper.createTable();

        // 4. Set username
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("username", "");

        // 5. Save info to database
        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());
        String content = editText.getText().toString();

        if (noteid == -1) {
            title = "NOTE_" + (Main2Activity.notes.size()+1);
            dbHelper.saveNotes(name, title, content, date);
        } else {
            title = "NOTE_" + (noteid + 1);
            dbHelper.updateNote(title, date, content);
        }

        // Go back
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}
