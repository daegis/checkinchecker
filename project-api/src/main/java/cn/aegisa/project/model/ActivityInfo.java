package cn.aegisa.project.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * ActivityInfo Entity.
 */
@Setter
@Getter
public class ActivityInfo implements Serializable {

    //列信息
    private Integer id;

    private String activityName;

    private LocalDateTime activityDate;

    private Integer dayCount;

    private Integer price;

    private LocalDateTime createTime;


}

