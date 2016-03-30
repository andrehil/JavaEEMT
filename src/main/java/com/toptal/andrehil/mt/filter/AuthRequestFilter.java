package com.toptal.andrehil.mt.filter;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.jpa.internal.EntityManagerFactoryImpl;

import com.toptal.andrehil.mt.hibernate.SchemaResolver;

/**
 * Filter to intercept requests and get the user from the header.
 *
 * @author André Hildinger
 */

@Provider
public class AuthRequestFilter implements ContainerRequestFilter {

    @PersistenceUnit(unitName = "pu")
    private EntityManagerFactory entityManagerFactory;
    @PersistenceContext(unitName = "pu", name = "persistence/pu")
    protected EntityManager em;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        final SessionFactoryImplementor sessionFactory = ((EntityManagerFactoryImpl) entityManagerFactory).getSessionFactory();
        final SchemaResolver schemaResolver = (SchemaResolver) sessionFactory.getCurrentTenantIdentifierResolver();

        final String username = containerRequestContext.getHeaderString("username");
        schemaResolver.setTenantIdentifier(username);
    }

}
