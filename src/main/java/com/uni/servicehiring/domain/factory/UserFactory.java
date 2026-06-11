package com.uni.servicehiring.domain.factory;

import com.uni.servicehiring.domain.model.*;

import java.util.Set;

public class UserFactory {

    public static Client createClient(String id, String name, String email) {
        return new Client(id, name, email);
    }

    public static Provider createProvider(String id, String name, String email,
                                          Set<Category> categories,
                                          double rating,
                                          int jobs) {
        return new Provider(id, name, email, categories, rating, jobs);
    }
}
