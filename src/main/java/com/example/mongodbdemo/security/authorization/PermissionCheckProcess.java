package com.example.mongodbdemo.security.authorization;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@EnableAspectJAutoProxy
@Component
@Aspect
@Slf4j
@Order(1)
public class PermissionCheckProcess {

    @Around("@annotation(permissionChecker)")
    public Object process(ProceedingJoinPoint point, PermissionChecker permissionChecker) throws Throwable {
        String[] permissions = permissionChecker.permissions();
        // TODO: check permission here, deny process if no permission
        log.info("TODO: checking permissions: {}", Arrays.toString(permissions));
        return point.proceed();
    }

}
