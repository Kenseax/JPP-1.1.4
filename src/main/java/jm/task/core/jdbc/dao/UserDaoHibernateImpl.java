package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (id bigint auto_increment, " +
                "name VARCHAR(50) not null, " +
                "lastName VARCHAR(50) not null, " +
                "age TINYINT not null, " +
                "PRIMARY KEY(id))";
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery(sql).executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery(sql).executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User users = new User(name,lastName, age);
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(users);
        transaction.commit();
        session.close();
        System.out.println("Пользователь с именем " + name + " добавлен в БД");
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.get(User.class, id));
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = (List<User>) Util.getSessionFactory().openSession().createQuery("FROM User").list();
        System.out.println(userList);
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM User").executeUpdate();
        transaction.commit();
        session.close();
    }
}
