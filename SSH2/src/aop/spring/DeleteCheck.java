package aop.spring;

import bean.Power;
import bean.Role;
import bean.RoleType;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;
import java.util.Set;

/**
 * Created by Administrator on 14-5-17.
 */
@Aspect
public class DeleteCheck {
    @Resource
    private SessionFactory sessionFactory;
    @Resource
    private sqlcheck SqlCheck;

    @Pointcut("execution(* serviceimpl..*.deletePower(..))")
    private void foodeletePower(){};
    @Around("foodeletePower()")
    public Object CheckDeletePower(ProceedingJoinPoint point){
        Power temp = (Power) point.getArgs()[0];
        Set user = temp.getUser();
        Set role = temp.getRole();
        return "There are "+user.size()+" users and "+role.size()+"role use this power";
    }

    @Pointcut("execution(* serviceimpl..*.deleteRole(..))")
    private void foodeleteRole(){};
    @Around("foodeleteRole()")
    public Object CheckDeleteRole(ProceedingJoinPoint point){
        Role temp = (Role) point.getArgs()[0];
        Set user = temp.getUser();
        Set power = temp.getPower();
        Set roleType = temp.getRoleType();
        return "There are "+user.size()+" users,"+power.size()+"role and "+roleType.size()+"roletype use this role";
    }

    @Pointcut("execution(* serviceimpl..*.deleteRoleType(..))")
    private void foodeleteRoleType(){};
    @Around("foodeleteRoleType()")
    public Object CheckDeleteRoleType(ProceedingJoinPoint point){
        RoleType temp = (RoleType) point.getArgs()[0];
        Set role = temp.getRole();
        return "There are "+role.size()+"role use this roletype";
    }
}