import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataMapperTest {

    @BeforeEach
    void setUp() {

        System.out.println("Adds data");
        Connection con = null;
        try {
            con = DBconnector.connection();
            String createTable = "CREATE TABLE IF NOT EXISTS `startcode_test`.`usertable` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `fname` VARCHAR(45) NULL,\n" +
                    "  `lname` VARCHAR(45) NULL,\n" +
                    "  `pw` VARCHAR(45) NULL,\n" +
                    "  `phone` VARCHAR(45) NULL,\n" +
                    "  `address` VARCHAR(45) NULL,\n" +
                    "  PRIMARY KEY (`id`));";
            con.prepareStatement(createTable).executeUpdate();
            String SQL = "INSERT INTO startcode_test.usertable (fname, lname, pw, phone, address) VALUES (?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, "Hans");
            ps.setString(2, "Hansen");
            ps.setString(3, "Hemmelig123");
            ps.setString(4, "40404040");
            ps.setString(5,"Rolighedsvej 3");
            ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @AfterEach
    void tearDown() {

        System.out.println("Remove data");
        Connection con = null;
        try {
            con = DBconnector.connection();
            String truncateTable = "TRUNCATE TABLE`startcode_test`.`usertable`";
            con.prepareStatement(truncateTable).executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getAllNames(){
        DataMapper dataMapper = new DataMapper();
        List<String> expected = new ArrayList<String>();
        expected.add("Hans Hansen");
        List<String> actual = dataMapper.getAllNames();
        assertEquals(expected,actual);


    }
}