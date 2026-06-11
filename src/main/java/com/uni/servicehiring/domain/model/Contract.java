package com.uni.servicehiring.domain.model;

import java.time.LocalDateTime;

public class Contract {
    private String id;
    private Client client;
    private Provider provider;
    private LocalDateTime date;

    public Contract(String id, Client client, Provider provider) {
        this.id = id;
        this.client = client;
        this.provider = provider;
        this.date = LocalDateTime.now();
    }

    public String getId() { return id; }
}
