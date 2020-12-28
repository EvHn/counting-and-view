package example.view.jpa;

import example.view.data.CountingResult;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.Collections;
import java.util.List;

/**
 * @author evgkhan
 */
@Stateless
public class CountingResultRepository implements ICountingResultRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addCountingResult(CountingResult countingResult) {
        em.persist(countingResult);
    }

    @Override
    public List<CountingResult> loadCountingResultsOrderBy(example.view.data.Order order, String field) {
        switch(order) {
            case Desc:
                return loadCountingResultsOrderBy(CriteriaBuilder::desc, field);
            case Asc:
                return loadCountingResultsOrderBy(CriteriaBuilder::asc, field);
        }
        return Collections.EMPTY_LIST;
    }

    private <T> List<CountingResult> loadCountingResultsOrderBy(SortingMethod method, String field) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CountingResult> query = cb.createQuery(CountingResult.class);
        Root<CountingResult> from = query.from(CountingResult.class);
        query.select(from).orderBy(method.apply(cb,from.get(field)));
        return em.createQuery(query).getResultList();
    }
}
