package com.haapp.formicary.persistence.specification;

import com.haapp.formicary.domain.model.SearchCampaignsParams;
import com.haapp.formicary.infrastructure.exception.ServiceException;
import com.haapp.formicary.persistence.model.Campaign;
import com.haapp.formicary.persistence.model.Campaign_;
import com.haapp.formicary.persistence.model.Category;
import com.haapp.formicary.persistence.model.Category_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class SearchCampaignsSpecification implements Specification<Campaign> {

    protected SearchCampaignsParams searchParams;

    protected Root<Campaign> root;

    protected CriteriaBuilder builder;

    protected Predicate result;

    protected CriteriaQuery<?> query;

   public SearchCampaignsSpecification(SearchCampaignsParams searchParams) {
       this.searchParams = searchParams;
    }

    @Override
    public Predicate toPredicate(Root<Campaign> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        init(root, query, builder);
        addSearchCriterias();
        return result;
    }

    protected void init(Root<Campaign> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        this.root = root;
        this.builder = builder;
        this.result = builder.conjunction();
        this.query = query;
    }

    protected void addSearchCriterias(){
        addCategoryId();
    }

    private void addCategoryId() {
        if (searchParams.hasCategoryId()) {
           Join<Campaign, Category> user = root.join(Campaign_.category);
            equal(user.get(Category_.id), searchParams.getCategoryId());
        }
    }

    protected void equal(Expression<?> expression, Object value) {
        result.getExpressions().add(
                builder.equal(expression, value)
        );
    }

    protected <Y> void equal(Path<Y> path, Object value) {
        result.getExpressions().add(
                builder.equal(path, value)
        );
    }

    protected void greaterThan(Expression<ZonedDateTime> path, ZonedDateTime value) {
        result.getExpressions().add(
                builder.greaterThan(path, value)
        );
    }

    protected void greaterThanOrEqualTo(Expression<ZonedDateTime> path, ZonedDateTime value) {
        result.getExpressions().add(
                builder.greaterThanOrEqualTo(path, value)
        );
    }

    protected void lessThan(Expression<ZonedDateTime> path, ZonedDateTime value) {
        result.getExpressions().add(
                builder.lessThan(path, value)
        );
    }

    protected void lessThan(Expression<Integer> path, Integer value) {
        result.getExpressions().add(
                builder.lessThan(path, value)
        );
    }

    protected void lessThanOrEqualTo(Expression<Integer> path, Integer value) {
        result.getExpressions().add(
                builder.lessThanOrEqualTo(path, value)
        );
    }

    protected void greaterThan(Expression<Integer> path, Integer value) {
        result.getExpressions().add(
                builder.greaterThan(path, value)
        );
    }

    protected void greaterThanOrEqualTo(Expression<Integer> path, Integer value) {
        result.getExpressions().add(
                builder.greaterThanOrEqualTo(path, value)
        );
    }

    protected void lessThan(Expression<BigDecimal> path, BigDecimal value) {
        result.getExpressions().add(
                builder.lessThan(path, value)
        );
    }

    protected void lessThanOrEqualTo(Expression<BigDecimal> path, BigDecimal value) {
        result.getExpressions().add(
                builder.lessThanOrEqualTo(path, value)
        );
    }

    protected void greaterThan(Expression<BigDecimal> path, BigDecimal value) {
        result.getExpressions().add(
                builder.greaterThan(path, value)
        );
    }

    protected void greaterThanOrEqualTo(Expression<BigDecimal> path, BigDecimal value) {
        result.getExpressions().add(
                builder.greaterThanOrEqualTo(path, value)
        );
    }

    protected void lessThan(Expression<Long> path, Long value) {
        result.getExpressions().add(
                builder.lessThan(path, value)
        );
    }

    protected void greaterThan(Expression<Long> path, Long value) {
        result.getExpressions().add(
                builder.greaterThan(path, value)
        );
    }

    protected void ending(Expression<String> path, Object value) {
        result.getExpressions().add(
                builder.like(path, "%" + value)
        );
    }

    protected void like(Expression<String> path, Object value) {
        result.getExpressions().add(
                builder.like(path, "%" + value + "%")
        );
    }

    protected void or(Predicate... predicates) {
        result.getExpressions().add(
                builder.or(predicates)
        );
    }

    protected void and(Predicate... predicates) {
        result.getExpressions().add(
                builder.and(predicates)
        );
    }

    protected <Y extends Comparable<? super Y>> void between(Expression<? extends Y> expression, Y from, Y to) {
        result.getExpressions().add(
                builder.between(expression, from, to)
        );
    }

    protected <Y> void in(Expression<? extends Y> expression, Y values) {
        result.getExpressions().add(
                expression.in(values)
        );
    }

    protected <Y> void isNull(Expression<? extends Y> expression) {
        result.getExpressions().add(
                expression.isNull()
        );
    }
}
