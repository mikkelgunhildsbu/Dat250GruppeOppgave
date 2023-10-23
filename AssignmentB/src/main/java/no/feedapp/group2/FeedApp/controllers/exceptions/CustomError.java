package no.feedapp.group2.FeedApp.controllers.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
public class CustomError {
    public CustomError(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    private String fieldName;
    private String message;
}
