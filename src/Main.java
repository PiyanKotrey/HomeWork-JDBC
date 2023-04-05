
import model.Student;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static JdbcImp jdbcImp;
    private static Scanner scanner;
    public static void main(String[] args) {

        jdbcImp = new JdbcImp();
//        scanner = new  Scanner(System.in);
//        Student student = new Student();
//        System.out.println("Enter Name: ");
//        String name=scanner.nextLine();
//        System.out.println("Enter Gender: ");
//        String gender=scanner.nextLine();
//        System.out.println("Enter className: ");
//        String classroom=scanner.nextLine();
//        insertStudent(new Student(name,gender,classroom));

//        updateStu();
//        deleteByIDStu();
        selectStudent();
//        selectByIDStudent();
//        selectByNameStudent();
//        selectStudent();
    }
    private static void selectStudent(){
        JdbcImp jdbcImp = new JdbcImp();
        try (Connection connection = jdbcImp.dataSource().getConnection()){
            String selectSql = "SELECT * FROM student";
            PreparedStatement statement = connection.prepareStatement(selectSql);
            ResultSet resultSet = statement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumn = metaData.getColumnCount();
            CellStyle numberStyle = new CellStyle(CellStyle.HorizontalAlign.left);

            Table t = new Table(4, BorderStyle.UNICODE_BOX_HEAVY_BORDER,
                    ShownBorders.SURROUND_HEADER_FOOTER_AND_COLUMNS);
            t.setColumnWidth(0, 4, 14);
            t.setColumnWidth(1, 30, 33);
            for (int i=1;i<=numberOfColumn;i++){
                t.addCell(metaData.getColumnName(i));
            }
            int count=0;
            while (resultSet.next()){
                t.addCell(resultSet.getInt("id")+" ");
                t.addCell(resultSet.getString("name"));
                t.addCell(resultSet.getString("gender"));
                t.addCell(resultSet.getString("className"));
                count++;
            }

            t.addCell("Total of Student: "+count,6);
            System.out.println(t.render());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void insertStudent(Student student){
        try(Connection connection= jdbcImp.dataSource().getConnection()){
            String insertSql = "INSERT INTO student VALUES (DEFAULT,?,?,?);";
            PreparedStatement statement = connection.prepareStatement(insertSql);
            statement.setString(1,student.getNames());
            statement.setString(2,student.getGender());
            statement.setString(3,student.getClassName());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    private static void updateStu(){
        try(Connection connection= jdbcImp.dataSource().getConnection()){
            Statement statement = connection.createStatement();
            String updateSql =  "UPDATE student  SET name = 'John Cina' WHERE id = 2";
            statement.executeUpdate(updateSql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    private static void deleteByIDStu(){
        int id=13;
        try(Connection connection= jdbcImp.dataSource().getConnection()){
            PreparedStatement preparedStatement= connection.prepareStatement("DELETE FROM student WHERE id = ?");
            preparedStatement.setInt(1,id);
            int rowDelete= preparedStatement.executeUpdate();
            if (rowDelete > 0) {
                System.out.println("Record with ID " + id + " deleted successfully.");
            } else {
                System.out.println("No record found with ID " + id + ".");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    private static void selectByIDStudent(Integer id){
        try (Connection connection = jdbcImp.dataSource().getConnection()){
            String selectBYIDSql ="SELECT * FROM student WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(selectBYIDSql);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int resultId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                String className = resultSet.getString("className");

                System.out.println("ID: " + resultId + ", Name: " + name + ", Gender: " + gender + "ClassName: "+className);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void selectByNameStudent(){
        try (Connection connection = jdbcImp.dataSource().getConnection()){
            Statement statement = connection.createStatement();
            String name="Leo Messi";
            String selectBYNameSql ="SELECT * FROM student WHERE name = '" + name + "'";
            ResultSet resultSet = statement.executeQuery(selectBYNameSql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String names = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                String className = resultSet.getString("className");

                System.out.println("ID: " + id + ", Name: " + names + ", Gender: " + gender + "ClassName: "+className);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}