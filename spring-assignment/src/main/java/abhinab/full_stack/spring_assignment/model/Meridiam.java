package abhinab.full_stack.spring_assignment.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Meridiam {
    AM("AM"),
    PM("PM");
    private final String meridiem;
    
    Meridiam(String meridiem){
        this.meridiem=meridiem;
    }

    @JsonValue
    public String getValue() {
        return this.name();
    }
}
