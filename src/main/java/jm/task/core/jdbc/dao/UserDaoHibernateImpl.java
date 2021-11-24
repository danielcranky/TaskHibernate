package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Users(id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                " name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, age TINYINT NOT NULL)";

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql);
            query.executeUpdate();
            transaction.commit();

        } catch (HibernateException hibernateException) {
            hibernateException.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS Users";

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql);
            query.executeUpdate();
            transaction.commit();

        } catch (HibernateException hibernateException) {
            hibernateException.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO Users(name, lastName, age) VALUES (:name, :lastName, :age)";

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.setParameter("name", name);
            query.setParameter("lastName", lastName);
            query.setParameter("age", age);
            query.executeUpdate();
            transaction.commit();
            System.out.println("User с именем - " + name + " добавлен в базу данных");

        } catch (HibernateException hibernateException) {
            hibernateException.printStackTrace();
        }


    }

    @Override
    public void removeUserById(long id) {
        String sql = "DELETE FROM Users WHERE id = :id";

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql);
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();

        } catch (HibernateException hibernateException) {
            hibernateException.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM Users";
        List<User> allUsers = new ArrayList();

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            Query query = session.createSQLQuery(sql).addEntity(User.class);
            allUsers = query.list();

        } catch (HibernateException hibernateException) {
            hibernateException.printStackTrace();
        }

        return allUsers;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE Users";

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql);
            query.executeUpdate();
            transaction.commit();

        } catch (HibernateException hibernateException) {
            hibernateException.printStackTrace();
        }
    }
}
