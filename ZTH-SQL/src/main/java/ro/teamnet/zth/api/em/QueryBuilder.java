package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.database.DBManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mi on 4/29/2015.
 */
public class QueryBuilder {
    private Object tableName;
    private List<ColumnInfo> queryColumns = new ArrayList<>();
    private QueryType queryType;
    private List<Condition> conditions = new ArrayList<>();

    public QueryBuilder addCondition(Condition condition) {
        conditions.add(condition);
        return this;
    }

    public QueryBuilder setTableName(Object tableName) {
        this.tableName = tableName;
        return this;
    }

    public QueryBuilder addQueryColumns(List<ColumnInfo> queryColumns) {
        this.queryColumns.addAll(queryColumns);
        return this;
    }

    public QueryBuilder setQueryType (QueryType queryType) {
        this.queryType = queryType;
        return this;
    }
    private Object objToString(Object obj) {
        if (obj instanceof String)
            return "'" + obj.toString() + "'";
        return obj;
    }

    private String createSelectQuery() {
        StringBuilder result = new StringBuilder();
        result.append("SELECT ");
        for (ColumnInfo c : queryColumns)
            result.append(c.getDbName() + ",");

        result.replace(result.length() - 1, result.length(), " ");
        result.append("FROM " + this.tableName);
        if (!conditions.isEmpty()) {
            result.append(" WHERE ");
            for (Condition c : conditions)
                result.append(c.getColumnName() + "=" + c.getValue() + ",");
            result.replace(result.length() - 1, result.length(), " ");
        }
        result.append(";");
        System.out.println(result.toString());
        return result.toString();
    }

    private String createDeleteQuery() {
        StringBuilder result = new StringBuilder();
        result.append("DELETE " + "FROM " + this.tableName);
        if (conditions != null) {
            result.append(" WHERE ");
            for (Condition c : conditions)
                result.append(c.getColumnName() + "=" + c.getValue() + ",");
        }

        result.replace(result.length() - 1, result.length(), " ");
        result.append(";");
        System.out.println(result.toString());
        return result.toString();
    }

    private String createUpdateQuery() {
        queryColumns.remove(0);
        StringBuilder result = new StringBuilder();
        result.append("UPDATE " + this.tableName);

        result.append(" SET ");
        for (ColumnInfo c : queryColumns) {
            result.append(c.getDbName() + "=" + objToString(c.getValue()) + ",");
        }
        result.replace(result.length() - 1, result.length(), " ");
        result.append(" WHERE ");
        for (Condition c : conditions)
            result.append(c.getColumnName() + "=" + c.getValue() + ",");

        result.replace(result.length() - 1, result.length(), " ");
        result.append(";");
        System.out.println(result.toString());
        return result.toString();
    }

    private String createInsertQuery() {

        StringBuilder result = new StringBuilder();
        queryColumns.remove(0);
        result.append("INSERT INTO " + this.tableName);
        result.append(" ( ");
        for (ColumnInfo c : queryColumns) {
            result.append(c.getDbName() + ",");
        }
        result.replace(result.length() - 1, result.length(), " ");
        result.append(") VALUES (");
        for (ColumnInfo c : queryColumns) {

            result.append(this.objToString(c.getValue()) + ",");
        }
        result.replace(result.length() - 1, result.length(), " ");
        result.append(" );");
        return  result.toString();
    }

    public String createQuery() {
        switch (this.queryType) {
            case SELECT: return createSelectQuery();
            case DELETE: return createDeleteQuery();
            case UPDATE: return createUpdateQuery();
            case INSERT: return createInsertQuery();
        }

        return "Query not found!!\n";
    }
}
