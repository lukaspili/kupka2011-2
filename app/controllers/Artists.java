package controllers;

import models.Artist;
import models.User;
import play.mvc.Controller;
import play.mvc.With;

import java.util.List;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
@With(Secure.class)
public class Artists extends Controller {

    public static void index() {

        List<Artist> artists = Artist.findAll();

        //List<Artist> artistsByUser = Artist.find("")

        render(artists);
    }

}
