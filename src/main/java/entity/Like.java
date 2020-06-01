package entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class Like {
    private int id;
    private int user_likes;
    private int user_liked;

    public Like(int user_likes, int user_liked) {
        this.user_likes = user_likes;
        this.user_liked = user_liked;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Like)) return false;
        Like like = (Like) o;
        return user_likes == like.user_likes &&
                user_liked == like.user_liked;
    }



    @Override
    public int hashCode() {
        return Objects.hash(user_likes, user_liked);
    }


}
