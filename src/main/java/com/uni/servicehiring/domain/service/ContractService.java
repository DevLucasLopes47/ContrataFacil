package com.uni.servicehiring.domain.service;

import com.uni.servicehiring.domain.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ContractService {

    private final List<Contract> contracts = new ArrayList<>();

    public Contract createContract(Client client, Provider provider) {
        Contract contract = new Contract(UUID.randomUUID().toString(), client, provider);
        contracts.add(contract);
        return contract;
    }

    public List<Contract> list() {
        return contracts;
    }
}
