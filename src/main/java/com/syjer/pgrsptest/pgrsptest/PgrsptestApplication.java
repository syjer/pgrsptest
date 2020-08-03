package com.syjer.pgrsptest.pgrsptest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootApplication
@Configuration
public class PgrsptestApplication {

	public static void main(String[] args) {
		SpringApplication.run(PgrsptestApplication.class, args);
	}


	@Bean
	public DataSourceTransactionManager getDataSourceTransactionManager(DataSource dataSource) {
		return new CustomDataSourceTransactionManager(dataSource);
	}


	private static class CustomDataSourceTransactionManager extends DataSourceTransactionManager {
		CustomDataSourceTransactionManager(DataSource dataSource) {
			super(dataSource);
		}

		@Override
		protected void prepareTransactionalConnection(Connection con, TransactionDefinition definition) throws SQLException {
			super.prepareTransactionalConnection(con, definition);

			try (Statement s = con.createStatement()) {
				s.execute("set local role application_user");
			}
			try (Statement s = con.createStatement()) {
				s.execute("set local my.var = '1'");
			}
		}
	}
}
