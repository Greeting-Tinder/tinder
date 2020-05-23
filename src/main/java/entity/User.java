package entity;


import java.util.Objects;


public class User {
    private int id;
    private String email;
    private String password;
    private String username;
    private String job;
    private String imgurl;


    public User(int id, String email, String password, String username, String job, String imgUrl) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.job = job;
        this.imgurl = imgUrl;

    }


    public User(String username, String password)
    {
        this.email = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getImgURL() {
        return imgurl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id &&
                email.equals(user.email) &&
                password.equals(user.password) &&
                username.equals(user.username) &&
                job.equals(user.job) &&
                imgurl.equals(user.imgurl);
    }

    public boolean checkEqual(User user)
    {
        return email.equals(user.getEmail()) &&
                password.equals(user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, username, job, imgurl);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", job='" + job + '\'' +
                ", imgurl='" + imgurl + '\'' +
                '}';
    }
}
