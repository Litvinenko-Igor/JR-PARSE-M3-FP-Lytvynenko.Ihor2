package root.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Question.class, name = "question"),
        @JsonSubTypes.Type(value = Result.class, name = "result")
})
public abstract class Stage {
    public String id;
    public String text;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
