package com.uni.servicehiring;

import com.uni.servicehiring.domain.factory.UserFactory;
import com.uni.servicehiring.domain.model.Category;
import com.uni.servicehiring.domain.model.Client;
import com.uni.servicehiring.domain.model.Provider;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserFactoryTest {

    @Test
    void shouldCreateClient() {

        Client client = UserFactory.createClient(
                "1",
                "Maria",
                "maria@email.com",
                "123456"
        );

        assertEquals("1", client.getId());
        assertEquals("Maria", client.getName());
        assertEquals("maria@email.com", client.getEmail());
        assertEquals("123456", client.getPassword());
    }

    @Test
    void shouldCreateProvider() {

        Provider provider = UserFactory.createProvider(
                "2",
                "João",
                "joao@email.com",
                "11122233344",
                "31999990000",
                Set.of(Category.ELECTRICAL),
                4.5,
                20
        );

        assertEquals("2", provider.getId());
        assertEquals("João", provider.getName());
        assertEquals("11122233344", provider.getCpf());
        assertEquals("31999990000", provider.getPhone());
        assertEquals(4.5, provider.getRating());
        assertEquals(20, provider.getCompletedJobs());
    }

    @Test
    void shouldNotCreateClientWithoutPassword() {

        assertThrows(IllegalArgumentException.class, () ->
                UserFactory.createClient(
                        "1",
                        "Maria",
                        "maria@email.com",
                        ""
                )
        );
    }

    @Test
    void shouldNotCreateProviderWithoutCategory() {

        assertThrows(IllegalArgumentException.class, () ->
                UserFactory.createProvider(
                        "2",
                        "João",
                        "joao@email.com",
                        "11122233344",
                        "31999990000",
                        Set.of(),
                        4.5,
                        20
                )
        );
    }
}