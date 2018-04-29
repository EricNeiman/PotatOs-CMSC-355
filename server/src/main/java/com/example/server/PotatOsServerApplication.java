package com.example.server;
import com.example.server.REST.ClassResource;
import com.example.server.REST.QuestionResource;
import com.example.server.REST.QuizResource;
import com.example.server.REST.UserResource;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;

public class PotatOsServerApplication extends Application {

    private final Set<Class<?>> classes;

    public PotatOsServerApplication() {
        HashSet<Class<?>> c = new HashSet<Class<?>>();
        c.add(RESTApi.class);   //You can register more classes here that handle paths

        c.add(ClassResource.class);
//        c.add(QuestionResource.class);
        c.add(QuizResource.class);
        c.add(UserResource.class);

        classes = Collections.unmodifiableSet(c);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }
}
