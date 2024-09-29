package com.codechallenge.wplex.dto;

import lombok.Data;

@Data
public class EventResponse {
    private double latitude;
    private double longitude;
    private String device;
    private String prefix;
    private String instant;
    private String company;
    private double distance;
}
