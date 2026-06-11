package com.uni.servicehiring;

import com.uni.servicehiring.application.filter.ProviderFilterEngine;
import com.uni.servicehiring.application.specification.*;
import com.uni.servicehiring.domain.factory.UserFactory;
import com.uni.servicehiring.domain.model.*;
import com.uni.servicehiring.domain.repository.InMemoryProviderRepository;
import com.uni.servicehiring.domain.service.ContractService;
import com.uni.servicehiring.domain.service.ProviderService;

import java.util.Set;

public class Main {

    public static void main(String[] args) {

        var repo = new InMemoryProviderRepository();
        var filter = new ProviderFilterEngine();
        var providerService = new ProviderService(repo, filter);
        var contractService = new ContractService();

        Provider p1 = UserFactory.createProvider(
                "1",
                "João",
                "joao@mail.com",
                Set.of(Category.CARPENTRY),
                4.8,
                120
        );

        Provider p2 = UserFactory.createProvider(
                "2",
                "Maria",
                "maria@mail.com",
                Set.of(Category.CLEANING),
                4.2,
                80
        );

        providerService.registerProvider(p1);
        providerService.registerProvider(p2);

        var spec = new CompositeSpecification(
                new CategorySpecification(Category.CARPENTRY),
                new RatingSpecification(4.5)
        );

        System.out.println("Providers encontrados:");
        providerService.search(spec).forEach(p ->
                System.out.println(p.getName())
        );

        Client client = UserFactory.createClient("10", "Cliente X", "c@c.com");

        Contract contract = contractService.createContract(client, p1);

        System.out.println("Contrato criado: " + contract.getId());
    }
}
