
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataMapper {

    public List<String> getAllNames() {
        List<String> userList = null;
        Connection con = null;
        try  {
            con = DBconnector.connection();
            String sql = "SELECT fname,lname FROM usertable";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    if (userList == null) {
                        userList = new ArrayList<>();
                    }

                    String fname = rs.getString("fname");
                    String lname = rs.getString("lname");

                    userList.add(fname+" " +lname);
                }
            }  catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return userList;
    }
}
