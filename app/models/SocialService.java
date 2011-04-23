package models;

import play.db.jpa.Model;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
public class SocialService extends Model {

    public enum Service {
        FACEBOOK("facebook");

        Service(String identification) {
            this.identification = identification;
        }

        public String identification;
    }

    public Long id;
    public Service service;

    public SocialService() {}

    public SocialService(Service service) {
        this.service = service;
    }
}