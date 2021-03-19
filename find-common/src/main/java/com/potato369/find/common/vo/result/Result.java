package com.potato369.find.common.vo.result;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public abstract class Result {
	/**
     * <pre>
     * @JSONField returnCode：返回状态码，PAY_SUCCESS/PAY_FAIL 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断。
     * </pre>
     */
    @JSONField(name = "return_code")
    @JsonProperty(value = "return_code")
    public String returnCode;

    /**
     * <pre>
     * @JSONField returnMsg：返回信息，如非空，为错误原因，签名失败，参数格式校验错误。
     * </pre>
     */
    @JSONField(name = "return_msg")
    @JsonProperty(value = "return_msg")
    public String returnMsg;

    /**
     * <pre>
     * @JSONField resultCode：业务结果，微信返回的业务结果，PAY_SUCCESS/PAY_FAIL。
     * </pre>
     */
    @JSONField(name = "result_code")
    @JsonProperty(value = "return_code")
    public String resultCode;

    /**
     * <pre>
     * @JSONField errCode：错误代码，详细参见第6节错误列表。
     * </pre>
     */
    @JSONField(name = "err_code")
    @JsonProperty(value = "err_code")
    public String errCode;

    /**
     * <pre>
     * @JSONField errCodeDes：错误代码描述，错误返回的信息描述。
     * </pre>
     */
    @JSONField(name = "err_code_des")
    @JsonProperty(value = "err_code_des")
    public String errCodeDes;
}
