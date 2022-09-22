package book.manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.ibatis.annotations.ConstructorArgs;

@Data
@AllArgsConstructor
public class GlobalStat {
    private int userCount;
    private int bookCount;
    private int borrowCount;


}
