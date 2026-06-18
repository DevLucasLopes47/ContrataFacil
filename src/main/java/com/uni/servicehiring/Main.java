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

import java.util.List;
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

        Provider provider3 = UserFactory.createProvider(
                "3",
                "Felipe",
                "felipe@gmail.com",
                "99988877766",
                "31977770000",
                Set.of(Category.CARPENTRY, Category.ELECTRICAL),
                4.3,
                35
        );

        providerService.registerProvider(provider1);
        providerService.registerProvider(provider2);
        providerService.registerProvider(provider3);

        System.out.println("======================================");
        System.out.println("CONTRAFÁCIL - DEMONSTRAÇÃO BACK-END");
        System.out.println("======================================");

        System.out.println();
        System.out.println("1. Profissionais cadastrados:");
        printProvider(provider1);
        printProvider(provider2);
        printProvider(provider3);

        System.out.println();
        System.out.println("2. Filtro aplicado:");
        System.out.println("Categoria: CARPENTRY");
        System.out.println("Avaliação mínima: 4.5");

        var specification = new CompositeSpecification(
                new CategorySpecification(Category.CARPENTRY),
                new RatingSpecification(4.5)
        );

        List<Provider> result = providerService.search(specification);

        System.out.println();
        System.out.println("3. Resultado da busca:");

        if (result.isEmpty()) {
            System.out.println("Nenhum profissional encontrado.");
        } else {
            result.forEach(Main::printProvider);
        }

        System.out.println();
        System.out.println("4. Criação de cliente:");

        Client client = UserFactory.createClient(
                "10",
                "Luis",
                "luis@gmail.com",
                "123456"
        );

        System.out.println("Cliente criado: " + client.getName() + " | E-mail: " + client.getEmail());

        System.out.println();
        System.out.println("5. Solicitação de contratação:");

        Contract contract = contractService.createContract(
                client,
                provider1,
                "Serviço de carpintaria residencial."
        );

        System.out.println("Contrato criado: " + contract.getId());
        System.out.println("Cliente: " + contract.getClient().getName());
        System.out.println("Profissional: " + contract.getProvider().getName());
        System.out.println("Descrição: " + contract.getDescription());
        System.out.println("Status inicial: " + contract.getStatus());

        System.out.println();
        System.out.println("6. Aceite da solicitação:");

        contractService.acceptContract(contract);

        System.out.println("Status após aceite: " + contract.getStatus());
        System.out.println("Serviços concluídos do profissional: " + provider1.getCompletedJobs());

        System.out.println();
        System.out.println("7. Registro de avaliação:");

        System.out.println("Avaliação antes: " + provider1.getRating());

        provider1.registerRating(5.0);

        System.out.println("Nova avaliação registrada: 5.0");
        System.out.println("Avaliação atualizada: " + provider1.getRating());

        System.out.println();
        System.out.println("8. Histórico básico de contratos:");

        contractService.list().forEach(c ->
                System.out.println(
                        "Contrato: " + c.getId()
                                + " | Cliente: " + c.getClient().getName()
                                + " | Profissional: " + c.getProvider().getName()
                                + " | Status: " + c.getStatus()
                )
        );

        System.out.println();
        System.out.println("Demonstração finalizada com sucesso.");
    }

    private static void printProvider(Provider provider) {
        System.out.println(
                "Profissional: " + provider.getName()
                        + " | Categorias: " + provider.getCategories()
                        + " | Avaliação: " + provider.getRating()
                        + " | Serviços concluídos: " + provider.getCompletedJobs()
        );
    }
}