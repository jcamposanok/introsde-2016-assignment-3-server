package introsde.document.dao;

import introsde.document.util.HerokuDatabaseUrlParser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


@WebListener
public class HealthDao implements ServletContextListener {

    private String DEFAULT_DB_URL = "./db/ehealth.sqlite";
    private String PERSISTENCE_UNIT = "HealthPersistenceUnit";

    private static EntityManagerFactory emf;

    public HealthDao() {
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        String databaseUrl = System.getenv("DATABASE_URL");

        if (databaseUrl == null) {
            String dbFilePath;
            Properties props = new Properties();
            URL url = this.getClass().getClassLoader().getResource("project.properties");
            try {
                props.load(url.openStream());
                dbFilePath = props.getProperty("project.db");
            } catch (IOException e) {
                e.printStackTrace();
                dbFilePath = DEFAULT_DB_URL;
            }
            databaseUrl = String.format("jdbc:sqlite:%s", dbFilePath);
            System.out.println("Use default config in persistence.xml with " + databaseUrl);
        }

        Map<String, String> properties = new HashMap<>();
        HerokuDatabaseUrlParser analyser = new HerokuDatabaseUrlParser(databaseUrl);

        System.out.println("SET JDBC URL TO " + analyser.getJdbcUrl());
        properties.put("javax.persistence.jdbc.url", analyser.getJdbcUrl());
        properties.put("javax.persistence.jdbc.user", analyser.getUserName());
        properties.put("javax.persistence.jdbc.password", analyser.getPassword());

        if ("postgres".equals(analyser.getDbVendor())) {
            System.out.println("SET DRIVER FOR postgres");
            properties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
            properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        } else if ("sqlite".equals(analyser.getDbVendor())) {
            System.out.println("SET DRIVER FOR sqlite");
            properties.put("javax.persistence.jdbc.driver", "org.sqlite.JDBC");
        }

        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT, properties);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        emf.close();
    }

    public static EntityManager createEntityManager() {
        if (emf == null) {
            throw new IllegalStateException("Context is not initialized yet.");
        }
        try {
            return emf.createEntityManager();
        } catch (Exception e) {
            throw e;
        }
    }

}
