package aop.spring;

import bean.Power;
import bean.Role;
import bean.RoleType;
import bean.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import javax.annotation.Resource;

/**
 * Created by Administrator on 14-5-15.
 */
@Aspect
public class AddCheck {
    @Resource
    private sqlcheck SqlCheck;
    @Pointcut("execution(* serviceimpl..*.addUser(..))")
    private void fooadduser(){};
    @Around("fooadduser()")
    public Object CheckAddUser(ProceedingJoinPoint point)throws Throwable{
        Object[] args = point.getArgs();
        User tempuser = (User) args[0];
        boolean tempbool= SqlCheck.check("User","userName",tempuser.getUserName())==0;
        if (tempbool) point.proceed();
        return tempbool;
    }

    @Pointcut("execution(* serviceimpl..*.addPower(..))")
    private void fooaddpower(){};
    @Around("fooaddpower()")
    public Object CheckAddPower(ProceedingJoinPoint point)throws Throwable{
        Object[] args = point.getArgs();
        Power tempuser = (Power) args[0];
        boolean tempbool= SqlCheck.check("Power","powerName",tempuser.getPowerName())==0;
        if (tempbool) point.proceed();
        return tempbool;
    }

    @Pointcut("execution(* serviceimpl..*.addRole(..))")
    private void fooaddrole(){};
    @Around("fooaddrole()")
    public Object CheckAddRole(ProceedingJoinPoint point)throws Throwable{
        Object[] args = point.getArgs();
        Role tempuser = (Role) args[0];
        boolean tempbool= SqlCheck.check("Role","roleName",tempuser.getRoleName())==0;
        if (tempbool) point.proceed();
        return tempbool;
    }

    @Pointcut("execution(* serviceimpl..*.addRoleType(..))")
    private void fooaddroletype(){};
    @Around("fooaddroletype()")
    public Object CheckAddRoleType(ProceedingJoinPoint point)throws Throwable{
        RoleType tempuser = (RoleType) point.getArgs()[0];
        boolean tempbool= SqlCheck.check("RoleType","typeName",tempuser.getTypeName())==0;
        if (tempbool) point.proceed();
        return tempbool;
    }
}
