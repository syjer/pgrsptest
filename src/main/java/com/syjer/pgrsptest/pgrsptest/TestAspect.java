package com.syjer.pgrsptest.pgrsptest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Component
@Aspect
public class TestAspect {


    private final NamedParameterJdbcTemplate jdbcTemplate;

    public TestAspect(NamedParameterJdbcTemplate jdbcTemplate) {
        System.err.println("aspect yooo");
        this.jdbcTemplate = jdbcTemplate;

    }

    @Around("within(com.syjer.pgrsptest.pgrsptest.*) && (@target(org.springframework.transaction.annotation.Transactional) || " +
            " @annotation(org.springframework.transaction.annotation.Transactional))")
    public Object setRoleAndVariable(ProceedingJoinPoint joinPoint) throws Throwable {
        System.err.println(joinPoint);

        DataSource dataSource = jdbcTemplate.getJdbcTemplate().getDataSource();
        Connection connection = DataSourceUtils.getConnection(dataSource);
        if(DataSourceUtils.isConnectionTransactional(connection, dataSource)) {
            System.err.println("connection is transactional");
            jdbcTemplate.update("set role application_user", new EmptySqlParameterSource());
            jdbcTemplate.update("set local my.var = '1'", new EmptySqlParameterSource());
        } else {
            System.err.println("connection is not transactional");
        }
        if (connection != null) {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }

        return joinPoint.proceed();
    }
}
