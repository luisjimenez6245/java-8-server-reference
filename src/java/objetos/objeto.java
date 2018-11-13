/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.sql.ResultSet;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author luis
 * @param <T>
 */
public abstract interface objeto<T> {


    public abstract T parse(HttpServletRequest request);

    public abstract T parse(ResultSet res);

    public abstract String toJSON();

    public abstract List<T> parseList(HttpServletRequest request);

    public abstract List<T> parseList(ResultSet res);

}
