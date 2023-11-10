package no.feedapp.group2.FeedApp.Cosmos;
// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

import com.azure.cosmos.*;
import com.azure.cosmos.ConsistencyLevel;
import com.azure.cosmos.models.CosmosItemRequestOptions;
import com.azure.cosmos.models.PartitionKey;
import no.feedapp.group2.FeedApp.Cosmos.PollInformation.Poll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class CosmosApp {

    /**
     * For application to log INFO and ERROR.
     */
    private static Logger logger = LoggerFactory.getLogger(CosmosApp.class.getSimpleName());
    /** Azure Cosmos DB endpoint URI. */
    private static String endpointUri = "https://db-for-feedapp.documents.azure.com:443/";

    /** Azure Cosmos DB primary key. */
    private static String primaryKey = CosmosUtil.getCosmosPrimaryKey();

    /** Azure Cosmos DB client instance. */
    private static CosmosAsyncClient client;

    /** Azure Cosmos DB database instance. */
    private static CosmosAsyncDatabase database;

    /** Azure Cosmos DB container instance. */
    private static CosmosAsyncContainer container;

    public void basicOperations(String id, String question, String g_count, String r_count){
        client = new CosmosClientBuilder()
                .endpoint(endpointUri)
                .key(primaryKey)
                .consistencyLevel(ConsistencyLevel.EVENTUAL)
                .directMode()
                .contentResponseOnWriteEnabled(true)
                .buildAsyncClient();

        database = client.getDatabase("PollResults");
        container = database.getContainer("polls");

        logger.info("Database and container validation complete");

        Poll newPollResults = new Poll(
                id,
                "Poll",
                r_count,
                g_count,
                question

        );

        createUserDocumentsIfNotExist(new ArrayList<Poll>(Arrays.asList(newPollResults)));

        client.close();
    }
    public CosmosApp() {
        // not called
    }

    /**
     * Take in list of Java POJOs, check if each exists, and if not insert it.
     * @param polls List of User POJOs to insert.
     */
    private static void createUserDocumentsIfNotExist(final List<Poll> polls) {
        Flux.fromIterable(polls).flatMap(poll -> {
            try {
                container.readItem(poll.getId(), new PartitionKey(poll.getResult()), Poll.class).block();
                logger.info("Poll {} already exists in the database", poll.getId());
                return Mono.empty();
            } catch (Exception err) {
                logger.info("Creating Poll {}", poll.getId());
                return container.createItem(poll, new PartitionKey(poll.getResult()), new CosmosItemRequestOptions());
            }
        }).blockLast();
    }

    /**
     * Main.
     * @param args Command line arguments
     */
    /*public static void main(final String[] args) {
        try {
            CosmosApp p = new CosmosApp();
            p.basicOperations();
        } catch (CosmosException e) {
            logger.error("Failed while executing app.", e);
        } finally {
            logger.info("End of demo, press any key to exit.");
        }
    }*/
}

