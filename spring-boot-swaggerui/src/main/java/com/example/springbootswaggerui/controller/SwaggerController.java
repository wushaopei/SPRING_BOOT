package com.example.springbootswaggerui.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName SwaggerController
 * @Description TODO
 * @Author wushaopei
 * @Date 2019/7/26 10:23
 * @Version 1.0
 */
@Api(value = "测试",description = "测试 API", position = 100, protocols = "http")
@RestController
@RequestMapping(value = "/webcode")
public class SwaggerController {

    @ApiOperation(value = "测试", notes = "测试")
    @GetMapping("/exporInvoiceOrderXls")
    @ResponseBody
    public Object exporInvoiceOrderXls(HttpServletRequest request, HttpServletResponse response,
                                       @RequestParam(value="data")@ApiParam(value="data") String data) {


        Map<String, Object> map = new HashMap<>();

            map.put("data",data);
            map.put("state","0");
            map.put("message", "导出成功");
            return map;

    }

}
