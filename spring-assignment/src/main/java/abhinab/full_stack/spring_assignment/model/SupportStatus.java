package abhinab.full_stack.spring_assignment.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SupportStatus {
    RESOLVED("Support resolved the Query"),
    NOT_RESOLVED("Support yet to resolve");
    private final String status;
    
    SupportStatus(String status){
        this.status=status;
    }

    @JsonValue
    public String getValue() {
        return this.name();
    }
}
