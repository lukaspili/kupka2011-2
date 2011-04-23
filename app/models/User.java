package models;

import com.google.gson.JsonObject;
import play.db.jpa.Model;
import play.mvc.Scope;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
@Entity
public class User extends Model {

    private static DateFormat dateFormat = new SimpleDateFormat();

    public String email;
    public String firstName;
    public String lastName;
    public String gender;
    public Date birthDate;

    @ManyToOne
    public City city;

    public User() {

    }

    public static void register(String firstName, String lastName, String gender, String birthDate) {

    }

    public static void facebookOAuthCallback(JsonObject data) {
        String firstName = data.get("first_name").getAsString();
        String lastName = data.get("last_name").getAsString();
        String gender = data.get("gender").getAsString();
        String birthDate = data.get("birthDate").getAsString();
        String interestedIn = data.get("interested_in").getAsString();

        register()
    }
    
}
