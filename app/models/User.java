package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.Date;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
@Entity
public class User extends Model {

    public Long id;
    public String email;
    public String firstName;
    public String lastName;
    public String gender;
    public Date birthDate;
}