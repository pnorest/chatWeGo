package cn.taobao.controller;

import cn.taobao.entity.study.Study;
import cn.taobao.service.study.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName StudyController
 * @Description TODO
 * @Author Pnorest
 * @Date 2019/11/14 14:47
 * @Version 1.0
 **/
@RestController
@RequestMapping("/chat")
public class StudyController {

    @Autowired
    private StudyService studyService;

    //代支付订单返回信息记录
    @RequestMapping("/findStudyInfo")
    public List<Study> findStudyInfo(){
        return studyService.findStudyInfo();
    }


}
