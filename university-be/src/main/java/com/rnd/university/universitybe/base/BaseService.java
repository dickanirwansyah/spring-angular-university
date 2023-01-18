package com.rnd.university.universitybe.base;

public interface BaseService<R extends BaseRequest, T extends BaseResponse>{
    T excute(R request);
}
