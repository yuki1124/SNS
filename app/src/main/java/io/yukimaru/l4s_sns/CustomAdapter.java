package io.yukimaru.l4s_sns;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Item> {
    List<Item> items;

    private OnLikeClickListener likeClickListener = null;


    public CustomAdapter(Context context, int resource, List<Item> items){
        super(context, resource, items);

        this.items=items;
    }

    @Override
    public int getCount(){//viewに必要なアイテムの数をitemsから数える
        return items.size();
    }

    public Item getItem(int position){// itemsからpositionへ、対応するobjectを返す
        return items.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){//itemに対応するviewを作ってviewHolderに返す
        final ViewHolder viewHolder;

        if(convertView != null){
            viewHolder = (ViewHolder)convertView.getTag();
        }else{
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.sns, parent, false);
            viewHolder = new ViewHolder();

            //viewHolderの関連付け
            viewHolder.titleText = (TextView) convertView.findViewById(R.id.titleText);
            viewHolder.contentText = (TextView) convertView.findViewById(R.id.contentText);
            viewHolder.likeImage = (ImageView) convertView.findViewById(R.id.likeImage);
            viewHolder.likeCountText = (TextView) convertView.findViewById(R.id.likeCountText);

            convertView.setTag(viewHolder);
        }

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

   static class ViewHolder{
        TextView titleText;
        TextView contentText;
        ImageView likeImage;
        TextView likeCountText;
    }

    interface OnLikeClickListener {//OnLikeClickListenerという型（TextView的な）の作成
        void onLikeClick(int position);//interface->特定の機能の概要。classによってimplementsされる。
        //onLikeClickというvoidを呼び出す
    }

    public void setOnLikeClickListener(OnLikeClickListener likeClickListener) {
        this.likeClickListener = likeClickListener;//OnLikeClickListenerの設定。準備。
    }
}
