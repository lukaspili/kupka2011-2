package controllers;

import play.mvc.Controller;

import java.util.*;

import models.*;
import play.mvc.Scope;
import play.test.Fixtures;

public class Application extends Controller {

    public static void index() {
        render();
    }

    public static void notFound(String message) {
        render(message);
    }

    public static void sendVideoMessage(Long senderId, Long recipientId, String videoPath) {
        User recipient = User.findById(recipientId);
        User sender = User.findById(senderId);
        VideoMessage videoMessage = new VideoMessage(videoPath, recipient, sender);
        videoMessage.save();
    }

    public static void landing() {
        render();
    }

    public static void removeAll() {
        Fixtures.deleteAllModels();
    }

}