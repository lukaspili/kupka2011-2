package models;

import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
@Entity
public class City extends Model {

    @Required
    public String name;

    public City() {}

    public City(String name) {
        this.name = name;
    }
    
}
