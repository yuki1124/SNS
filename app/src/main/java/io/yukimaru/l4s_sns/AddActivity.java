package io.yukimaru.l4s_sns;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        EditText titleText = (EditText)findViewById(R.id.titleText);
        EditText contentText = (EditText)findViewById(R.id.contentText);
        Button sendButton = (Button)findViewById(R.id.sendButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddActivity.this, "追加！！", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
