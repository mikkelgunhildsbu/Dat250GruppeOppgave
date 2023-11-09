package no.feedapp.group2.FeedApp.security;

import io.github.cdimascio.dotenv.Dotenv;

public class PepperUtil {
    public static String getPepper() {
        Dotenv dotenv = Dotenv.load();
        return dotenv.get("PEPPER");
    }
}
