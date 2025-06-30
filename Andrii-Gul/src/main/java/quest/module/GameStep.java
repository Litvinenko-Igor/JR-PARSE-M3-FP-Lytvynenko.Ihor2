package quest.module;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class GameStep {
    private int id;
    private String text;
    private String imageUrl;
    private List<GameOption> options;

    private boolean terminal = false;

    @JsonProperty("isWin")
    private boolean isWin = false;
}
