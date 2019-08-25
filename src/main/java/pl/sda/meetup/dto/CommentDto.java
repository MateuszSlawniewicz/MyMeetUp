package pl.sda.meetup.dto;

import lombok.*;
import pl.sda.meetup.dao.model.Event;
import pl.sda.meetup.dao.model.User;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private String description;
    private User user;
    private Event event;
    private LocalDateTime dateOfCreation;
}
