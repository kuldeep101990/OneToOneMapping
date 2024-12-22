package oneToOne;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateInheritanceExample {

	 public static void main(String[] args) {
	        // Load the configuration and build the SessionFactory
	        Configuration configuration = new Configuration();
	        configuration.configure("hibernate.cfg.xml");
	        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
	        		.applySettings(configuration.getProperties())
	        		.build();
	        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

	        // Create a session
	        Session session = sessionFactory.openSession();
	        Transaction transaction = null;

	        // Create a new Address
            Address address = new Address();
            address.setStreet("123 Main St");
            address.setCity("Springfield");
            address.setState("Illinois");

            // Create a new Person
            Person person = new Person();
            person.setName("John Doe");
            person.setAddress(address); // Set the Address for Person
	        
	        
	        try {
	            transaction = session.beginTransaction();
	            
	         // Save the Person (which will also save Address due to cascade="all")
	            session.save(person);
	           
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	            sessionFactory.close();
	        }
	    }	            
}