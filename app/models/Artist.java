package models;

import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
@Entity
public class Artist extends Model {

    @Required
    public String name;

    @ManyToMany(mappedBy = "artists")
    public List<User> users;
    
    public String imagePath;

    public Artist() {}

    public Artist(String name) {
        this.name = name;
    }
}
