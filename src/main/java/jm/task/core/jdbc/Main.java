package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

public class Main {

    public static void main(String[] args) {
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.createUsersTable();
        userDaoHibernate.saveUser("Николай", "Задорожнов", (byte) 25);
        userDaoHibernate.saveUser("Игорь", "Круглов", (byte) 12);
        userDaoHibernate.saveUser("Анастасия", "Конева", (byte) 56);
        userDaoHibernate.saveUser("Артур", "Смекалов", (byte) 32);
        userDaoHibernate.getAllUsers();
        userDaoHibernate.cleanUsersTable();
        userDaoHibernate.dropUsersTable();
    }
}
