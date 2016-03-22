package database;

import javax.persistence.*;

/**
 * @author Jari Van Melckebeke
 */
@Entity
@Table(name = "questions")
public class Command {
    @Id
    @GeneratedValue
    @Column(name = "com_id")
    private int comId;

    @Column(name = "com_question")
    private String comQuestion;

    @Column(name = "com_class")
    private String comClass;

    @Column(name = "com_method")
    private String comMethod;

    public String getComMethod() {
        return comMethod;
    }

    public void setComMethod(String comMethod) {
        this.comMethod = comMethod;
    }

    public String getComClass() {
        return comClass;
    }

    public void setComClass(String comClass) {
        this.comClass = comClass;
    }

    public String getComQuestion() {
        return comQuestion;
    }

    public void setComQuestion(String comQuestion) {
        this.comQuestion = comQuestion;
    }
}
