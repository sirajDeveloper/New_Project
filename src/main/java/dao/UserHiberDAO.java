package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.DBHelper;

import java.util.List;

public class UserHiberDAO implements UserDAO {

    private SessionFactory sessionFactory;
    private Session session;

    public UserHiberDAO() {
        this.sessionFactory = DBHelper.getDBHelper().getSessionFactory();
    }

    @Override
    public List<User> getAllUsersDAO() {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> list = (List<User>) session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public void addUserDAO(User user) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteUserDAO(Long id) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
/*
        session.createQuery("DELETE from User WHERE id = :id").executeUpdate();
*/
        session.delete(id);
        transaction.commit();
        session.close();
    }

    @Override
    public void updateUserDAO(User newUser) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        /*int id = newUser.getId();
        String name = newUser.getName();
        String email = newUser.getEmail();
        Query query = session.createQuery("UPDATE User SET name = :name, email = :email where id = :id");
        query.setParameter("id", id);
        query.setParameter("name", name);
        query.setParameter("email", email);*/
        session.update(newUser);
        transaction.commit();
        session.close();
    }

    @Override
    public User getUserById(Long id) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM User where id = :id");
        query.setParameter("id", id);
        User user = (User) query.list().get(0);
        transaction.commit();
        return user;
    }

    @Override
    public User getRoleUserDAO(String email, String password) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM User where email = :email and password = :password");
        query.setParameter("email", email);
        query.setParameter("password", password);
        User user = (User) query.list().get(0);
        transaction.commit();
        return user;
    }
}
