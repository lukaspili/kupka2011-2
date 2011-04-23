package models;

import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
@Entity
public class SocialService extends Model {

    @Required
    public String name;

    @OneToMany
    public List<Artist> artists;

    public SocialService() {}

}