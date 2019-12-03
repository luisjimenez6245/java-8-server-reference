package controllers.utils;

import sources.mysql.repositoryMysql;
import sources.requests.RepositoryRequests;

/**
 *
 * @author luis
 */
public class Devs extends iController {

    public Devs(repositoryMysql source) {
        super(source);
    }

    @Override
    public void get(String name, RepositoryRequests repository) {
        System.out.println(name);
        if (name.equals("email")) {
            res = JSON.toJson(source.getEmail(repository.getEmail()));
        }

        if (name.equals("phone")) {
            res = JSON.toJson(source.getPhone(repository.getPhone()));
        }

        if (name.equals("user")) {
            res = JSON.toJson(source.getUser(repository.getUser()));
        }

    }

    @Override
    public void getList(String name, RepositoryRequests repository) {

        if (name.equals("email")) {
            res = JSON.toJson(source.getEmailList(repository.getEmail()));
        }

        if (name.equals("phone")) {
            res = JSON.toJson(source.getPhoneList(repository.getPhone()));
        }

        if (name.equals("user")) {
            res = JSON.toJson(source.getUserList(repository.getUser()));
        }
    }

    @Override
    public void save(String name, RepositoryRequests repository) {

        if (name.equals("email")) {
            res = JSON.toJson(source.saveEmail(repository.getEmail()));
        }

        if (name.equals("phone")) {
            res = JSON.toJson(source.savePhone(repository.getPhone()));
        }

        if (name.equals("user")) {
            res = JSON.toJson(source.saveUser(repository.getUser()));
        }
    }

    @Override
    public void delete(String name, RepositoryRequests repository) {

        if (name.equals("email")) {
            res = JSON.toJson(source.deleteEmail((repository.getEmail()).emailId));
        }

        if (name.equals("phone")) {
            res = JSON.toJson(source.deletePhone((repository.getPhone()).phoneId));
        }

        if (name.equals("user")) {
            res = JSON.toJson(source.deleteUser((repository.getUser()).userId));
        }
    }

    @Override
    public void update(String name, RepositoryRequests repository) {

        if (name.equals("email")) {
            res = JSON.toJson(source.setEmail(repository.getEmail()));
        }

        if (name.equals("phone")) {
            res = JSON.toJson(source.setPhone(repository.getPhone()));
        }
        if (name.equals("user")) {
            res = JSON.toJson(source.setUser(repository.getUser()));
        }
    }

}
