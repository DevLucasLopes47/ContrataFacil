package com.uni.servicehiring.application.specification;

import com.uni.servicehiring.domain.model.Provider;

public interface Specification {
    boolean isSatisfiedBy(Provider provider);
}
