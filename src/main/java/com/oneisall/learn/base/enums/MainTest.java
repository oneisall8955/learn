package com.oneisall.learn.base.enums;

import com.oneisall.learn.common.CommonResult;
import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author oneisall
 */
public class MainTest {
    public static void main(String[] args) {
        OrderStatus[] statuses = OrderStatus.values();
        UserType[] userTypes = UserType.values();
        Operation[] operations = Operation.values();

        Map<OrderStatus, Map<UserType, List<Operation>>> allowMap = new LinkedHashMap<>();

        for (OrderStatus status : statuses) {
            Map<UserType, List<Operation>> allowUserTypeMap = new LinkedHashMap<>();
            for (UserType userType : userTypes) {
                List<Operation> allowOptionList = new ArrayList<>();
                for (Operation operation : operations) {
                    CommonResult result = status.allowOperate(userType, operation);
                    System.out.println(result.getMsg());
                    if (result.isStatus()) {
                        allowOptionList.add(operation);
                    }
                }
                allowUserTypeMap.put(userType, allowOptionList);
                System.out.println("----------------------------");
            }
            allowMap.put(status, allowUserTypeMap);
        }
        for (Map.Entry<OrderStatus, Map<UserType, List<Operation>>> entry : allowMap.entrySet()) {
            OrderStatus key = entry.getKey();
            Map<UserType, List<Operation>> value = entry.getValue();
            for (Map.Entry<UserType, List<Operation>> entry1 : value.entrySet()) {
                UserType key1 = entry1.getKey();
                if (key1 == UserType.ALL) {
                    continue;
                }
                List<Operation> value1 = entry1.getValue();
                String join = StringUtils.join(value1.stream().map(operation -> operation.text).collect(Collectors.toList()), ",");
                System.out.println("退废单状态:[" + key.text + "],角色[" + key1.text + "],允许操作:" + join);
            }
            System.out.println("-");
        }
    }
}
