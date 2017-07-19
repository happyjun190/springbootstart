package com.ysb.service.test.impl;

import com.ysb.dao.test.TestDAO;
import com.ysb.model.test.TabTest;
import com.ysb.service.test.ITestService;
import com.ysb.web.model.in.BaseInModel;
import com.ysb.web.result.JsonResultOut;
import com.ysb.web.result.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wushenjun on 2017/7/19.
 */
@Service
public class TestService implements ITestService {

    @Autowired
    private TestDAO testDAO;

    @Override
    public JsonResultOut<List<TabTest>> getAllTestData(BaseInModel inModel) {
        return new JsonResultOut(ReturnCode.SUCCESS, "获取测试数据成功", testDAO.getAllTestData());
    }
}
