package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Util {
    private static Util instance;
    private static Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/user_data";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    private Util() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static Util getInstance() throws SQLException {
        if (instance == null) {
            instance = new Util();
        } else if (instance.getConnection().isClosed()) {
            instance = new Util();
        }
        return instance;
    }

    //подключение через Hibernate
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            StandardServiceRegistryBuilder registryBuilder =
                    new StandardServiceRegistryBuilder();
            Map<String, String> settings = new HashMap<>();
            settings.put(Environment.URL, URL);
            settings.put(Environment.USER, USERNAME);
            settings.put(Environment.PASS, PASSWORD);
            settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
            registryBuilder.applySettings(settings);
            registry = registryBuilder.build();
            MetadataSources metadataSources = new MetadataSources(registry)
                    .addAnnotatedClass(User.class);
            sessionFactory = metadataSources.buildMetadata().buildSessionFactory();
        }
        return sessionFactory;
    }
}
