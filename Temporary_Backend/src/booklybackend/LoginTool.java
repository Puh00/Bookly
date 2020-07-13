package booklybackend;

public class LoginTool {

    private User user;

    public LoginTool(User user) {
        this.user = user;
    }

    public boolean login(String username, String password) {
        return confirmUsername(username) && confirmPassword(password);
    }

    private boolean confirmUsername(String username) {
        return user.getUserName().equals(username);
    }

    private boolean confirmPassword(String password) {
        return user.getPassword().equals(password);
    }
}