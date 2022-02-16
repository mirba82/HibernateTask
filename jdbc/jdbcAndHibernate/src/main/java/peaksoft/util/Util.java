package peaksoft.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import peaksoft.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    // реализуйте настройку соеденения с БД

    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String userName = "postgres";
    private static final String password = "mirba85";

    public static Connection connection() {
        Connection connect = null;
        try {
            connect = DriverManager.getConnection(url, userName, password);
            System.out.println("PostgresSQL ге ийгиликуу уландыныз. Азаматсыз!!! ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connect;
    }
    //реализация конфигураций Hibernate.
    private static SessionFactory session;

    public static SessionFactory getSession() {
        if (session == null) {
            try {
                Configuration configuration = new Configuration();

                Properties properties = new Properties();
                properties.put(Environment.DRIVER, "org.postgresql.Driver");
                properties.put(Environment.URL, "jdbc:postgresql://localhost:5432/postgres");
                properties.put(Environment.USER, "postgres");
                properties.put(Environment.PASS, "mirba85");
                properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL9Dialect");
                properties.put(Environment.SHOW_SQL, "true");
                //properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                properties.put(Environment.HBM2DDL_AUTO, "create");

                configuration.setProperties(properties);
                configuration.addAnnotatedClass(User.class);

                StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                session = configuration.buildSessionFactory( serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return session;
    }

    public static void shutDown() {

        getSession().close();
    }
}






