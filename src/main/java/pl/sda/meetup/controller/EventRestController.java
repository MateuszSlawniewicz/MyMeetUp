package pl.sda.meetup.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.meetup.dto.EventDto;
import pl.sda.meetup.services.EventService;

import java.util.List;

@RestController
public class EventRestController {

    private final EventService eventService;


    public EventRestController(EventService eventService) {
        this.eventService = eventService;
    }


    @GetMapping("/events")
    public List<EventDto> listAllEvents() {
        return eventService.showAllEvents();
    }
}
