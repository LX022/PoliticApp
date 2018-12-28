package clabersoftware.politicapp.DataBase.Entity;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class PoliticianFB {

    @Exclude
    String politicianUid;
    String firstName;
    String password;
    String lastName;
    String login;
    String fkParty;

    public String getPoliticianUid() {
        return politicianUid;
    }

    public void setPoliticianUid(String politicianUid) {
        this.politicianUid = politicianUid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFkParty() {
        return fkParty;
    }

    public void setFkParty(String fkParty) {
        this.fkParty = fkParty;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("firstName", firstName);
        result.put("password", password);
        result.put("lastName", lastName);
        result.put("login", login);
        result.put("fkParty", fkParty);
        return result;
    }
}
