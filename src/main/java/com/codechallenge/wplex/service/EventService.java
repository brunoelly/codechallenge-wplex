package com.codechallenge.wplex.service;

import com.codechallenge.wplex.dto.EventResponse;
import com.codechallenge.wplex.entity.Event;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    private static final Logger logger = LoggerFactory.getLogger(EventService.class);
    private static final double EARTH_RADIUS = 6371000;

    @Value("${csv.file.path}")
    private String csvFilePath;

    public List<EventResponse> findNearbyEvents(double latitude, double longitude) throws IOException {
        List<Event> events = readEvents();
        processPayload(events);

        List<EventResponse> nearbyEvents = new ArrayList<>();

        for (Event event : events) {
            // Calcular a distância
            double distance = calculateDistance(latitude, longitude, event.getLatitude(), event.getLongitude());
            if (distance <= 50) {
                nearbyEvents.add(mapToEventResponse(event, distance));
            }
        }

        nearbyEvents.sort((e1, e2) -> e1.getInstant().compareTo(e2.getInstant()));

        return nearbyEvents;
    }

    private List<Event> readEvents() throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader();

        try (InputStream inputStream = new ClassPathResource(csvFilePath).getInputStream()) {
            MappingIterator<Event> iterator = mapper.readerFor(Event.class).with(schema).readValues(inputStream);
            return iterator.readAll();
        }
    }

    private void processPayload(List<Event> events) {
        for (Event event : events) {
            String[] payloadParts = event.getPayload().replace(">", "").replace("<", "").split(",");
            if (payloadParts.length >= 4) {
                event.setLatitude(Double.parseDouble(payloadParts[2]));
                event.setLongitude(Double.parseDouble(payloadParts[3]));
            }
        }
    }

    private EventResponse mapToEventResponse(Event event, double distance) {
        EventResponse response = new EventResponse();
        response.setLatitude(event.getLatitude());
        response.setLongitude(event.getLongitude());
        response.setDevice(event.getDevice());
        response.setPrefix(event.getPrefix());
        response.setInstant(event.getInstant());
        response.setCompany(event.getCompany());
        response.setDistance(distance);
        return response;
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }
}