package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Mi on 4/28/2015.
 */
public class EntityUtils {

    private EntityUtils() {
        throw new UnsupportedOperationException();
    }

    public static String getTableName(Class entity) {
        Table t = (Table) entity.getAnnotation(Table.class);
        return t.name();
    }

    public static ArrayList<ColumnInfo> getColumns(Class entity) {
        ArrayList<ColumnInfo> columnInfos = new ArrayList<>();
        ColumnInfo cInfo;
        Field[] fields = entity.getDeclaredFields();
        for (Field f:fields) {
            Column c = (Column)f.getAnnotation(Column.class);
            Id id = (Id) f.getAnnotation(Id.class);
            if (c != null){
                cInfo=new ColumnInfo();
                cInfo.setColumnName(f.getName());
                cInfo.setColumnType(f.getType());
                cInfo.setDbName(c.name());
                cInfo.setIsId(false);
                columnInfos.add(cInfo);
            } else {
                cInfo=new ColumnInfo();
                cInfo.setColumnName(f.getName());
                cInfo.setColumnType(f.getType());
                cInfo.setDbName(id.name());
                cInfo.setIsId(true);
                columnInfos.add(cInfo);
            }


        }
        return columnInfos;
    }

    public static Object castFromSqlType(Object value, Class<?> wantedType) {
        if ((value instanceof BigDecimal) && wantedType == Integer.class){
            BigDecimal b = (BigDecimal)value;
            return b.intValue();
        }
        return value;
    }

    public static ArrayList<Field> getFieldsByAnnotations(Class clazz, Class annotation) {
        ArrayList<Field> returnedFields = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();

        for (Field f : fields) {
            if (f.getAnnotation(annotation) != null){
                returnedFields.add(f);
            }
        }
        return returnedFields;
    }

    public static Object getSqlValue(Object object) {
        return object;
    }
}
