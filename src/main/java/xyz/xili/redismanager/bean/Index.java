package xyz.xili.redismanager.bean;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Index {
    private Integer id;
    private String text;
    private String pattern;
}
