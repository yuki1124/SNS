package io.yukimaru.l4s_sns;

public class Item {
    private String title;
    private String content;
    private int likeCount;
    private String key;

    public Item(String key,String title, String content, int likeCount){
        this.title = title;
        this.content = content;
        this.likeCount = likeCount;
        this.key = key;
    }

    public Item(){

    }
    public String getTitle(){//今までのタイトル
        return title;
    }

    public void setTitle(String title){//タイトルの情報の更新
        this.title = title;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getContent(){//今までの内容
        return content;
    }

    public void setContent(String content){//タイトルの情報の取得
        this.content = content;
    }

    public int getLikeCount(){//今までのlike数の計算
        return likeCount;
    }

    public void setLikeCount(int likeCount) {//like数の更新
        this.likeCount = likeCount;
    }

}
