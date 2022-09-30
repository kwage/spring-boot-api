package com.disney.studios;

import com.disney.studios.models.Dog;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

/**
 * Loads stored objects from the file system and builds up
 * the appropriate objects to add to the data source.
 *
 * Created by fredjean on 9/21/15.
 */
@Component
public class PetLoader implements InitializingBean {

    // name of dog table
    private final String DOG_TABLE_NAME = "DOG";

    // Resources to the different files we need to load.
    @Value("classpath:data/labrador.txt")
    private Resource labradors;

    @Value("classpath:data/pug.txt")
    private Resource pugs;

    @Value("classpath:data/retriever.txt")
    private Resource retrievers;

    @Value("classpath:data/yorkie.txt")
    private Resource yorkies;

    @Autowired
    DataSource dataSource;

    /**
     * Load the different breeds into the data source after
     * the application is ready.
     *
     * @throws Exception In case something goes wrong while we load the breeds.
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        // boolean result = createDogTable();
        // System.out.println("TABLE CREATE RESULT: " + result);
        loadBreed("Labrador", labradors);
        loadBreed("Pug", pugs);
        loadBreed("Retriever", retrievers);
        loadBreed("Yorkie", yorkies);
    }

    /**
     * Reads the list of dogs in a category and (eventually) add
     * them to the data source.
     * @param breed The breed that we are loading.
     * @param source The file holding the breeds.
     * @throws IOException In case things go horribly, horribly wrong.
     */
    private void loadBreed(String breed, Resource source) throws IOException, SQLException {
        Connection con = dataSource.getConnection();
        // String insertQuery = "INSERT INTO " + DOG_TABLE_NAME + "(img_url, breed, like_count) VALUES (?, ?, 0)";
        String insertQuery = "INSERT INTO " + DOG_TABLE_NAME + "(img, breed, likes, dislikes) VALUES (?, ?, 0, 0)";

        PreparedStatement insertStatement = con.prepareStatement(insertQuery);
        try ( BufferedReader br = new BufferedReader(new InputStreamReader(source.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                insertStatement.setString(1, line);
                insertStatement.setString(2, breed);
                insertStatement.executeUpdate();
            }
        }
    }
}
