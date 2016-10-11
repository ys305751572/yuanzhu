package com.smallchill.api.common.model;

import com.smallchill.core.annotation.BindID;
import com.smallchill.core.base.model.BaseModel;
import org.beetl.sql.core.annotatoin.Table;

/**
 * 被锁IP
 * Created by yesong on 2016/9/27.
 */
@Table(name = "tb_iplimt")
@BindID(name = "id")
public class LockIp extends BaseModel{

    private Integer id;
    private String ip;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
