package no.feedapp.group2.FeedApp.controllers.exceptions;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CustomErrorResponse {

    private final List<CustomError> errors = new ArrayList<>();
}

