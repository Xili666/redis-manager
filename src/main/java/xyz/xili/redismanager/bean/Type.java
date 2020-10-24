package xyz.xili.redismanager.bean;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Type {
    private Integer id;
    private String redisType;
    private String javaType;
    private String pattern;
}
