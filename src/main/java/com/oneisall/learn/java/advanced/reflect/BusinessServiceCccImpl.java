package com.oneisall.learn.java.advanced.reflect;

import com.oneisall.learn.java.common.Result;

/**
 * TODO :please describe it in one sentence
 *
 * @author oneisall
 * @version v1 2019/4/25 10:15
 */
@SuppressWarnings("all")
public class BusinessServiceCccImpl implements BusinessService{

    @Override
    public Result<BusinessDto> operate1(BusinessDto dto) {
        dto.setName("Bbb-operate1");
        dto.setAge(21);
        return info("BusinessServiceBbbImpl-operate1",dto);
    }

    @Override
    public Result<BusinessDto> operate2(BusinessDto dto) {
        dto.setName("Bbb-operate2");
        dto.setAge(22);
        return info("BusinessServiceBbbImpl-operate2",dto);
    }

    @Override
    public Result<BusinessDto> operate3(BusinessDto dto) {
        dto.setName("Bbb-operate3");
        dto.setAge(23);
        return info("BusinessServiceBbbImpl-operate3",dto);
    }

}
