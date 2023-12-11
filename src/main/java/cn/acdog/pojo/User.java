package cn.acdog.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private Integer role;
    private String email;
    private String avatar;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer vip;
    private Integer status;
}
