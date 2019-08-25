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

    @NotNull(message = "not null")
    @NotBlank(message = "not blank")
    @Size(max = 500, message = "max 500 letters")
    private String description;
    private UserLoginDto userLoginDto;
    private EventDto eventDto;
    private LocalDateTime dateOfCreation;
}
