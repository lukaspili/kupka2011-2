package libs.lastfm;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import models.Artist;
import play.libs.ws.WSUrlFetch;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * User: derf4002
 * Date: 4/23/11
 * Time: 8:56 PM
 */
public class LastFMApi {

    private static final String API_URL = "http://ws.audioscrobbler.com/2.0/?format=json";


    public enum PictureSize {
        LARGE("large"), MEDIUM("medium"), SMALL("small"), EXTRA_LARGE("extralarge"), LARGE_SQUARE("largesquare");

        private String name;

        PictureSize(String name) {
            this.name = name;
        }
    }

    private String apiKey;


    public LastFMApi(String apiKey) {
        this.apiKey = apiKey;
    }

    public Artist retrieveArtist(String name, PictureSize size) {
        Artist artist = null;

        String serviceURL = API_URL + "&api_key=" + apiKey + "&method=artist.search"
                + "&artist=" + name + "&limit=1";
        try {
            WSUrlFetch ws = new WSUrlFetch();
            JsonObject response = ws.newRequest(serviceURL).get().getJson().getAsJsonObject();
            JsonObject artistObject = response.get("results").getAsJsonObject().get("artistmatches").getAsJsonObject().get("artist").getAsJsonObject();

            String artistName = artistObject.get("name").getAsString();

            if (artistName != null) {
                artist = new Artist();

                artist.name = artistName;

                URL imagePath = retrieveArtistImage(artistName, size);
                if(imagePath != null) artist.imagePath = imagePath.toString();
            }
        } catch (Exception e) {
        }

        return artist;
    }

    private URL retrieveArtistImage(String name, PictureSize size) {
        String serviceURL = API_URL + "&api_key=" + apiKey + "&method=artist.getimages"
                + "&artist=" + name + "&limit=1";

        WSUrlFetch ws = new WSUrlFetch();
        JsonObject response = ws.newRequest(serviceURL).get().getJson().getAsJsonObject();

        try {
            JsonArray images = response.get("images").getAsJsonObject().get("image").getAsJsonObject().get("sizes").getAsJsonObject().get("size").getAsJsonArray();

            try {
                for (JsonElement image : images) {
                    String imageSize = image.getAsJsonObject().get("name").getAsString();
                    if (imageSize.equals(size.name)) {
                        String imagePath = image.getAsJsonObject().get("#text").getAsString();
                        return new URL(imagePath);
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) { }

        return null;
    }

    public static void main(String... args) {
        LastFMApi fmApi = new LastFMApi("1cae0d3a28fc36a955ea9241610d113a");

        Artist artist = fmApi.retrieveArtist("* IAM *", PictureSize.LARGE_SQUARE);
        System.out.println(artist.name);
        System.out.println(artist.imagePath);

        artist = fmApi.retrieveArtist("* LinkinPark *", PictureSize.LARGE_SQUARE);
        System.out.println(artist.name);
        System.out.println(artist.imagePath);

        artist = fmApi.retrieveArtist("Bjorc", PictureSize.LARGE_SQUARE);
        System.out.println(artist.name);
        System.out.println(artist.imagePath);

        artist = fmApi.retrieveArtist("2 be 3", PictureSize.LARGE_SQUARE);
        System.out.println(artist.name);
        System.out.println(artist.imagePath);

        artist = fmApi.retrieveArtist("admiralty", PictureSize.LARGE_SQUARE);
        System.out.println(artist.name);
        System.out.println(artist.imagePath);
    }

}
