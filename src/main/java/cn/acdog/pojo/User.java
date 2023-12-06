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
    @NotNull
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    @NotNull
    @Pattern(regexp = "^\\S{1,16}$")
    private String nickname;
    private Integer role;
    @NotNull
    @Email
    private String email;
    @URL
    private String avatar;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer vip;
    private Integer status;
}
