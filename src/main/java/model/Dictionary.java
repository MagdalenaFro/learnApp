/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Magdalena
 */
public class Dictionary {

    public static final String DB_DIR = "DATABASE";
    public static final String SEPARATOR = "//";
    public static final String CREATED_DB_DIRECTORY = "Database directory created successfully";
    public static final String CREATED_DB_DIRECTORY_FAILURE = "Database directory created - FAILURE";
    public static final String DONE_QUESTION_EXPORT = "done_question";
    public static final String FILE_EXTENTION = ".xlsx";

    //DatabaseControler fields
    public static final String TYPE_OF_DATABASE = "org.sqlite.JDBC";
    public static final String DB_TYPE_AND_DB_NAME = "jdbc:sqlite: data.db";
    public static final String MISSING_JDBC_DRIVER = "Error occured durning connect with db";
    public static final String JDBC = "jdbc:sqlite:";
    public static final String DB_NAME = "mutual.db";
    //query builder
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS ";
    public static final String COLUMNS_NAME = "(ID INTEGER PRIMARY KEY AUTOINCREMENT, QUESTION VARCHAR(255), ANSWER VARCHAR(255), NUMBER_OF_SLIDE CHAR(50), COMMENTS VARCHAR(255))";
    public static final String SELECT_FROM = "SELECT * FROM ";
    public static final String WHERE_ID = " WHERE ID='";
    public static final String DELETE_FROM = "DELETE FROM ";

    //QueryFactory
    public static final String SPACE_WITH_OPEN_BRACKET = " (";
    public static final String CLOSE_BRACKET_WITH_SPACE = ") ";
    public static final String COMA_WITH_SPACE = ", ";
    public static final String QUESTIONS_TO_DO_TABLE = "QUESTIONS_TO_DO";
    public static final String SINGLE_QUOTA = "'";
    public static final String QUESTIONS_DONE = "QUESTION_DONE";
}
