package pl.sda.meetup.dto;

import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    @NotBlank(message = "not blank")
    @NotNull(message = "not null")
    private String title;
    @Size(min = 20,message = "min 20 letters")
    private String description;
    private UserLoginDto userLoginDto;
    @Future(message = "wrong date")
    private LocalDateTime start;
    @Future(message = "wrong date")
    private LocalDateTime end;

}
