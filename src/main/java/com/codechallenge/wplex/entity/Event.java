package com.codechallenge.wplex.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Event {
    @JsonProperty("device")
    private String device;

    @JsonProperty("prefix")
    private String prefix;

    @JsonProperty("instant")
    private String instant;

    @JsonProperty("payload")
    private String payload;

    @JsonProperty("company")
    private String company;

    private double latitude;
    private double longitude;
    private double distance;
}