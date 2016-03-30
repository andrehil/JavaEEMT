package com.toptal.andrehil.mt.hibernate;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.service.spi.ServiceRegistryAwareService;
import org.hibernate.service.spi.ServiceRegistryImplementor;

/**
 * Responsible for changing the schema before database interactions.
 * <br>
 * <br>
 * Documentação do Hibernate: {@link see http://docs.jboss.org/hibernate/orm/4.3/devguide/en-US/html_single/#d5e4771}
 *
 * @author André Hildinger
 *
 */
public class MultiTenantProvider implements MultiTenantConnectionProvider, ServiceRegistryAwareService {

    private static final long serialVersionUID = 1L;

    private DataSource dataSource;

    @Override
    public boolean supportsAggressiveRelease() {
        return false;
    }

    @Override
    public void injectServices(ServiceRegistryImplementor serviceRegistry) {
        try {
            final Context init = new InitialContext();
            dataSource = (DataSource) init.lookup("java:/AmbersDS");
        } catch (final NamingException e) {
            throw new RuntimeException("Não foi possível encontrar AmbersDS");
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean isUnwrappableAs(Class clazz) {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> clazz) {
        return null;
    }

    @Override
    public Connection getAnyConnection() throws SQLException {
        final Connection connection = dataSource.getConnection();
        return connection;
    }

    @Override
    public Connection getConnection(String tenantIdentifier) throws SQLException {
        final Connection connection = getAnyConnection();
        try {
            connection.createStatement().execute("SET SCHEMA '" + tenantIdentifier + "'");
        } catch (final SQLException e) {
            throw new HibernateException("Could not alter JDBC connection to specified schema [" + tenantIdentifier + "]", e);
        }
        return connection;
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        try {
            connection.createStatement().execute("SET SCHEMA 'public'");
        } catch (final SQLException e) {
            throw new HibernateException("Could not alter JDBC connection to specified schema [public]", e);
        }
        connection.close();
    }

    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        releaseAnyConnection(connection);
    }
}