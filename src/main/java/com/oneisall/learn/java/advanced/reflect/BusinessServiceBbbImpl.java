package com.oneisall.learn.java.advanced.reflect;

import com.oneisall.learn.java.common.Result;

/**
 * TODO :please describe it in one sentence
 *
 * @author oneisall
 * @version v1 2019/4/25 10:15
 */
@SuppressWarnings("all")
public class BusinessServiceBbbImpl implements BusinessService{

    @Override
    public Result<BusinessDto> operate1(BusinessDto dto) {
        dto.setName("Ccc-operate1");
        dto.setAge(21);
        return info("BusinessServiceCccImpl-operate1",dto);
    }

    @Override
    public Result<BusinessDto> operate2(BusinessDto dto) {
        dto.setName("Ccc-operate2");
        dto.setAge(22);
        return info("BusinessServiceCccImpl-operate2",dto);
    }

    @Override
    public Result<BusinessDto> operate3(BusinessDto dto) {
        dto.setName("Ccc-operate3");
        dto.setAge(23);
        return info("BusinessServiceCccImpl-operate3",dto);
    }

}
