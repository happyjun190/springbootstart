package com.ysb.web.controller.test;

import com.ysb.service.test.ITestService;
import com.ysb.web.model.in.BaseInModel;
import com.ysb.web.result.JsonResultOut;
import com.ysb.web.result.ReturnCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wushenjun on 2017/7/19.
 */
@Api(tags = "TestController")
@RestController
@RequestMapping("/servlet/test")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private ITestService testService;

    @RequestMapping(value = "/getAllTestData/{version}", method = RequestMethod.GET)
    @ApiOperation(value = "example:获取测试数据")
    public JsonResultOut getAllTestData(HttpServletRequest request,
                                           @ApiParam(value = "版本号:v100", required = true) @PathVariable("version") String version,
                                           @ApiParam(value = "接口所需基本信息", required = true) @RequestBody BaseInModel inModel) {
        JsonResultOut result = null;
        try {
            switch (version) {
                case "v100":
                    result = testService.getAllTestData(inModel);
                    break;
                default:
                    result = new JsonResultOut(ReturnCode.PARAMSERROR, "无效的URL版本号！");
                    break;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new JsonResultOut(ReturnCode.EXCEPTION, "获取数据异常！");
        }
        return result;
    }
}
