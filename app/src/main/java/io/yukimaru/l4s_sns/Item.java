package io.yukimaru.l4s_sns;

public class Item {
    private String title;
    private String content;
    private int likeCount;

    public Item(String title, String content, int likeCount){
        this.title = title;
        this.content = content;
        this.likeCount = likeCount;
    }

    public String getTitle(){//今までのタイトル
        return title;
    }

    public String getContent(){//今までの内容
        return content;
    }

    public int getLikeCount(){//今までのlike数の計算
        return likeCount;
    }

    public void setLikeCount(int likeCount) {//like数の更新
        this.likeCount = likeCount;
    }
}
