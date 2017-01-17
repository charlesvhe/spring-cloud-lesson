package charles.sc.provider.service;

/**
 * Created by issuser on 2017/1/13.
 */
public class User {
    private Long id;
    private String account;
    private String password;

    public User() {
    }

    public User(Long id, String account, String password) {
        this();
        this.id = id;
        this.account = account;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
