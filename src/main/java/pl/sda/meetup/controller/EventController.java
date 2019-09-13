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
import pl.sda.meetup.services.CommentService;
import pl.sda.meetup.services.EventService;
import pl.sda.meetup.services.UserService;

import javax.validation.Valid;
import java.util.List;


@Slf4j
@Controller
public class EventController {
    private final EventService eventService;
    private final CommentService commentService;

    public EventController(EventService eventService, CommentService commentService) {
        this.eventService = eventService;
        this.commentService = commentService;
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
        eventService.saveEvent(eventDto);
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
        List<EventDto> events = eventService.findEventsWithSpecialConditions(title, type);
        model.addAttribute("eventsDto", events);
        model.addAttribute("title", title);
        model.addAttribute("type", type);
        return "eventResult";
    }

    @GetMapping("/search/event/{eventId}")
    public String showEventDetails(@PathVariable Long eventId, Model model) {
        log.info(eventId.toString());
        model.addAttribute("eventDto", eventService.getEventById(eventId));
        return prepareCommentsAndCheckIfUserIsParticipant(eventId, model);
    }

    @GetMapping("/search/event/{eventId}/deleteparticipation")
    public String deleteParticipation(@PathVariable Long eventId, Model model) {
        EventDto eventDto = eventService.deleteParticipation(eventId);
        model.addAttribute("eventDto", eventDto);
        return prepareCommentsAndCheckIfUserIsParticipant(eventId, model);
    }

    @GetMapping("/search/event/{eventId}/participate")
    public String declareParticipation(@PathVariable Long eventId, Model model) {
        EventDto eventDto = eventService.saveParticipation(eventId);
        model.addAttribute("eventDto", eventDto);
        return prepareCommentsAndCheckIfUserIsParticipant(eventId, model);

    }

    private String prepareCommentsAndCheckIfUserIsParticipant(@PathVariable Long eventId, Model model) {
        model.addAttribute("comments", commentService.showAllComments(eventId));
        model.addAttribute("isParticipant", eventService.checkIfUserParticipant(eventId));
        return "eventView";
    }
}
