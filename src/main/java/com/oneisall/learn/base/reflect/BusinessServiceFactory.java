package com.oneisall.learn.base.reflect;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * factory to get BusinessService
 *
 * @author oneisall
 * @version v1 2019/4/25 10:46
 */
class BusinessServiceFactory {

    /**
     * singleton
     */
    private static final BusinessServiceFactory INSTANCE = new BusinessServiceFactory();
    private BusinessServiceFactory() {}
    static BusinessServiceFactory instance() {
        return INSTANCE;
    }

    /**
     * map container to store the existed BusinessService
     */
    private final Map<String, BusinessService> hTableMapperMap = new ConcurrentHashMap<>();


    BusinessService getBusinessService(String className) {
        BusinessService hTableMapper = hTableMapperMap.get(className);
        if(hTableMapper == null) {
            try {
                /*
                  check class.forName
                 */
                hTableMapper = (BusinessService) Class.forName(className).newInstance();
                /*
                  cache into the map
                 */
                hTableMapperMap.put(className, hTableMapper);
            } catch (Exception e) {
                hTableMapper = null;
            }
        }
        return hTableMapper;
    }

}
