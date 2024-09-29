package com.codechallenge.wplex.controller;

import com.codechallenge.wplex.dto.EventResponse;
import com.codechallenge.wplex.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    private static final Logger logger = LoggerFactory.getLogger(EventController.class);
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<EventResponse>> getNearbyEvents(@RequestParam double latitude, @RequestParam double longitude) {
        logger.info("Received request for events near latitude: {}, longitude: {}", latitude, longitude);
        if (latitude < -90 || latitude > 90 || longitude < -180 || longitude > 180) {
            return ResponseEntity.badRequest().body(null);
        }

        try {
            List<EventResponse> events = eventService.findNearbyEvents(latitude, longitude);
            logger.info("Found events: {}", events);
            return ResponseEntity.ok(events);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}