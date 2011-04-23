package models;

import play.db.jpa.Model;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
public class Artist extends Model {

    public Long id;
    public String name;

    public Artist() {}

    public Artist(String name) {
        this.name = name;
    }
}
