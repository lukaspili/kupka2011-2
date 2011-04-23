package controllers;

import models.Artist;
import models.User;
import play.mvc.Controller;

import java.util.List;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
public class Artists extends Controller {

    public static void index() {

        List<Artist> artists = Artist.findAll();

        //List<Artist> artistsByUser = Artist.find("")

        render(artists);
    }

}
