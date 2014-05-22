package aop.spring;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import javax.annotation.Resource;

/**
 * Created by Administrator on 14-5-17.
 */
public class sqlcheck {
    @Resource
    private  SessionFactory sessionFactory;
    private  String sqlselect="SELECT count(*) FROM %s WHERE %s='%s'";
    public int check(String table, String colum, String value)
    {
        String temp=String.format(sqlselect,table,colum,value);
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery(temp);
        return  Integer.parseInt((query.list()).get(0).toString());
    }
    private  String sqlselect2="SELECT count(*) FROM %s WHERE %s='%s' and id!=%d";
    public int checkother(String table, String colum, String value,int id)
    {
        String temp=String.format(sqlselect2,table,colum,value,id);
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery(temp);
        return  Integer.parseInt((query.list()).get(0).toString());
    }
}
