package controllers;

import models.User;
import play.mvc.Controller;
import play.test.Fixtures;

import java.util.List;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
public class Users extends Controller {

    public static void index() {

        List<User> users = User.findAll();

        render(users);
    }

}