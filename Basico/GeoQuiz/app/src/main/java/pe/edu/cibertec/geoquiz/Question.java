package pe.edu.cibertec.geoquiz;

public class Question {
    private String name;
    private boolean response;

    public Question(String name, boolean response) {
        this.name = name;
        this.response = response;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }
}
