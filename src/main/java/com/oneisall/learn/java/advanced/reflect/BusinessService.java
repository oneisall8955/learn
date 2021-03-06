package com.oneisall.learn.java.advanced.reflect;

import com.oneisall.learn.java.common.Result;

/**
 * 某业务,有多个实现类,且都需要进行相同的业务操作,只是业务逻辑不同
 *
 * @author : oneisall
 * @version : v1 2019/4/25 10:07
 */
public interface BusinessService {

    /** 默认方法
     * @param methodName 方法名称
     * @param dto 业务DTO
     * @return 结果集
     */
    default Result<BusinessDto> info(String methodName, BusinessDto dto){
        System.out.println(String.format("method:%s,name:%s,age:%s",methodName,dto.getName(),dto.getAge()));
        return Result.success("成功!",dto);
    }

    /** 业务操作1
     * @param dto 业务DTO
     * @return 返回操作结果
     */
    @SuppressWarnings("all")
    Result<BusinessDto> operate1(BusinessDto dto);

    /** 业务操作2
     * @param dto 业务DTO
     * @return 返回操作结果
     */
    @SuppressWarnings("all")
    Result<BusinessDto> operate2(BusinessDto dto);

    /** 业务操作3
     * @param dto 业务DTO
     * @return 返回操作结果
     */
    @SuppressWarnings("all")
    Result<BusinessDto> operate3(BusinessDto dto);

}
