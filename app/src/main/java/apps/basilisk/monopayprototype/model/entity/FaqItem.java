package apps.basilisk.monopayprototype.model.entity;

public class FaqItem {
    private String language;
    private String question;
    private String answer;
    private boolean expanded;
    private String constraint;


    public FaqItem(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.constraint = "";
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public String getConstraint() {
        return constraint;
    }

    public void setConstraint(String constraint) {
        this.constraint = constraint;
    }

    @Override
    public String toString() {
        return "FaqItem{" +
                "language='" + language + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", expanded=" + expanded +
                ", constraint='" + constraint + '\'' +
                '}';
    }
}
