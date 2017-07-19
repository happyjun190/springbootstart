package com.ysb.service.test;

import com.ysb.model.test.TabTest;
import com.ysb.web.model.in.BaseInModel;
import com.ysb.web.result.JsonResultOut;

import java.util.List;

/**
 * Created by wushenjun on 2017/7/19.
 */
public interface ITestService {

    /**
     * 获取所有测试数据
     * @param inModel
     * @return
     */
    JsonResultOut<List<TabTest>> getAllTestData(BaseInModel inModel);

}
