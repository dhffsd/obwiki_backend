package com.gec.obwiki.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@ApiModel("测试sayhello参数类")
@Data
public class DemoReq {
    @NotBlank
    @Pattern(regexp = "^[\\u4e00-\\u9fa5]{2,15}$", message = "姓名应为2-15中文")
    @ApiModelProperty("姓名：2-15中文")
    private String name;

    @NotBlank(message = "手机号不能为空！")
    @Pattern(regexp = "^1[3|4|5|6|7|8|9][0-9]{9}$", message = "手机号格式不正确！")
    private String phone;

    @NotBlank(message = "密码必须填写")
    @Length(min = 6, max = 20, message = "密码必须是6-20位字符")
    private String password;
} 