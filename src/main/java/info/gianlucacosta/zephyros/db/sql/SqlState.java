package info.gianlucacosta.zephyros.db.sql;

import java.util.Arrays;
import java.util.Optional;

/**
 * Common SQL states
 */
public enum SqlState {
    UNIQUE_VIOLATION("23505");

    public static Optional<SqlState> getByCode(String code) {
        return
                Arrays
                        .stream(SqlState.values())
                        .filter(sqlState ->
                                sqlState.code.equals(code)
                        )
                        .findAny();
    }


    private final String code;


    SqlState(String code) {
        this.code = code;
    }


    public String getCode() {
        return code;
    }
}
