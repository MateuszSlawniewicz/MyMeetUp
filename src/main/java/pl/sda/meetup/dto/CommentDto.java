package pl.sda.meetup.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private String description;
    private UserDto userDto;
    private EventDto eventDto;
    private LocalDateTime dateOfCreation;
    private Long id;
}
