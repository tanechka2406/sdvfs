package com.inwhite.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by Jeka on 29/06/2014.
 */
@Aspect
@Component
public class TracingAspect {
    @Before("execution(* com.inwhite.controllers..*.*(..))")
    public void printBefore(){
        System.out.println("BEFOREEEEEEEEEEEEEEEee");
    }
}
