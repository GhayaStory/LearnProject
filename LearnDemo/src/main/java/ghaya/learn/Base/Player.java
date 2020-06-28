package ghaya.learn.Base;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 玩家
 */
@Data
public class Player {
    private String name;
    private Integer age;
    private BigDecimal score;
    private Integer type;
    private List<Equip> equipList;

    public Player() {
        this.equipList = new ArrayList<>();
    }
}
