package me.twitter.producer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
public class Twitter {
    private Long id;
    private String message;
    private String author;
}

