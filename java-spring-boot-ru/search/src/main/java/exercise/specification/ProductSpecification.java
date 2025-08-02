package exercise.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;

// BEGIN
@Component
public class ProductSpecification {
    public Specification<Product> build(ProductParamsDTO params) {
        return withCategoryId(params.getCategoryId())
                .and(withPriceGt(params.getPriceGt()))
                .and(withPriceLt(params.getPriceLt()))
                .and(withRatingGt(params.getRatingGt()))
                .and(withTitleCont(params.getTitleCont()));
    }

    public Specification<Product> withCategoryId(Long categoryId) {
        return (root, query, criteriaBuilder) -> {
            if (categoryId == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("category").get("id"), categoryId);
        };
    }

    public Specification<Product> withPriceGt(Integer priceGt) {
        return ((root, query, criteriaBuilder) -> {
            if (priceGt == null) {
                return null;
            }
            return criteriaBuilder.greaterThan(root.get("price"), priceGt);
        });
    }

    public Specification<Product> withPriceLt(Integer priceGt) {
        return ((root, query, criteriaBuilder) -> {
            if (priceGt == null) {
                return null;
            }
            return criteriaBuilder.lessThan(root.get("price"), priceGt);
        });
    }

    public Specification<Product> withRatingGt(Double ratingGt) {
        return ((root, query, criteriaBuilder) -> {
            if (ratingGt == null) {
                return null;
            }
            return criteriaBuilder.greaterThan(root.get("rating"), ratingGt);
        });
    }

    public Specification<Product> withTitleCont(String titleCont) {
        return (root, query, criteriaBuilder) -> {
            if (titleCont == null) {
                return null;
            }
            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("title")),
                    "%" + titleCont.toLowerCase() + "%"
            );
        };
    }


}
// END
