package com.multitenancy.test.multitenancy.routing;

import com.multitenancy.test.multitenancy.thread.ThreadLocalStorage;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class TenantAwareRoutingSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return ThreadLocalStorage.getTenantName();
    }

}
