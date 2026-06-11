package com.uni.servicehiring.domain.repository;

import com.uni.servicehiring.domain.model.Provider;
import java.util.List;

public interface ProviderRepository {
    void save(Provider provider);
    List<Provider> findAll();
}
