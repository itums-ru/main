package p_cwb.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import p_cwb.models.*;

public class SessionFactoryUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger("p_cwb.utils.SessionFactoryUtil");

    private static SessionFactory sessionFactory;

    private SessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Bd1c.class);
                configuration.addAnnotatedClass(Cluster1c.class);
                configuration.addAnnotatedClass(Server.class);
                configuration.addAnnotatedClass(Server1c.class);
                configuration.addAnnotatedClass(Service.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                LOGGER.error(e.toString());
                for(StackTraceElement s:e.getStackTrace())
                    LOGGER.error("\t"+s);
            }
        }
        return sessionFactory;
    }
}
