package com.example.springsecurityapplication.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory() {
        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().
                configure("hibernate.cfg.xml").build();

        Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().
                build();

        SessionFactoryBuilder sessionFactoryBuilder = metadata.getSessionFactoryBuilder();

        SessionFactory sessionFactory = sessionFactoryBuilder.build();

        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = buildSessionFactory();
        }
        return sessionFactory;
    }
}