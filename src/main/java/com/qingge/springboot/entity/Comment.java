package com.qingge.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author 青哥哥
 * @since 2022-03-22
 */
@Getter
@Setter
  @TableName("t_comment")
@ApiModel(value = "Comment对象", description = "")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public Integer getPid() {
    return pid;
  }

  public void setPid(Integer pid) {
    this.pid = pid;
  }

  public String getpNickname() {
    return pNickname;
  }

  public void setpNickname(String pNickname) {
    this.pNickname = pNickname;
  }

  public Integer getpUserId() {
    return pUserId;
  }

  public void setpUserId(Integer pUserId) {
    this.pUserId = pUserId;
  }

  public Integer getOriginId() {
    return originId;
  }

  public void setOriginId(Integer originId) {
    this.originId = originId;
  }

  public Integer getArticleId() {
    return articleId;
  }

  public void setArticleId(Integer articleId) {
    this.articleId = articleId;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }

  public List<Comment> getChildren() {
    return children;
  }

  public void setChildren(List<Comment> children) {
    this.children = children;
  }

  @ApiModelProperty("内容")
      private String content;

      @ApiModelProperty("评论人id")
      private Integer userId;

      @ApiModelProperty("评论时间")
      private String time;

      @ApiModelProperty("父id")
      private Integer pid;
      @TableField(exist = false)
      private String pNickname;  // 父节点的用户昵称
      @TableField(exist = false)
      private Integer pUserId;  // 父节点的用户id

      @ApiModelProperty("最上级评论id")
      private Integer originId;

      @ApiModelProperty("关联文章的id")
      private Integer articleId;

      @TableField(exist = false)
      private String nickname;
      @TableField(exist = false)
      private String avatarUrl;

      @TableField(exist = false)
      private List<Comment> children;


}
