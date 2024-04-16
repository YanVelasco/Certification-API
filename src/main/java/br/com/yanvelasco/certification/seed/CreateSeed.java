package br.com.yanvelasco.certification.seed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CreateSeed {

    private static final Logger logger = LoggerFactory.getLogger(CreateSeed.class);
    private final JdbcTemplate jdbcTemplate;



    public CreateSeed(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public static void main(String[] args) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5434/pg_nlw");
        dataSource.setUsername("admin"); // Fixed typo "adimin" -> "admin"
        dataSource.setPassword("admin");

        CreateSeed createSeed = new CreateSeed(dataSource);
        createSeed.run(args);
    }

    public void run(String... args) {
        executeSqlFile("src/main/resources/Create.sql");
    }

    public void executeSqlFile(String path) {
        try {
            String sql = new String(Files.readAllBytes(Paths.get(path)));
            jdbcTemplate.execute(sql);
        } catch (IOException e) {
            logger.error("Error executing SQL file: {}", path, e);
        }
    }
}