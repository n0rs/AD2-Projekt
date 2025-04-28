import java.time.LocalDateTime;

public class User {
    private String userName;
    private String email;
    private String password;
    private LocalDateTime registrationTime;
    private LocalDateTime activationTime;
    private String passwordResetToken;

    public User(String userName, String email, String password, LocalDateTime registrationTime, LocalDateTime activationTime) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.registrationTime = registrationTime;
        this.activationTime = activationTime;
    }

    public String getUserId() {
        return userName;
    }
    public void setUserId(String userName) {
        this.userName = userName;
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
    public LocalDateTime getRegistrationTime() {
        return registrationTime;
    }
    public void setRegistrationTime(LocalDateTime registrationTime) {
        this.registrationTime = registrationTime;
    }
    public LocalDateTime getActivationTime() {
        return activationTime;
    }
    public void setActivationTime(LocalDateTime activationTime) {
        this.activationTime = activationTime;
    }
    public String getPasswordResetToken() {
        return passwordResetToken;
    }
    public void setPasswordResetToken(String passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }
}
