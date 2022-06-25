package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoHibernateImpl();
        //userDao.dropUsersTable();
        userDao.createUsersTable();

        //userDao.saveUser("Adolf", "Dasslier", (byte) 77);
        userDao.saveUser("Rudolf", "Dasslier", (byte) 76);
        userDao.saveUser("Phil", "Knight", (byte) 84);
        userDao.saveUser("Michael", "Jordan", (byte) 59);

        userDao.getAllUsers();
        userDao.removeUserById(1);
        userDao.getAllUsers();
        //userService.removeUserById(1);
        //userService.getAllUsers();
        //userService.cleanUsersTable();
        //userService.dropUsersTable();


    }
}
