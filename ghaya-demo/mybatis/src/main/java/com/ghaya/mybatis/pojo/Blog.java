package com.ghaya.mybatis.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Blog {
    private String Id;
    private String Comments;
    private String Body;
    private User Author;
    private String Title;
    private String Labels;


//    protected String getTitle() {
//        return Title;
//    }
}
