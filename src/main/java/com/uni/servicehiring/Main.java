package com.uni.servicehiring;

import com.uni.servicehiring.application.filter.ProviderFilterEngine;
import com.uni.servicehiring.application.specification.CategorySpecification;
import com.uni.servicehiring.application.specification.CompositeSpecification;
import com.uni.servicehiring.application.specification.RatingSpecification;
import com.uni.servicehiring.domain.factory.UserFactory;
import com.uni.servicehiring.domain.model.Category;
import com.uni.servicehiring.domain.model.Client;
import com.uni.servicehiring.domain.model.Contract;
import com.uni.servicehiring.domain.model.Provider;
import com.uni.servicehiring.domain.repository.InMemoryProviderRepository;
import com.uni.servicehiring.domain.service.ContractService;
import com.uni.servicehiring.domain.service.ProviderService;

import java.util.Set;

public class Main {

    public static void main(String[] args) {

        var repository = new InMemoryProviderRepository();
        var filterEngine = new ProviderFilterEngine();

        var providerService = new ProviderService(repository, filterEngine);
        var contractService = new ContractService();

        Provider provider1 = UserFactory.createProvider(
                "1",
                "Leo",
                "leo@gmail.com",
                "11122233344",
                "31999990000",
                Set.of(Category.CARPENTRY),
                4.8,
                120
        );

        Provider provider2 = UserFactory.createProvider(
                "2",
                "Lucas",
                "lucas@gmail.com",
                "55566677788",
                "31988880000",
                Set.of(Category.CLEANING),
                4.2,
                80
        );

        providerService.registerProvider(provider1);
        providerService.registerProvider(provider2);

        var specification = new CompositeSpecification(
                new CategorySpecification(Category.CARPENTRY),
                new RatingSpecification(4.5)
        );

        System.out.println("Profissionais encontrados:");

        providerService.search(specification).forEach(provider ->
                System.out.println(provider.getName())
        );

        Client client = UserFactory.createClient(
                "10",
                "Luis",
                "luis@gmail.com",
                "123456"
        );

        Contract contract = contractService.createContract(
                client,
                provider1,
                "Serviço de carpintaria residencial."
        );

        System.out.println("Contrato criado: " + contract.getId());
        System.out.println("Status inicial: " + contract.getStatus());

        contractService.acceptContract(contract);

        System.out.println("Status após aceite: " + contract.getStatus());

        provider1.registerRating(5.0);

        System.out.println("Avaliação atualizada do profissional: " + provider1.getRating());
    }
}