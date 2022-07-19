package org.upc.oj.judger.po;

public class TestIOFile {
    private String input;
    private String output;
    private  String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String innput) {
        input = innput;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return "TestIOFile{" +
                "input='" + input + '\'' +
                ", output='" + output + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
