package io.yukimaru.l4s_sns;

import android.content.ClipData;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CustomAdapter.OnLikeClickListener {
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
        adapter.setOnLikeClickListener(this);
        listView.setAdapter(adapter);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
        adapter.addAll(getSampleData());
    }

    @Override
    public void onLikeClick(int position) {
        Toast.makeText(MainActivity.this, "いいねが押されたよ", Toast.LENGTH_SHORT).show();
    }
//    @Override
//    public void onLikeClick(int position){//CustomAdapterで設定したadapterのonLikeClick
//        Item item = adapter.getItem(position);
//        if (item == null) return;
//
//        int likeCount = item.getLikeCount();
//        likeCount = likeCount + 1;
//
//        item.setLikeCount(likeCount);
//    }
    public List<Item> getSampleData() {
        return Arrays.asList(
                new Item("title", "content", 3),
                new Item("title", "content", 3),
                new Item("title", "content", 3),
                new Item("title", "content", 3),
                new Item("title", "content", 3),
                new Item("title", "content", 3),
                new Item("title", "content", 3),
                new Item("title", "content", 3),
                new Item("title", "content", 3),
                new Item("title", "content", 3));
    }
}
