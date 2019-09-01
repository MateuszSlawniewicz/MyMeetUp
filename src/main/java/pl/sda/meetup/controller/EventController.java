package pl.sda.meetup.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.meetup.dao.model.User;
import pl.sda.meetup.dao.repositories.EventRepository;
import pl.sda.meetup.dao.repositories.UserRepository;
import pl.sda.meetup.dto.EventDto;
import pl.sda.meetup.dto.UserLoginDto;
import pl.sda.meetup.mappers.UserMapper;
import pl.sda.meetup.services.EventService;
import pl.sda.meetup.services.UserService;

import javax.validation.Valid;
import java.util.List;

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
        List<EventDto> eventsDto = eventService.showAllEvents();
        if(eventsDto.isEmpty()){
            return "index";
        }
        model.addAttribute("eventsDto", eventsDto);
        return "index";
    }





}
