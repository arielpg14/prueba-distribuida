package com.distribuida.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

@ApplicationScoped
public class DbConfig {

    @Inject
    @ConfigProperty(name="db.user")
    String dbUser;
    @Inject
    @ConfigProperty(name="db.password")
    String dbPassword;
    @Inject
    @ConfigProperty(name="db.url")
    String dbUrl;

    @PostConstruct
    public void init (){
        System.out.println("********* post construct");

        Config config = ConfigProvider.getConfig();

        /*
        String user = config.getValue("db.user", String.class);
        String passw = config.getValue("db.password", String.class);
        String url = config.getValue("db.url", String.class);

        System.out.println(" op1: user: "+ user);
        System.out.println(" op1: pwd: "+ passw);
        System.out.println(" op1: url: "+ url); */

        System.out.println(" op2: user: "+ dbUser);
        System.out.println(" op2: pwd: "+ dbPassword);
        System.out.println(" op2: url: "+ dbUrl);



    }

//    public HikariConfig hikari(){
//        HikariConfig config = new HikariConfig();
//        config.db
//        config.setUsername("postgres");
//        config.setPassword("postgres");
//        HikariDataSource ds = new HikariDataSource(config);
//        Jdbi jdbi = Jdbi.create(ds);
//        return jdbi.open();
//    }


  public Handle conexion() {
       Jdbi jdbi = Jdbi.create(dbUrl, dbUser, dbPassword);
       return  jdbi.open();
  }


    public void test(){

    }

}
