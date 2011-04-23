package models;

import play.db.jpa.Model;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
public class City extends Model {

    public Long id;
    public String name;

    public City() {}

    public City(String name) {
        this.name = name;
    }
}
