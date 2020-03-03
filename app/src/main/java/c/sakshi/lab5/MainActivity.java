package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    String usernameKey = "username";

    public void onButtonClick(View view) {
        EditText name = (EditText) findViewById(R.id.editTextName);
        EditText password = (EditText) findViewById(R.id.editTextPassword);
        String str = name.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", str).apply();

        goToNotes(str);
    }

    public void goToNotes(String s) {
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("message", s);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        if (!sharedPreferences.getString(usernameKey, "").equals("")) {
            goToNotes(sharedPreferences.getString(usernameKey, ""));
        } else {
            setContentView(R.layout.activity_main);
        }
    }
}
