import java.time.LocalDateTime;

public class User {
    private String userId;
    private String email;
    private String password;
    private LocalDateTime registrationTime;
    private LocalDateTime activationTime;
    private String passwordResetToken;

    public User(String userId, String email, String password, LocalDateTime registrationTime, LocalDateTime activationTime) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.registrationTime = registrationTime;
        this.activationTime = activationTime;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
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
