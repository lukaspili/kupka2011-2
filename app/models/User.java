package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

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

    public User() {

    }

    public static void register(String firstName, String lastName, String gender, String birthDate) {

    }
}