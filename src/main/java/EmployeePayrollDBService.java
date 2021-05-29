import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class  EmployeePayrollDBService {

    private Connection getConnection() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
        String username = "root";
        String password = "1111";
        System.out.println("connecting to database");
        Connection connection = DriverManager.getConnection(jdbcURL,username,password);
        System.out.println("Connected Successfully!");
        return connection;
    }

    public List<EmployeePayrollData> readData() {
        String sql = "SELECT * FROM employee_payroll";
        List<EmployeePayrollData> employeePayrollList = new ArrayList<EmployeePayrollData>();
        try {
            Connection connection = this.getConnection();
            Statement  statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");
                LocalDate startDate = resultSet.getDate("start").toLocalDate();
                employeePayrollList.add(new EmployeePayrollData(id, name, salary, startDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeePayrollList;
    }
        /*public static void main(String[] args) {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Loaded");
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        listDrivers();
    }

    private static void listDrivers() {
        Enumeration<Driver> driverList = DriverManager.getDrivers();
        while (driverList.hasMoreElements()) {
            Driver driverClass = (Driver) driverList.nextElement();
            System.out.println(" "+driverClass.getClass().getName());
        }
    }*/
}
