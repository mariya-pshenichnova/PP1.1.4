package jm.task.core.jdbc.util;

import javax.persistence.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;


public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/mybase";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1995";

    public static final String driver = "com.mysql.cj.jdbc.Driver";

    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, driver);
                settings.put(Environment.URL, URL);
                settings.put(Environment.USER, USERNAME);
                settings.put(Environment.PASS, PASSWORD);
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static Connection getConnection() {

        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return connection;
    }
}
