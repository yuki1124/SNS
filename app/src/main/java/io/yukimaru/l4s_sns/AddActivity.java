package io.yukimaru.l4s_sns;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference();

    EditText titleText;
    EditText contentText;
    Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        titleText = (EditText)findViewById(R.id.titleText);
        contentText = (EditText)findViewById(R.id.contentText);
        sendButton = (Button)findViewById(R.id.sendButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddActivity.this, "追加！！", Toast.LENGTH_SHORT).show();
                String title = titleText.getText().toString();
                String content = contentText.getText().toString();
                String key = reference.push().getKey();//keyのpush->firebaseのkey(現在時刻を暗号化したもの)
                Item item = new Item(key,title, content, 0);

                reference.child(key).setValue(item).addOnSuccessListener(new OnSuccessListener<Void>() {//保存がされた後の処理
                    @Override
                    public void onSuccess(Void aVoid) {
                        finish();//画面を閉じる
                    }
                });
            }
        });
    }
}
