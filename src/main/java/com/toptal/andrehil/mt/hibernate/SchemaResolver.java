package com.toptal.andrehil.mt.hibernate;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

/**
 * Implementation for the hibernate tenant identifier resolver by schema.
 *
 * @author André Hildinger
 *
 */
public class SchemaResolver implements CurrentTenantIdentifierResolver {

    private String tenantIdentifier = "public";

    @Override
    public String resolveCurrentTenantIdentifier() {
        return tenantIdentifier;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return false;
    }

    public void setTenantIdentifier(String tenantIdentifier) {
        this.tenantIdentifier = tenantIdentifier;
    }
}