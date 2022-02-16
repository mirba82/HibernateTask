package peaksoft.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import peaksoft.model.User;
import peaksoft.util.Util;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Util.getSession();

    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSession().openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM User");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted all Users!");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSession().openSession();
        session.beginTransaction();
        session.save(new User(name,lastName,age));
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully saveUser");
    }
    @Override
    public void removeUserById(long id) {
        try {
            Session session = Util.getSession().openSession();
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM users WHERE id = ?").executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println(id + " " + "user was removed by id");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<User> getAllUsers() {
        List<User> userList;
        Session session = Util.getSession().openSession();
        session.beginTransaction();
        userList = session.createQuery("FROM User").list();
        session.getTransaction().commit();

        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSession().openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM User");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted all Users");

    }
}
