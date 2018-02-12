/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.util.List;
import java.util.Random;
import model.DatabaseDTO;
import model.Dictionary;
import model.QueryFactory;

/**
 *
 * @author Magdalena
 */
public class DataFinder {

    private QueryFactory queryFactory;
    private DatabaseControler databaseControler;

    public DatabaseDTO randomRecord(String nameOfTable) {
        queryFactory = new QueryFactory();
        databaseControler = new DatabaseControler();
        try {
            List<DatabaseDTO> found;
            do {
                String query = queryFactory.findRecordInDB(nameOfTable, generateID());
                found = databaseControler.findRecord(nameOfTable, query);
            } while (found.isEmpty());

            return found.get(0);
        } catch (Exception ex) {
            return null;
        }

    }

    private String generateID() {
        return String.valueOf(new Random().nextInt(200));
    }

    public void moveToDoneTable(DatabaseDTO databaseDTO) {
        //add record to doneQuestion table
        queryFactory = new QueryFactory();
        queryFactory.addQuestionToDatabase(databaseDTO, Dictionary.QUESTIONS_DONE);

        //remoce record from questionToDo table
        String query = queryFactory.removeQuestion(Dictionary.QUESTIONS_TO_DO_TABLE, databaseDTO.getId().toString());
        databaseControler = new DatabaseControler();
        databaseControler.sendQuery(Dictionary.QUESTIONS_TO_DO_TABLE, query);
    }

    public void makeUpdateOfComment(DatabaseDTO databaseDTO, String text) {

        queryFactory = new QueryFactory();

        databaseControler = new DatabaseControler();
        databaseControler.sendQuery(Dictionary.QUESTIONS_TO_DO_TABLE, queryFactory.generateQueryToUpdate(databaseDTO, text));
    }

}
