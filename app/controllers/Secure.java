package controllers;

import play.mvc.Before;
import play.mvc.Controller;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
public class Secure extends Controller {

    @Before
    public static void checkAuth() {
        if (null == session.get("user")) {
            Application.index();
        }
    }
}
