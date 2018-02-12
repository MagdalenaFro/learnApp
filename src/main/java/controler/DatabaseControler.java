package controler;

import java.nio.file.*;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.*;
import model.DBColumnName;
import model.DatabaseDTO;

import static model.Dictionary.COLUMNS_NAME;
import static model.Dictionary.CREATE_TABLE;
import static model.Dictionary.DB_DIR;
import static model.Dictionary.DB_NAME;
import static model.Dictionary.DB_TYPE_AND_DB_NAME;
import static model.Dictionary.JDBC;
import static model.Dictionary.MISSING_JDBC_DRIVER;
import static model.Dictionary.SEPARATOR;
import static model.Dictionary.TYPE_OF_DATABASE;

/**
 *
 * @author Magdalena
 */
public class DatabaseControler {

    DatabaseConnector databaseConnector = new DatabaseConnector();

    public void sendQuery(String nameOfTable, String query) {
        databaseConnector.openDBConnection();
        databaseConnector.createQuestionTypeTable(nameOfTable);
        databaseConnector.executeStatement(query);
        databaseConnector.closeConnection();
    }

    public List<DatabaseDTO> findRecord(String nameOfTable, String query) {
        List<DatabaseDTO> found = new LinkedList<>();
        databaseConnector.openDBConnection();
        // databaseConnector.createQuestionTypeTable(nameOfTable);
        found = databaseConnector.executeStatementForSearching(query);
        databaseConnector.closeConnection();

        return found;
    }

    private class DatabaseConnector {

        private Statement statement;
        private Connection connection;
        private PreparedStatement preparedStatement;
        private ResultSet resultSet;

        public Boolean openDBConnection() {
            connection = null;
            try {
                Class.forName(TYPE_OF_DATABASE);
                try {
                    connection = DriverManager.getConnection(DB_TYPE_AND_DB_NAME);
                } catch (SQLException ex) {
                    System.err.println(MISSING_JDBC_DRIVER);
                    ex.printStackTrace();
                    return false;
                }
            } catch (ClassNotFoundException ex) {
                System.err.println("Error occured durning connect with db");
                System.out.println(ex);
                return false;
            }

            try {
                connection = DriverManager.getConnection(buildDBConfig());
                statement = connection.createStatement();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            return true;
        }

        private void createQuestionTypeTable(String nameOfTable) {

            statement = null;

            try {
                statement = connection.createStatement();
                String query = CREATE_TABLE + nameOfTable + COLUMNS_NAME;
                statement.executeUpdate(query);
                statement.close();

            } catch (SQLException ex) {
                Logger.getLogger(DatabaseControler.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Can not create table" + ex.getMessage());
            }

        }

        private String buildDBConfig() {
            Path currentRelativePath = Paths.get("");
            StringBuilder builder = new StringBuilder();
            builder.append(JDBC);
            builder.append(new FileIO().generateDBDirectory(currentRelativePath.toAbsolutePath().toString(), DB_DIR));
            builder.append(SEPARATOR);
            builder.append(DB_NAME);
            return builder.toString();
        }

        private void executeStatement(String query) {
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.execute();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }

        private void closeConnection() {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }

        private List<DatabaseDTO> executeStatementForSearching(String query) {
            List<DatabaseDTO> found = new LinkedList<>();

            try {
                resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    DatabaseDTO recievedFromDB = new DatabaseDTO();
                    recievedFromDB.setId(resultSet.getInt(DBColumnName.ID.getValue()));
                    recievedFromDB.setQuestion(resultSet.getString(DBColumnName.QUESTION.getValue()));
                    recievedFromDB.setAnswer(resultSet.getString(DBColumnName.ANSWER.getValue()));
                    recievedFromDB.setNumberOfSlide(resultSet.getString(DBColumnName.NUMBER_OF_SLIDE.getValue()));
                    recievedFromDB.setComments(resultSet.getString(DBColumnName.COMMENTS.getValue()));

                    found.add(recievedFromDB);

                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            return found;
        }
    }
}
