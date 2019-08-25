package pl.sda.meetup.dao.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private User user;
    private Event event;
    private LocalDateTime dateOfCreation;


}
