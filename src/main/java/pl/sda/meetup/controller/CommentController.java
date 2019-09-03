package pl.sda.meetup.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.meetup.services.CommentService;


@Controller
@Slf4j
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("/comment/{eventId}")
    public String addComment(@RequestParam(name = "comment") String comment, @PathVariable Long eventId) {
        log.info("Hej");
        log.info(eventId.toString());
        log.info(comment);
        log.info("Hej");
        commentService.saveComment(comment, eventId);

        return "redirect:/search/event/"+eventId;

    }


}
