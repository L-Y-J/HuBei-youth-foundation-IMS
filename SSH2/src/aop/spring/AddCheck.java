package aop.spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by yongjie on 14-5-14.
 */

@Aspect
public class AddCheck {
	@Pointcut("execution(* serviceimpl..*.add*(..))")
	private void aspectjMethod(){};

	@Around("aspectjMethod()")
	public Object doUserCheck(ProceedingJoinPoint point)throws Throwable {
		System.out.println("测试");
		return true;

	}
}
