package controllers;

import models.Artist;
import models.User;
import org.apache.commons.collections.CollectionUtils;
import play.data.validation.Required;
import play.mvc.Controller;
import play.mvc.With;

import java.util.*;
import java.util.jar.Manifest;

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

    public static void matchingUser(@Required String email) {

        if (null == email) {
            notFound("Email not found");
            //Application.notFound("Email not found");
        }

        //validation.errorsMap().size()

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
        List<Artist> matchingArtists = new ArrayList<Artist>(artistsSession);
        matchingArtists.retainAll(artistsCompare);
        //matchingArtists.addAll(CollectionUtils.retainAll(artistsSession, artistsCompare));

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

        List<MatchingWrapper> matchingUsers = new ArrayList<MatchingWrapper>();

        List<Artist> artistsTest = Artist.findAll();

        count = 0;
        for (User item : users) {

            if (count == 10) {
                continue;
            }

            if (item.equals(user)) {
                continue;
            }

            // get the artists from the user to compare
            List<Artist> artistsCompare = item.artists;

            // get the matching list
            List<Artist> matchingArtists = new ArrayList<Artist>(artistsSession);
            matchingArtists.retainAll(artistsCompare);
            //matchingArtists.addAll(CollectionUtils.retainAll(artistsSession, artistsCompare));

            // get the matching percent
            Integer percentSession = matchingArtists.size() * 100 / artistsSession.size();

            MatchingWrapper wrapper = new MatchingWrapper(item, percentSession);
            matchingUsers.add(wrapper);

            count++;
        }

        Collections.sort(matchingUsers);

        render(user, userArtists, matchingUsers);
    }

    public static class MatchingWrapper implements Comparable {

        public User user;
        public Integer percent;

        public MatchingWrapper(User user, Integer percent) {
            this.user = user;
            this.percent = percent;
        }

        public int compareTo(Object o) {

            if(!(o instanceof MatchingWrapper)) {
                return -1;
            }

            return this.percent.compareTo(((MatchingWrapper) o).percent) * -1;
        }
    }
}