package models;

import play.db.jpa.Model;

import java.util.List;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
public class Relation extends Model {

    public Long id;
    public List<User> users;
    public List<Artist> artists;
    public String social;
}