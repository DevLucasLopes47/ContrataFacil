package com.uni.servicehiring.application.specification;

import com.uni.servicehiring.domain.model.Category;
import com.uni.servicehiring.domain.model.Provider;

public class CategorySpecification implements Specification {

    private final Category category;

    public CategorySpecification(Category category) {
        this.category = category;
    }

    public boolean isSatisfiedBy(Provider provider) {
        return provider.getCategories().contains(category);
    }
}
