package ro.teamnet.zth.api.database;

import org.junit.Test;
import ro.teamnet.zth.api.em.QueryBuilder;
import ro.teamnet.zth.api.em.QueryType;

/**
 * Created by Mi on 4/29/2015.
 */
public class QueryBuilderTest {

    @Test
    public void testCreateQuery() {
        QueryBuilder qb = new QueryBuilder();

        qb.setQueryType(QueryType.SELECT);
        System.out.println(qb.createQuery());
        qb.setQueryType(QueryType.INSERT);
        System.out.println(qb.createQuery());
        qb.setQueryType(QueryType.UPDATE);
        System.out.println(qb.createQuery());
        qb.setQueryType(QueryType.DELETE);
        System.out.println(qb.createQuery());
    }
}
