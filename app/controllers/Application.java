package controllers;

import models.Artist;
import models.User;
import play.data.validation.Valid;
import play.mvc.Controller;

import javax.persistence.Query;
import javax.xml.validation.Validator;
import java.util.ArrayList;
import java.util.List;

public class Application extends Controller {

    public static void index() {
        render();
    }

    public static void indexDev() {

        // user from session
        User user = getUserFromSession();

        // get the covers from the user
        List<Artist> covers = user.artists;

        // get the matching users
        List<User> matchingUsers = new ArrayList<User>();

        render(user, covers, matchingUsers);
    }

    public static User getUserFromSession() {
        User user = User.find("byEmail", "lukasz.pili@gmail.com").first();
        return user;
    }

    public static void notFound(String message) {
        render(message);
    }



}