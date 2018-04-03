package com.example.server;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;

public class PotatOsServerApplication extends Application {

    private final Set<Class<?>> classes;

    public PotatOsServerApplication() {
        HashSet<Class<?>> c = new HashSet<Class<?>>();
        c.add(RESTApi.class);   //You can register more classes here that handle paths
        classes = Collections.unmodifiableSet(c);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }
}
