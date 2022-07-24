package com.qingge.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@TableName("order_info")
@ApiModel(value = "order_info对象", description = "")
@ToString
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("访问的公司")
    private String visitCompany;

    @ApiModelProperty("管理員")
    private String visitAdmin;

    @ApiModelProperty("名稱")
    private String name;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("公司名称")
    private String company;

    @ApiModelProperty("来访目的")
    private String purpose;

    @ApiModelProperty("隨行人")
    private String people;

    @ApiModelProperty("随行物品")
    private String article;

    @ApiModelProperty("车辆信息")
    private String car;

    @ApiModelProperty("访问时间")
    private Date time;

    @ApiModelProperty("预约单状态(1.暂存待提交2.提交待审核3.审核通过4.审核未通过5.已过期)")
    private String status;

    @ApiModelProperty("是否草稿")
    private String ifDraft;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVisitCompany() {
        return visitCompany;
    }

    public void setVisitCompany(String visitCompany) {
        this.visitCompany = visitCompany;
    }

    public String getVisitAdmin() {
        return visitAdmin;
    }

    public void setVisitAdmin(String visitAdmin) {
        this.visitAdmin = visitAdmin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIfDraft() {
        return ifDraft;
    }

    public void setIfDraft(String ifDraft) {
        this.ifDraft = ifDraft;
    }
}
