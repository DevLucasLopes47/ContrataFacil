package com.uni.servicehiring.application.specification;

import com.uni.servicehiring.domain.model.Provider;
import java.util.Arrays;
import java.util.List;

public class CompositeSpecification implements Specification {

    private final List<Specification> specs;

    public CompositeSpecification(Specification... specs) {
        this.specs = Arrays.asList(specs);
    }

    public boolean isSatisfiedBy(Provider provider) {
        return specs.stream().allMatch(s -> s.isSatisfiedBy(provider));
    }
}
