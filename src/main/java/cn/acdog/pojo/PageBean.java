package cn.acdog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageBean <T> {
    private Long total; // 总记录数
    private List<T> items; // 每页显示的数据
}
