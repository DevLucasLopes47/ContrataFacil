package com.uni.servicehiring.domain.service;

import com.uni.servicehiring.domain.model.Client;
import com.uni.servicehiring.domain.model.Contract;
import com.uni.servicehiring.domain.model.Provider;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ContractService {

    private final List<Contract> contracts = new ArrayList<>();

    public Contract createContract(Client client, Provider provider, String description) {
        Contract contract = new Contract(
                UUID.randomUUID().toString(),
                client,
                provider,
                description
        );

        contracts.add(contract);

        return contract;
    }

    public void acceptContract(Contract contract) {
        if (contract == null) {
            throw new IllegalArgumentException("Contrato é obrigatório.");
        }

        contract.accept();
    }

    public void rejectContract(Contract contract) {
        if (contract == null) {
            throw new IllegalArgumentException("Contrato é obrigatório.");
        }

        contract.reject();
    }

    public List<Contract> list() {
        return new ArrayList<>(contracts);
    }
}