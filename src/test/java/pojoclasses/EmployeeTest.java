package pojoclasses;

import junit.framework.TestCase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class EmployeeTest extends TestCase
{
    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;

    @BeforeClass
    public static void before()
    {
        Configuration config = new Configuration();
        config.configure();
        config.addAnnotatedClass( Employee.class ); //specify annotatioed classes here
        //config.addResource("User.hbm.xml"); //specify mapped resources here
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings( config.getProperties() ).build();
        factory = config.buildSessionFactory( serviceRegistry );
    }

    @Test
    public void testEmployee()
    {
       try (Session session = factory.openSession())
        {
            Transaction transaction = session.beginTransaction();
            Employee employee = new Employee( "hemal", "nilanga", 4500 );
            int empId = (int) session.save( employee );
            transaction.commit();
        }
    }
}