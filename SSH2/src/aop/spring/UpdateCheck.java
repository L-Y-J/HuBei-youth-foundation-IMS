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
 * Created by Administrator on 14-5-17.
 */
@Aspect
public class UpdateCheck {
    @Resource
    private sqlcheck SqlCheck;
    @Pointcut("execution(* serviceimpl..*.updateUser(..))")
    private void fooupdateUser(){}
    @Around("fooupdateUser()")
    public Object CheckUpdateUser(ProceedingJoinPoint point)
    {
        User user=(User) point.getArgs()[0];
        boolean temp = SqlCheck.checkother("User","userName",user.getUserName(),user.getId())==0;
        return true;
    }
    @Pointcut("execution(* serviceimpl..*.updatePower(..))")
    private void fooupdatePower(){}
    @Around("fooupdatePower()")
    public Object CheckUpdatePower(ProceedingJoinPoint point)
    {
        Power temp=(Power) point.getArgs()[0];
        boolean bo = SqlCheck.checkother("Power","powerName",temp.getPowerName(),temp.getId())==0;
        return true;
    }
    @Pointcut("execution(* serviceimpl..*.updateRole(..))")
    private void fooupdateRole(){}
    @Around("fooupdateRole()")
    public Object CheckUpdateRole(ProceedingJoinPoint point)
    {
        Role temp=(Role) point.getArgs()[0];
        boolean bo = SqlCheck.checkother("Power","powerName",temp.getRoleName(),temp.getId())==0;
        return true;
    }
    @Pointcut("execution(* serviceimpl..*.updateRoleType(..))")
    private void fooupdateRoleType(){}
    @Around("fooupdateRoleType()")
    public Object CheckUpdateRoleType(ProceedingJoinPoint point)
    {
        RoleType temp=(RoleType) point.getArgs()[0];
        boolean bo = SqlCheck.checkother("Power","powerName",temp.getTypeName(),temp.getId())==0;
        return true;
    }
}
