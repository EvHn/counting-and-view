package example.view.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;

/**
 * @author evgkhan
 */
@FunctionalInterface
public interface SortingMethod {
    <T> Order apply(CriteriaBuilder cb, Expression<T> expression);
}
