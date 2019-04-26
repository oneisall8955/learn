package com.oneisall.learn.advanced.reflect;

import java.lang.reflect.InvocationTargetException;

/**
 * 动态调用方法
 *
 * @author oneisall
 * @version v1 2019/4/25 10:16
 */
public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        BusinessService service = new BusinessServiceAaaImpl();
        BusinessDto dto = new BusinessDto();
        dto.setName("TEST");
        dto.setAge(0);
        //,"operate4"
        String[] methodNames = {"operate1", "operate2", "operate3"};
        for (String methodName : methodNames) {
            service.getClass().getMethod(methodName, BusinessDto.class).invoke(service, dto);
        }
        System.out.println("--------");
        String[] serviceNames = {"BusinessServiceAaaImpl", "BusinessServiceBbbImpl", "BusinessServiceCccImpl"};
        for (String serviceName : serviceNames) {
            service = BusinessServiceFactory.instance().getBusinessService("com.oneisall.learn.basic.reflect." + serviceName);
            service.operate1(dto);
            service.operate2(dto);
            service.operate3(dto);
            System.out.println("--------");
        }
    }
}
