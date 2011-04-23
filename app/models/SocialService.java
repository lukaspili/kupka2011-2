package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.List;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
@Entity
public class SocialService extends Model {

//    public enum ServiceName {
//        FACEBOOK("facebook");
//
//        ServiceName(String identification) {
//            this.identification = identification;
//        }
//
//        public String identification;
//    }
//    public ServiceName service;

    public String name;

    public Social social;
    public List<Artist> artists;

    public SocialService() {}

}