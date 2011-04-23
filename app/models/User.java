package models;

import com.google.gson.JsonObject;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
@Entity
public class User extends Model {

    public String email;
    public String firstName;
    public String lastName;
    public String gender;
    public Date birthDate;

    @ManyToOne
    public City city;

    @ManyToMany
    public List<Social> socials;

    @ManyToMany
    public List<Artist> artists;

    public User() {
        socials = new ArrayList<Social>();
        artists = new ArrayList<Artist>();
    }

    public static void register(String firstName, String lastName, String gender, String birthDate) {

    }

    public static void facebookOAuthCallback(JsonObject data) {

    }

}