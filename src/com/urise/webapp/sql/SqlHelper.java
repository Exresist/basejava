package com.urise.webapp.sql;

import com.urise.webapp.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlHelper {
    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public interface Executor<T> {
        T execute(PreparedStatement ps) throws SQLException;
    }

    public <T> T statementExecution(String sql, Executor<T> executor) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
        ) {
            return executor.execute(ps);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}
