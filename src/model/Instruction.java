package model;

public class Instruction {
    private String key;
    private boolean isActive;

    public Instruction(String key, boolean isActive) {
        this.key = key;
        this.isActive = isActive;
    }

    public Instruction() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}