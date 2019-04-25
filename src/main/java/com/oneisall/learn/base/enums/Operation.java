package com.oneisall.learn.base.enums;

public enum Operation {
    /**
     *审核通过
     */
    AUDIT_PASS("审核通过"),
    /**
     * 暂不能退废
     */
    TEMP_CANNOT_REFUND("暂不能退废"),
    /**
     * 继续退废
     */
    CONTINUE_REFUND("继续退废"),
    /**
     * 取消退废
     */
    CANCEL_REFUND("取消退废"),
    /**
     *退款
     */
    REFUND("退款"),
    /**
     *申请补退款
     */
    APPLY_ADD_REFUND("申请补退款"),
    /**
     *确认补退款
     */
    CONFIRM_ADD_REFUND("确认补退款");

    public final String text;

    Operation(String text) {
        this.text = text;
    }
}
