package controllers;

import models.Artist;
import models.User;
import org.apache.commons.collections.CollectionUtils;
import play.data.validation.Required;
import play.mvc.Controller;
import play.mvc.With;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
@With(Secure.class)
public class Users extends Controller {

    public static User getUserFromSession() {

        String email = session.get("user");

        if (null == email) {
            Application.index();
        }

        return User.find("byEmail", email).first();
    }

    public static void index() {

        List<User> users = User.findAll();

        render(users);
    }

    public static void matchingUsers(@Required String email) {

        if (null == email) {
            Application.notFound("Email not found");
        }

        // get user from session
        User userSession = getUserFromSession();

        // get user to compare with
        User userCompare = User.find("byEmail", email).first();

        if (null == userCompare) {
            Application.notFound("User not found");
        }

        // get the artists from both users
        List<Artist> artistsSession = userSession.artists;
        List<Artist> artistsCompare = userCompare.artists;

        // get the matching count
        List<Artist> matchingArtists = new ArrayList<Artist>();
        matchingArtists.addAll(CollectionUtils.retainAll(artistsSession, artistsCompare));

        // get the matching percent
        Integer percentSession = matchingArtists.size() * 100 / artistsSession.size();
        Integer percentCompare = matchingArtists.size() * 100 / artistsCompare.size();


        render(userSession, userCompare, matchingArtists, artistsSession, artistsCompare, percentSession, percentCompare);
    }

    public static void profileDev() {

        User user = getUserFromSession();

        render(user);
    }

    public static void profile() {

        User user = getUserFromSession();

        // get user artists
        List<Artist> artistsSession = user.artists;

        // get 10 user artists not null
        List<Artist> userArtists = new ArrayList<Artist>();
        Integer count = 0;
        for (Artist artist : artistsSession) {

            // only 10
            if (count == 10) {
                break;
            }

            // only not null
            if (null == artist.imagePath) {
                continue;
            }

            userArtists.add(artist);

            count++;
        }

        // get all users
        List<User> users = User.findAll();

        Map<Integer, User> matchingUsers = new HashMap<Integer, User>();

        //matchingUsers = List<Integer> matchingPercents = new ArrayList<Integer>();

        List<Artist> artistsTest = Artist.findAll();

        count = 0;
        for (User item : users) {

            if(count == 10) {
                continue;
            }

            if (item.equals(user)) {
                continue;
            }

            // get the artists from the user to compare
            List<Artist> artistsCompare = item.artists;

            // get the matching list
            List<Artist> matchingArtists = new ArrayList<Artist>();
            matchingArtists.addAll(CollectionUtils.retainAll(artistsSession, artistsCompare));

            // get the matching percent
            Integer percentSession = matchingArtists.size() * 100 / artistsSession.size();

            matchingUsers.put(percentSession, item);

            count++;
        }

        render(user, userArtists, matchingUsers);
    }
}