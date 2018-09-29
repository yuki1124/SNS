package io.yukimaru.l4s_sns;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Item> {//<>内で何のためのArrayAdapterかを指定。->中身がItemであるArrayAdapter
    //Itemをどのように表示させるか？というクラス。
    private List<Item> items;// private->このクラスの中でしかこのListは使えないから、勝手にMainActivityで情報が更新されないようにする。触らないで！みたいな

    private OnLikeClickListener likeClickListener = null;

    public CustomAdapter(Context context, int resource, List<Item> items){//コンストラクタ：クラスと同じ名前のメソッド。クラスの初期化のためのメソッドで、他のクラスでCustomAdapterをどうやって使うかを提示。
        super(context, resource, items);

        this.items=items;
    }

    @Override
    public int getCount(){//adapter内のアイテムの数をitemsから数える。size->要素の個数。
        return items.size();
    }

    public Item getItem(int position){// adapterが正しくものを並べられるように、どこに何の情報が入ってるかを見つける。adapterが勝手に使う。
        return items.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){//itemに対応するviewを作ってviewHolderに返す
        final ViewHolder viewHolder;//viewHolder->情報を受け渡しを再利用

        if(convertView != null){//convertView->こすれば情報が消えるスタンプ的な。convertViewがnullではない時はスタンプに情報を掘る。
            viewHolder = (ViewHolder)convertView.getTag();
        }else{//convertViewがnullの時はスタンプを一から作る。
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.sns, parent, false);
            viewHolder = new ViewHolder();

            //viewHolderの関連付け
            viewHolder.titleText = (TextView) convertView.findViewById(R.id.titleText);
            viewHolder.contentText = (TextView) convertView.findViewById(R.id.contentText);
            viewHolder.likeImage = (ImageView) convertView.findViewById(R.id.likeImage);
            viewHolder.likeCountText = (TextView) convertView.findViewById(R.id.likeCountText);

            convertView.setTag(viewHolder);
        }

        //実際に情報を掘る作業。何を掘るか？
        Item item = items.get(position);//item中のpositionの情報を取得、itemへ

        //関連付け
        viewHolder.titleText.setText(item.getTitle());
        viewHolder.contentText.setText(item.getContent());
        viewHolder.likeCountText.setText(String.valueOf(item.getLikeCount()));
        viewHolder.likeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (likeClickListener != null) {
                    likeClickListener.onLikeClick(position);
                }
            }
        });

        return convertView;
    }
    public Item getItemByKey(String key) {
        for (Item item : items) {
            if (item.getKey().equals(key)) {
                return item;
            }
        }

        return null;
    }

   static class ViewHolder{
        TextView titleText;
        TextView contentText;
        ImageView likeImage;
        TextView likeCountText;
    }

    interface OnLikeClickListener {//OnLikeClickListenerという型（TextView的な）の作成。新しいClassって感じ.
        void onLikeClick(int position);
        //onLikeClickというvoidを呼び出す
    }

    public void setOnLikeClickListener(OnLikeClickListener likeClickListener) {
        this.likeClickListener = likeClickListener;//OnLikeClickListenerの設定。準備。
    }
}
