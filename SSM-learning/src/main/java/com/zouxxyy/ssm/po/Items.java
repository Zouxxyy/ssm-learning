package com.zouxxyy.ssm.po;

import com.zouxxyy.ssm.controller.vaildation.ValidGroup1;
import com.zouxxyy.ssm.controller.vaildation.ValidGroup2;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class Items {
    private Integer id;

    // 校验名称在1-30个字符
    @Size(min = 1, max = 30, message = "{items.name.length.error}", groups = {ValidGroup1.class})
    private String name;

    private Float price;

    private String pic;

    // 非空校验
    @NotNull(message = "{items.createname.isNULL}", groups = {ValidGroup2.class})
    private Date createtime;

    private String detail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
}