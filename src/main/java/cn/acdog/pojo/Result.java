package cn.acdog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result <T>{
    private Integer code;
    private String message;
    private T data;

    //快速返回操作成功相应结果（带响应数据）
    public static <E> Result<E> success(E data){
        return new Result<>(0,"操作成功",data);
    }
    //快速返回操作成功相应结果（不带响应数据）
    public static Result success(){
        return new Result<>(0,"操作成功",null);
    }
    //快速返回操作失败相应结果（带响应数据）
    public static  Result error(String message){
        return new Result<>(1,message,null);
    }
}
