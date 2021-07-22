package com.codecool.demo.controller;

import com.codecool.demo.controller.status.BadRequestHttpException;
import com.codecool.demo.controller.status.InternalServerErrorHttpException;
import com.codecool.demo.controller.status.NotFoundHttpException;
import com.codecool.demo.model.Event;
import com.codecool.demo.model.EventAsJson;
import com.codecool.demo.repositories.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class EventsController {

    private EventRepository eventRepository;
    private Logger logger;

    @Autowired
    public EventsController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
        this.logger = LoggerFactory.getLogger(EventsController.class);
    }

    @GetMapping("/api/events")
    public List<Event> getAllEvents() {
        return (List<Event>) eventRepository.findAll();
    }

    @GetMapping("/api/event/{id}")
    public Event getAnEventById(@PathVariable(value = "id") Long id){
        Optional<Event> maybeEvent = eventRepository.findById(id);
        return maybeEvent.orElseThrow(NotFoundHttpException::new);
    }

    @PostMapping("/api/events")
    public Event addAnEvent(@RequestBody EventAsJson simpleEvent) {
        Event newEvent = mapFromEventAsJson(simpleEvent);
        Event createdEvent;
        try {
             createdEvent = eventRepository.save(newEvent);
        } catch (IllegalArgumentException e) {
            // when newEvent is null
            logger.warn(e.getMessage());
            throw new BadRequestHttpException();
        }
        return createdEvent;
    }

    @PutMapping("/api/event/{id}")
    public Event replaceEventById(
            @PathVariable(value = "id") Long id,
            @RequestBody EventAsJson simpleEvent
    ) {
       Event newEvent = mapFromEventAsJson(simpleEvent);
       if (newEvent == null) {
           throw new BadRequestHttpException();
       }
       newEvent.setId(id);
       Event replacedEvent = eventRepository.save(newEvent);
       return replacedEvent;
    }

    @PatchMapping("/api/event/{id}")
    public Event updateEventById(
            @PathVariable("id") Long id,
            @RequestBody EventAsJson simpleEvent
    ) {
        Event maybeEvent = eventRepository.findById(id).orElseThrow(NotFoundHttpException::new);
        updateNonNullFieldsOfJson(simpleEvent, maybeEvent);
        Event updatedEvent = eventRepository.save(maybeEvent);
        return updatedEvent;
    }

    // Will require ADMIN rights!!
    @DeleteMapping("/api/events")
    public void deleteAllEvents() {
        eventRepository.deleteAll();
    }


    @DeleteMapping("/api/event/{id}")
    public void deleteAnEventById(@PathVariable("id") Long id) {
        eventRepository.deleteById(id);
    }



    private void updateNonNullFieldsOfJson(EventAsJson json, Event toUpdate) {
        if (json.getName() != null) {
            toUpdate.setName(json.getName());
        }
        if (json.getInfo() != null) {
            toUpdate.setInfo(json.getInfo());
        }
        if (json.getRewardValue() != null) {
            Integer value;
            try {
                value = Integer.parseInt(json.getRewardValue());

            } catch (NumberFormatException e) {
                throw new BadRequestHttpException();
            }
            toUpdate.setRewardValue(value);
        }
    }


    private Event mapFromEventAsJson(EventAsJson json) {
        Event newEvent;
        try {
            newEvent = new Event(
                    null,
                    json.getName(),
                    json.getInfo(),
                    Integer.parseInt(json.getRewardValue())
            );
        } catch (NullPointerException | NumberFormatException e) {
            // when trying to access the property of a null
            logger.warn(e.getMessage());
            throw new BadRequestHttpException();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new InternalServerErrorHttpException();
        }
        return newEvent;
    }
}
