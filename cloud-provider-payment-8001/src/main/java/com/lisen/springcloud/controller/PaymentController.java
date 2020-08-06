package com.lisen.springcloud.controller;

import com.lisen.springcloud.entities.Payment;
import com.lisen.springcloud.service.PaymentService;
import com.lisen.springcloud.vo.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author lisen
 * @create 2020/8/5 18:45
 */
@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @RequestMapping(value = "/index")
    public HashMap index(){
        HashMap map = new HashMap();
        map.put("name","lisen");
        map.put("age",26);
        map.put("sex","老男人");
        return map;
    }

    /**
     * 添加信息
     * @param payment
     * @return
     */
    @PostMapping(value = "/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("插入结果："+result);
        if (result > 0){
            return new CommonResult(200, "插入成功", result);
        }else {
            return new CommonResult(444, "插入失败", null);
        }
    }

    @GetMapping(value = "/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment result = paymentService.getPaymentById(id);
        log.info("查询结果："+result);
        if (result != null){
            return new CommonResult(200, "查询成功", result);
        }else {
            return new CommonResult(444, "无此记录，查询ID："+id, null);
        }
    }
}
