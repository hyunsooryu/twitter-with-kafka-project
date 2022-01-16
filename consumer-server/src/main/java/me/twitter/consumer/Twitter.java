package me.twitter.consumer;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "TWITTER")
public class Twitter {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="MESSAGE")
    private String message;
    @Column(name="AUTHOR")
    private String author;
}
