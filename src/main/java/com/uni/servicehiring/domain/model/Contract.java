package com.uni.servicehiring.domain.model;

import java.time.LocalDateTime;

public class Contract {

    private String id;
    private Client client;
    private Provider provider;
    private String description;
    private LocalDateTime date;
    private ContractStatus status;

    public Contract(String id, Client client, Provider provider, String description) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID do contrato é obrigatório.");
        }

        if (client == null) {
            throw new IllegalArgumentException("Cliente é obrigatório.");
        }

        if (provider == null) {
            throw new IllegalArgumentException("Profissional é obrigatório.");
        }

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Descrição do serviço é obrigatória.");
        }

        this.id = id;
        this.client = client;
        this.provider = provider;
        this.description = description;
        this.date = LocalDateTime.now();
        this.status = ContractStatus.PENDING;
    }

    public void accept() {
        if (this.status != ContractStatus.PENDING) {
            throw new IllegalStateException("Somente solicitações pendentes podem ser aceitas.");
        }

        this.status = ContractStatus.ACCEPTED;
        this.provider.incrementCompletedJobs();
    }

    public void reject() {
        if (this.status != ContractStatus.PENDING) {
            throw new IllegalStateException("Somente solicitações pendentes podem ser recusadas.");
        }

        this.status = ContractStatus.REJECTED;
    }

    public String getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public Provider getProvider() {
        return provider;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public ContractStatus getStatus() {
        return status;
    }
}