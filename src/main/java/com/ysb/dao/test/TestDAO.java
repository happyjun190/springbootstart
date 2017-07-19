package com.ysb.dao.test;

import com.ysb.model.test.TabTest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by wushenjun on 2017/7/19.
 */
@Mapper
public interface TestDAO {

    /**
     * 获取所有的测试数据
     * @return
     */
    List<TabTest> getAllTestData();

}
