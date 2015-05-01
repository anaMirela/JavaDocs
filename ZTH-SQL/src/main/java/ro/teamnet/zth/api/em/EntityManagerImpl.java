package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.database.DBManager;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mi on 4/29/2015.
 */
public class EntityManagerImpl implements EntityManager {

    @Override
    public <T> T findById(Class<T> entityClass, int id) {
        /*conexiune la BD */
        Connection conn = DBManager.getConnection();

        /* informatii entitate */
        String tableName = EntityUtils.getTableName(entityClass);
        ArrayList<ColumnInfo> columnInfos = EntityUtils.getColumns(entityClass);

        /* setare conditie */
        Condition condition = new Condition();
        condition.setColumnName(columnInfos.get(0).getDbName());
        condition.setValue(id);

        /*construire query */
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setQueryType(QueryType.SELECT);
        queryBuilder.setTableName(tableName);
        queryBuilder.addQueryColumns(columnInfos);
        queryBuilder.addCondition(condition);
        String query = queryBuilder.createQuery();

        Statement stm = null;
        T t = null;
        try {
            stm = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResultSet resultSet;
        try {
            resultSet = stm.executeQuery(query);
            if (resultSet.next()) {
                t = entityClass.newInstance();
                for (ColumnInfo c : columnInfos) {
                    Field f = entityClass.getDeclaredField(c.getColumnName());
                    f.setAccessible(true);
                    f.set(t, resultSet.getObject(c.getDbName()));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public <T> List<T> findAll(Class<T> entityClass) {
        List<T> listToReturn = new ArrayList<>();

        Connection conn = DBManager.getConnection();
        String tableName = EntityUtils.getTableName(entityClass);
        ArrayList<ColumnInfo> columnInfos = EntityUtils.getColumns(entityClass);

        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setQueryType(QueryType.SELECT).setTableName(tableName).addQueryColumns(columnInfos);

        String query = queryBuilder.createQuery();
        Statement stm = null;
        try {
            stm = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = null;
        try {
            resultSet = stm.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            while (resultSet.next()) {
                T t = entityClass.newInstance();
                for (ColumnInfo c : columnInfos) {
                    Field f = entityClass.getDeclaredField(c.getColumnName());
                    f.setAccessible(true);
                    f.set(t, resultSet.getObject(c.getDbName()));
                }
                listToReturn.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return listToReturn;
    }

    @Override
    public <T> T insert(T entity) {
        /* conexiune la BD */
        Connection conn = DBManager.getConnection();

        /* informatii entitate */
        String tableName = EntityUtils.getTableName(entity.getClass());
        ArrayList<ColumnInfo> columnInfos = EntityUtils.getColumns(entity.getClass());

        /* setare valori noi pe coloane */
        for (ColumnInfo c : columnInfos) {
            try {
                Field f = entity.getClass().getDeclaredField(c.getColumnName());
                f.setAccessible(true);
                c.setValue(f.get(entity));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        /* construire query */
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setQueryType(QueryType.INSERT).setTableName(tableName).addQueryColumns(columnInfos);

        String query = queryBuilder.createQuery();
        Statement stm = null;
        try {
            stm = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            stm.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        T newInst = null;
        try {
            ResultSet resultSet = stm.executeQuery("SELECT LAST_INSERT_ID()");
            resultSet.next();
            int id = resultSet.getInt(1);
            newInst = (T) findById(entity.getClass(), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newInst;
    }

    @Override
    public <T> T update(T entity) {

        /* conexiune la BD */
        Connection conn = DBManager.getConnection();

        /*informatii despre entitate */
        String tableName = EntityUtils.getTableName(entity.getClass());
        ArrayList<ColumnInfo> columnInfos = EntityUtils.getColumns(entity.getClass());
        Condition condition = new Condition();

        /* setare noile valori pe coloane */
        for (ColumnInfo c : columnInfos) {
            try {
                Field f = entity.getClass().getDeclaredField(c.getColumnName());
                f.setAccessible(true);
                c.setValue(f.get(entity));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        /* setare conditie */
        condition.setColumnName(columnInfos.get(0).getDbName());
        condition.setValue(columnInfos.get(0).getValue());

        /* construire query */
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setQueryType(QueryType.UPDATE).setTableName(tableName).addQueryColumns(columnInfos);
        queryBuilder.addCondition(condition);
        String query = queryBuilder.createQuery();
        Statement stm = null;

        try {
            stm = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /* executare query */
        try {
            stm.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public void delete(Object entity) {
        /* conexiune la BD */
        Connection conn = DBManager.getConnection();

        /* informatii entitate :
         * nume tabel si coloane
         */
        String tableName = EntityUtils.getTableName(entity.getClass());
        ArrayList<ColumnInfo> columnInfos = EntityUtils.getColumns(entity.getClass());

        /* setare noile valori pe coloane */
        for (ColumnInfo c : columnInfos) {
            try {
                Field f = entity.getClass().getDeclaredField(c.getColumnName());
                f.setAccessible(true);
                c.setValue(f.get(entity));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

       /* creare conditie */
        Condition condition = new Condition();
        condition.setColumnName(columnInfos.get(0).getDbName());
        condition.setValue(columnInfos.get(0).getValue());

        /* construire query */
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setQueryType(QueryType.DELETE).setTableName(tableName).addQueryColumns(columnInfos);
        queryBuilder.addCondition(condition);

        /* creare si executare query */
        String query = queryBuilder.createQuery();
        Statement stm = null;
        try {
            stm = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /* executare query */
        try {
            stm.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
