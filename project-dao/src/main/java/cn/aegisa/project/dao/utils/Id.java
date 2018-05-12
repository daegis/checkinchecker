package cn.aegisa.project.dao.utils;

import lombok.Data;

/**
 * @author xiang.lv@hnari.com
 * @date Create in 18:09 2017/11/13
 * @description 查询符合条件的表的最大id和最小id
 **/
@Data
public class Id implements java.io.Serializable {
    private static final long serialVersionUID = -8428061463671815363L;

    private Integer minId;

    private Integer maxId;
    public static Id empty() {
        Id id = new Id();
        id.setMinId(0);
        id.setMaxId(0);
        return id;
    }

}
