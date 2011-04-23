package models;

<<<<<<< .merge_file_A16Kr4
import com.google.gson.JsonObject;
import play.db.jpa.Model;
import play.mvc.Scope;

=======
>>>>>>> .merge_file_q7Jha4
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
<<<<<<< .merge_file_A16Kr4

    public static void facebookOAuthCallback(JsonObject data) {

    }
    
}

=======
}
>>>>>>> .merge_file_q7Jha4
