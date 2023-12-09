package cn.acdog.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetJob {
    private Integer id; // id
    @NotNull
    private String job;// 岗位
    @NotNull
    private String address;// 意向地址
    @NotNull
    private Integer payMin; // 最低薪资
    @NotNull
    private Integer payMax;// 最高薪资
    @NotNull
    private String jobCategory;// 岗位类别
    private Integer createUser;// 创建用户
    private String condition; // 对公司要求的条件
    private Integer state; // 审核不通过是0，通过是1，待审核是2
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;// 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;// 修改时间
}