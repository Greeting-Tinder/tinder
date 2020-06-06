package entity;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class User {
    private int id;
    private String email;
    private String password;
    private String username;
    private String job;
    private String imgurl;

    public User(String username, String password)
    {
        this.email = username;
        this.password = password;
    }
    public User(String email, String password, String username, String job) {
        this.email=email;
        this.password=password;
        this.username=username;
        this.job=job;
    }

    public boolean checkEqual(User user)
    {
        return email.equals(user.getEmail()) &&
                password.equals(user.getPassword());
    }
}
