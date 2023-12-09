package cn.acdog.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostJob {
    private Integer id; // id
    private String job;// 岗位
    private String company;// 公司
    private String address;// 公司地址
    private Integer payMin; // 最低薪资
    private Integer payMax;// 最高薪资
    private String jobCategory;// 岗位类别
    private Integer createUser;// 创建用户
    private String condition; // 公司的条件
    private Integer state; // 审核不通过是0，通过是1，待审核是2
    private String img; // 公司图片
    private String info; // 公司简介
    private LocalDateTime createTime;// 创建时间
    private LocalDateTime updateTime;// 修改时间
}
