package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public void onButtonClick(View view) {
        EditText name = (EditText) findViewById(R.id.editTextName);
        EditText password = (EditText) findViewById(R.id.editTextPassword);
        String str = name.getText().toString();

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
        setContentView(R.layout.activity_main);
    }
}
