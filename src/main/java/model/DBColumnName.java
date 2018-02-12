package model;

/**
 *
 * @author Magdalena
 */
public enum DBColumnName {

    ID("ID"),
    QUESTION("QUESTION"),
    ANSWER("ANSWER"),
    NUMBER_OF_SLIDE("NUMBER_OF_SLIDE"),
    COMMENTS("COMMENTS");

    private String value;

    private DBColumnName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
