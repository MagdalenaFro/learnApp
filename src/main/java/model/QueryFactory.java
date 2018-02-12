package model;

import controler.DatabaseControler;

import static model.Dictionary.*;

/**
 *
 * @author Magdalena
 */
public class QueryFactory {

    DatabaseControler databaseControler;

    public void addQuestionToDatabase(DatabaseDTO databaseDTO, String nameOfTable) {
        databaseControler = new DatabaseControler();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO ");
        stringBuilder.append(nameOfTable);
        stringBuilder.append(SPACE_WITH_OPEN_BRACKET);
        stringBuilder.append(DBColumnName.QUESTION.getValue());
        stringBuilder.append(COMA_WITH_SPACE);
        stringBuilder.append(DBColumnName.ANSWER.getValue());
        stringBuilder.append(COMA_WITH_SPACE);
        stringBuilder.append(DBColumnName.NUMBER_OF_SLIDE.getValue());
        stringBuilder.append(COMA_WITH_SPACE);
        stringBuilder.append(DBColumnName.COMMENTS.getValue());
        stringBuilder.append(CLOSE_BRACKET_WITH_SPACE);
        stringBuilder.append(" VALUES");
        stringBuilder.append(SPACE_WITH_OPEN_BRACKET);
        stringBuilder.append(SINGLE_QUOTA);
        stringBuilder.append(databaseDTO.getQuestion());
        stringBuilder.append(SINGLE_QUOTA);
        stringBuilder.append(COMA_WITH_SPACE);
        stringBuilder.append(SINGLE_QUOTA);
        stringBuilder.append(databaseDTO.getAnswer());
        stringBuilder.append(SINGLE_QUOTA);
        stringBuilder.append(COMA_WITH_SPACE);
        stringBuilder.append(SINGLE_QUOTA);
        stringBuilder.append(databaseDTO.getNumberOfSlide());
        stringBuilder.append(SINGLE_QUOTA);
        stringBuilder.append(COMA_WITH_SPACE);
        stringBuilder.append(SINGLE_QUOTA);
        stringBuilder.append(databaseDTO.getComments());
        stringBuilder.append(SINGLE_QUOTA);
        stringBuilder.append(CLOSE_BRACKET_WITH_SPACE);

        databaseControler.sendQuery(nameOfTable, stringBuilder.toString());

    }

    public String findRecordInDB(String nameOfTable, String ID) {
        StringBuilder builder = new StringBuilder();
        builder.append(SELECT_FROM);
        builder.append(nameOfTable);
        builder.append(WHERE_ID);
        builder.append(ID);
        builder.append(SINGLE_QUOTA);

        return builder.toString();

    }

    public String removeQuestion(String nameOfTable, String ID) {
        StringBuilder builder = new StringBuilder();
        builder.append(DELETE_FROM);
        builder.append(nameOfTable);
        builder.append(WHERE_ID);
        builder.append(ID);
        builder.append(SINGLE_QUOTA);

        return builder.toString();
    }

    public String findAllRecords() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SELECT_FROM);
        stringBuilder.append(QUESTIONS_DONE);

        return stringBuilder.toString();
    }

    public String generateQueryToUpdate(DatabaseDTO databaseDTO, String text) {

        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ");
        builder.append(QUESTIONS_TO_DO_TABLE);
        builder.append(" SET ");
        builder.append("COMMENTS = ");
        builder.append("'");
        builder.append(text);
        builder.append("'");
        builder.append(" WHERE ID = ");
        builder.append(databaseDTO.getId());

        System.err.println(builder.toString());

        return builder.toString();

    }

}
