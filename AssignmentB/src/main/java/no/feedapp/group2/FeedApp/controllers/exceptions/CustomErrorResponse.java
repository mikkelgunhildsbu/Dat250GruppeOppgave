package no.feedapp.group2.FeedApp.controllers.exceptions;

import lombok.Getter;
import no.feedapp.group2.FeedApp.controllers.exceptions.CustomError;

import java.util.ArrayList;
import java.util.List;

public class CustomErrorResponse {

    @Getter
    private List<CustomError> errors = new ArrayList<>();
}

