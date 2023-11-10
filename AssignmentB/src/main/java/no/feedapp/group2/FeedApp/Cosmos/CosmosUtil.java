package no.feedapp.group2.FeedApp.Cosmos;

import io.github.cdimascio.dotenv.Dotenv;

public class CosmosUtil {
    public static String getCosmosPrimaryKey() {
        Dotenv dotenv = Dotenv.load();
        return dotenv.get("PRIMARY_KEY_COSMOS");
    }
}