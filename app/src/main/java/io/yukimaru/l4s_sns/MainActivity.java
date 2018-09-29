package io.yukimaru.l4s_sns;

import android.content.ClipData;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements CustomAdapter.OnLikeClickListener {
    //firebaseとrealtimebaseの初期化
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference();

    ListView listView;
    FloatingActionButton add_button;
    public CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        add_button = (FloatingActionButton) findViewById(R.id.add_button);

        //adapterの設定
        adapter = new CustomAdapter(this, 0, new ArrayList<Item>());
        adapter.setOnLikeClickListener(this);//lisviewのなかのadapterが生成したheartに
        listView.setAdapter(adapter);//adapterの表示先はlistView

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Item item = dataSnapshot.getValue(Item.class);
                adapter.add(item);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Item result = dataSnapshot.getValue(Item.class);
                if (result == null) return;

                Item item = adapter.getItemByKey(result.getKey());
                item.setTitle(result.getTitle());
                item.setContent(result.getContent());
                item.setLikeCount(result.getLikeCount());

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onLikeClick(int position) {
        Toast.makeText(MainActivity.this, "いいねが押されたよ", Toast.LENGTH_SHORT).show();
        Item item = adapter.getItem(position);
        if (item == null) return;
        int likeCount = item.getLikeCount();
        likeCount = likeCount + 1;
        item.setLikeCount(likeCount);

        Map<String, Object> postValues = new HashMap<>();
        postValues.put("/" + item.getKey() + "/", item);

        reference.updateChildren(postValues);
    }
}
//♡を押して落ちる場合は、新しいkeyを追加したのに過去のfirebaseがkeyの項目が追加されていないから、怒られる。　