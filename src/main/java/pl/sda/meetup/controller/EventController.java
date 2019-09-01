package pl.sda.meetup.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sda.meetup.dto.EventDto;
import pl.sda.meetup.mappers.UserMapper;
import pl.sda.meetup.services.EventService;
import pl.sda.meetup.services.UserService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Controller
public class EventController {
    private final EventService eventService;
    private final UserService userService;
    private final UserMapper userMapper;

    public EventController(EventService eventService, UserService userService, UserMapper userMapper) {
        this.eventService = eventService;
        this.userService = userService;
        this.userMapper = userMapper;
    }


    @GetMapping("/add-event")
    public String eventForm(Model model) {
        EventDto eventDto = new EventDto();
        model.addAttribute("eventDto", eventDto);
        return "eventForm";
    }

    @PostMapping("/add-event")
    public String retrieveEventForm(@ModelAttribute("eventDto") @Valid EventDto eventDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "eventForm";
        }
        String userName;
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        eventService.saveEvent(eventDto, userName);
        return "redirect:/";
    }


    @GetMapping("/")
    public String listAllEvents(Model model) {
        List<EventDto> eventsDto = eventService.showAllCurrentEvents();
        if (eventsDto.isEmpty()) {
            return "index";
        }
        model.addAttribute("eventsDto", eventsDto);
        return "index";
    }

    @GetMapping("/search")
    public String listEvents(@RequestParam(name = "title") String title, @RequestParam(name = "type") String type, Model model) {
        log.info(title);
        log.info(type);
        List<EventDto> eventDtos = eventService.showAllEventsIgnoreCase(title);
        switch (type) {
            case "future":
                model.addAttribute("eventsDto", eventDtos.stream()
                        .filter(e -> e.getStart().isAfter(LocalDateTime.now()))
                        .collect(Collectors.toList()));
                break;
            case "current_and_future":
                model.addAttribute("eventsDto", eventDtos.stream()
                        .filter(e -> e.getEnd().isAfter(LocalDateTime.now()))
                        .collect(Collectors.toList()));
                break;
            default:
                model.addAttribute("eventsDto", eventDtos);
                break;
        }
        model.addAttribute("title", title);
        model.addAttribute("type", type);
        return "eventResult";

    }


}
