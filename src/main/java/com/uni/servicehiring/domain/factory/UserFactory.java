package com.uni.servicehiring.domain.factory;

import com.uni.servicehiring.domain.model.Category;
import com.uni.servicehiring.domain.model.Client;
import com.uni.servicehiring.domain.model.Provider;

import java.util.Set;

public class UserFactory {

    public static Client createClient(String id,
                                      String name,
                                      String email,
                                      String password) {
        return new Client(id, name, email, password);
    }

    public static Provider createProvider(String id,
                                          String name,
                                          String email,
                                          String cpf,
                                          String phone,
                                          Set<Category> categories,
                                          double rating,
                                          int completedJobs) {

        return new Provider(
                id,
                name,
                email,
                cpf,
                phone,
                categories,
                rating,
                completedJobs
        );
    }
}