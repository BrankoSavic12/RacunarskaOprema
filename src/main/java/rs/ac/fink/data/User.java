/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.fink.data;

/**
 *
 * @author MyPC
 */
import java.io.Serializable;
public class User implements Serializable {
    private int idUser;
    private String fullName;
    private String username;
    private String email;
    private String birthDate;
    private long accountBalance;
    private long spentAmount;

    public User() {}

    public User(int idUser, String fullName, String username, String email, String birthDate, long accountBalance, long spentAmount) {
        this.idUser = idUser;
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.birthDate = birthDate;
        this.accountBalance = accountBalance;
        this.spentAmount = spentAmount;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public long getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(long accountBalance) {
        this.accountBalance = accountBalance;
    }

    public long getSpentAmount() {
        return spentAmount;
    }

    public void setSpentAmount(long spentAmount) {
        this.spentAmount = spentAmount;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", fullName='" + fullName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", accountBalance=" + accountBalance +
                ", spentAmount=" + spentAmount +
                '}';
    }
}