package com.oneisall.learn.base.reflect;

import com.oneisall.learn.base.genericity.CommonResult;

/**
 * TODO :please describe it in one sentence
 *
 * @author oneisall
 * @version v1 2019/4/25 10:15
 */
public class BusinessServiceAaaImpl implements BusinessService{

    @Override
    public CommonResult<BusinessDto> operate1(BusinessDto dto) {
        dto.setName("Aaa-operate1");
        dto.setAge(21);
        return info("BusinessServiceAaaImpl-operate1",dto);
    }

    @Override
    public CommonResult<BusinessDto> operate2(BusinessDto dto) {
        dto.setName("Aaa-operate2");
        dto.setAge(22);
        return info("BusinessServiceAaaImpl-operate2",dto);
    }

    @Override
    public CommonResult<BusinessDto> operate3(BusinessDto dto) {
        dto.setName("Aaa-operate3");
        dto.setAge(23);
        return info("BusinessServiceAaaImpl-operate3",dto);
    }

}
