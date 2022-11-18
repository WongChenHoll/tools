package test.enums;

/**
 * @author WongChenHoll
 * @date 2022-11-18 17:12
 **/
public enum SqlFunctionEnum {
    SELECT("SELECT ${columns} FROM ${table} WHERE ${condition}") {
        @Override
        public String buildSQL(String columns, String table, String condition, String values) {
            return getExpression().replace("${columns}", columns).replace("${table}", table).replace("${condition}", condition == null ? "1=1" : condition);
        }
    },
    DELETE("DELETE ${table} WHERE ${condition}") {
        @Override
        public String buildSQL(String columns, String table, String condition, String values) {
            return getExpression().replace("${columns}", columns).replace("${table}", table).replace("${condition}", condition == null ? "1=1" : condition);

        }
    },
    UPDATE("UPDATE ${table} SET ${columns} WHERE ${condition}") {
        @Override
        public String buildSQL(String columns, String table, String condition, String values) {
            return getExpression().replace("${columns}", columns).replace("${table}", table).replace("${condition}", condition == null ? "1=1" : condition);

        }
    },
    INSERT("INSERT INTO ${table} (${columns}) values (${values})") {
        @Override
        public String buildSQL(String columns, String table, String condition, String values) {
            return getExpression().replace("${columns}", columns).replace("${table}", table).replace("${values}", values);

        }
    };

    private final String expression;

    SqlFunctionEnum(String expression) {
        this.expression = expression;
    }

    public abstract String buildSQL(String columns, String table, String condition, String values);

    public String getExpression() {
        return expression;
    }
}
