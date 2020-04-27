package com.mp.test.entity;

import com.mp.common.entity.Identifiable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 退款申请表
 */
@Data
public class PayRefundApply implements Identifiable<Long> {

    private Long id;

    /**
     * 渠道编码
     */
    private String wayId;

    /**
     * 授权订单号
     */
    private String authNo;

    /**
     * 订单号
     */
    private String outTradeNo;


    /**
     * 外部订单号
     */
    private String outOrderNo;

    /**
     * 订单金额
     */

    private String amount;

    /**
     * 红包金额
     */

    private String redPackAmount;
    /**
     * 提前结清金额
     */
    private  String beforeSettleAmount;

    /**
     * 发起时间
     */
    private String createTime;


    /**
     * 退款时间
     */
    private String refundTime;


    private  String dealTime;






    /**
     *加急状态  0 未加急  已加急
     */
    private Integer urgentState;


    public  enum  UrgentState{
        urgent(1,"已加急"),
        un_urgent(0,"未加急")
        ;
        private Integer code;
        private String desc;

        UrgentState(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
    public String getUrgentStateDesc(){
       if (UrgentState.urgent.getCode().equals(urgentState)){
           return  UrgentState.urgent.getDesc();
       }else {
           return  UrgentState.un_urgent.getDesc();
       }
    }




    /**
     *退款状态
     */
    private Integer refundState;

    public  enum  RefundState{
        wait(0, "等待支付"),
        closed(-1, "退款失败"),
        success(1, "退款成功"),
        refunding(2,"退款中"),
        cancel(3,"取消退款");

        private Integer code;
        private String desc;

        RefundState(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public   String getRefundStateDesc(){
      if (RefundState.success.getCode().equals(refundState)){
          return  RefundState.success.getDesc();
      }else  if (RefundState.closed.getCode().equals(refundState)){
          return  RefundState.closed.getDesc();
      }else if (RefundState.refunding.getCode().equals(refundState)){
          return  RefundState.refunding.getDesc();
      } else if (RefundState.cancel.getCode().equals(refundState)){
          return  RefundState.cancel.getDesc();
      }else {
          return  RefundState.wait.getDesc();
      }
    }



    /**
     *退款类型   0  boos回退截图方式  1 保证函方式
     */
    private Integer refundType;

    public  enum  RefundType{
        auto(0, "自动退款"),
        people(1, "人工退款");


        private Integer code;
        private String desc;

        RefundType(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }
        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public String getRefundTypeDesc(){
        if (RefundType.auto.getCode().equals(refundType)){
            return RefundType.auto.getDesc();
        }else {
            return RefundType.people.getDesc();
        }
    }




    /**
     * 红包状态
     */

    private Integer redPackState;

    public enum RedPackState {
        receive(1, "已领取"),
        un_receive(0, "未领取");

        private Integer code;
        private String desc;

        RedPackState(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public String getRedPacketStateDesc(){
        if (RedPackState.receive.getCode().equals(redPackState)){
            return RedPackState.receive.getDesc();
        } else {
            return RedPackState.un_receive.getDesc();
        }
    }




    @ApiModelProperty("驳回原因")
    /**
     *驳回原因
     */
    private String reason;



    @ApiModelProperty("支付宝转账流水单号")
    /**
     *支付宝转账流水单号
     */
    private String alipayTransferOrderNo;
    @ApiModelProperty("支付宝实名")
    /**
     *支付宝实名
     */
    private String alipayName;

    @ApiModelProperty("支付宝账号")
    /**
     *支付宝账号
     */
    private String alipayAccount;



    @ApiModelProperty("应退金额")
    /**
     *应退金额
     */
    private String transferAmount;

    private String uid;

    /**
     * 垫资回款状态
     */
    private Integer repaymentState;
    public enum RepaymentState {
        no_need_paid(0, "无需结清"),
        paid(3, "成功回款"),
        wait_success(4, "等待结清"),
        success(1, "结清贷款"),
        no_paid(2, "等待回款");

        private Integer code;
        private String desc;

        RepaymentState(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public String getRepaymentStateDesc() {
        if (RepaymentState.paid.getCode().equals(repaymentState)) {
            return RepaymentState.paid.getDesc();
        } else if (RepaymentState.no_need_paid.getCode().equals(repaymentState)) {
            return RepaymentState.no_need_paid.getDesc();
        } else if (RepaymentState.wait_success.getCode().equals(repaymentState)) {
            return RepaymentState.wait_success.getDesc();
        } else if (RepaymentState.success.getCode().equals(repaymentState)) {
            return RepaymentState.success.getDesc();
        } else if(RepaymentState.no_paid.getCode().equals(repaymentState)){
            return RepaymentState.no_paid.getDesc();
        }else {
            return null;
        }
    }
    /**
     * boos回退截图
     */
    private  String backFile;

    /**
     * 支付宝转账截图
     */
    private String transferFile;

}
