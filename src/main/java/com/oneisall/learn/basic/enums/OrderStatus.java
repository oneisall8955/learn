package com.oneisall.learn.basic.enums;

import com.oneisall.learn.common.CommonResult;

import java.util.*;

/**
 * 退废订单状态
 *
 * @author oneisall
 */
public enum OrderStatus implements AllowOperate<UserType, Operation> {

    /**
     * 提交退废申请后第一个状态，可退的录入退票费、手续费审核通过，
     * 无法退废的可进行暂不能操作
     */
    WAIT_AUDIT("待审核"),
    /**
     * 暂不能操作后的状态，确认不能退废则取消退废，确认可退则上传凭证继续退废
     */
    TEMP_CANNOT_REFUND("暂不能退废"),
    /**
     * 取消退废后的状态，为终结状态，不可进行任何操作
     */
    CANCELED("已取消"),
    /**
     * 审核通过后的状态，待实际收到退款后，操作退款（也可先垫付退款）
     */
    WAIT_REFUND("待退款"),
    /**
     * 操作退款后的状态，为终结状态，特殊情况下可由运营商发起补退款
     */
    REFUNDED("已退款");

    public final String text;

    /**
     * private 不允许外部操作
     */
    private final Map<UserType, Set<Operation>> allowOperateSetting;

    OrderStatus(String text) {
        this.text = text;
        this.allowOperateSetting = new HashMap<>();
    }

    // 初始化
    static {
        // WAIT_AUDIT 待审核
        WAIT_AUDIT.allowOperateSetting.put(UserType.PURCHASER, null);//采购商,无
        WAIT_AUDIT.allowOperateSetting.put(UserType.OPERATION, new HashSet<>(Arrays.asList(Operation.AUDIT_PASS, Operation.TEMP_CANNOT_REFUND)));//运营商,审核通过、暂不能退废
        WAIT_AUDIT.allowOperateSetting.put(UserType.SUPPLIER, new HashSet<>(Arrays.asList(Operation.AUDIT_PASS, Operation.TEMP_CANNOT_REFUND)));//供应商,审核通过、暂不能退废

        // TEMP_CANNOT_REFUND 暂不能退废
        TEMP_CANNOT_REFUND.allowOperateSetting.put(UserType.PURCHASER, new HashSet<>(Arrays.asList(Operation.CONTINUE_REFUND, Operation.CANCEL_REFUND)));//采购商,继续退废、取消退废
        TEMP_CANNOT_REFUND.allowOperateSetting.put(UserType.OPERATION, new HashSet<>(Arrays.asList(Operation.CONTINUE_REFUND, Operation.CANCEL_REFUND)));//运营商,继续退废、取消退废
        TEMP_CANNOT_REFUND.allowOperateSetting.put(UserType.SUPPLIER, null);//供应商,无

        // CANCELED 已取消
        CANCELED.allowOperateSetting.put(UserType.PURCHASER, null);//采购商,无
        CANCELED.allowOperateSetting.put(UserType.OPERATION, null);//运营商,无
        CANCELED.allowOperateSetting.put(UserType.SUPPLIER, null);//供应商,无

        // CONTINUE_REFUND 待退款
        WAIT_REFUND.allowOperateSetting.put(UserType.PURCHASER, null);//采购商,无
        WAIT_REFUND.allowOperateSetting.put(UserType.OPERATION, new HashSet<>(Collections.singletonList(Operation.REFUND)));//运营商,退款
        WAIT_REFUND.allowOperateSetting.put(UserType.SUPPLIER, new HashSet<>(Collections.singletonList(Operation.REFUND)));//供应商,退款

        // REFUNDED 已退款
        REFUNDED.allowOperateSetting.put(UserType.PURCHASER, null);//采购商,无
        REFUNDED.allowOperateSetting.put(UserType.OPERATION, new HashSet<>(Collections.singletonList(Operation.APPLY_ADD_REFUND)));//运营商,申请补退款
        REFUNDED.allowOperateSetting.put(UserType.SUPPLIER, new HashSet<>(Collections.singletonList(Operation.CONFIRM_ADD_REFUND)));//供应商,确认补退款

    }


    /**
     * @param userType  用户
     * @param operation 操作
     * @return
     */
    @Override
    public CommonResult allowOperate(UserType userType, Operation operation) {
        if (operation == null || userType == null) {
            return CommonResult.failed("异常操作,操作或操作者无效,operation:[" + operation + "],userType:[" + userType + "]");
        }
        Set<Operation> allowSet = allowOperateSetting.get(userType);
        if (allowSet == null || allowSet.isEmpty()) {
            return CommonResult.failed("禁止操作,订单状态:[" + text + "],角色:[" + userType.text + "],操作:[" + operation.text + "]");
        }
        boolean allow = allowSet.contains(operation);
        CommonResult success = CommonResult.success("允许操作,订单状态:[" + text + "],角色:[" + userType.text + "],操作:[" + operation.text + "]");
        CommonResult failed = CommonResult.failed("禁止操作,订单状态:[" + text + "],角色:[" + userType.text + "],操作:[" + operation.text + "]");
        return allow ? success : failed;
    }
}
