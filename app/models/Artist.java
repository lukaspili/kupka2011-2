package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
@Entity
public class Artist extends Model {

    public String name;

    public Artist() {}

    public Artist(String name) {
        this.name = name;
    }
}
