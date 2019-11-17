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


    @RequestMapping("/findStudyInfoList")
    public List<Study> findStudyInfoList(){
        return studyService.findStudyInfoList();
    }


    //代支付订单返回信息记录
    @RequestMapping("/findStudyInfo")
    public List<Study> findStudyInfo(String id){
        return studyService.findStudyInfo(id);
    }

//    [
//    {
//        "id": 2,
//            "name": "普通话资料",
//            "pass": "iakt",
//            "url": "https://pan.baidu.com/s/1mOoo9LfCaBZ_DBR0UGv_QA",
//            "status": 0
//    },
//    {
//        "id": 3,
//            "name": "四级资料",
//            "pass": "79n3",
//            "url": "https://pan.baidu.com/s/1FmBbQHYiY0Cxbr8HgVxJJg ",
//            "status": 0
//    },
//    {
//        "id": 4,
//            "name": "六级资料",
//            "pass": "a5a2",
//            "url": "https://pan.baidu.com/s/1dwQQjIsIohz2FRJx63p65A",
//            "status": 0
//    },
//    {
//        "id": 5,
//            "name": "教师资格证小学",
//            "pass": "6Z32",
//            "url": "https://pan.baidu.com/s/1BlWT_w9mx5AeXvFey75azA",
//            "status": 0
//    },
//    {
//        "id": 6,
//            "name": "教师资格证中学",
//            "pass": "BHEY",
//            "url": "https://pan.baidu.com/s/1QYBirVytcIkIArtjNgpROQ",
//            "status": 0
//    },
//    {
//        "id": 7,
//            "name": "计算机二级",
//            "pass": "52pj",
//            "url": "https://pan.baidu.com/s/17yVH8xcOJtCDyOaWlC0OeQ",
//            "status": 0
//    },
//    {
//        "id": 8,
//            "name": "会计初级",
//            "pass": null,
//            "url": "http://www.cuipixiong.com/forum.php?mod=viewthread&tid=249",
//            "status": 0
//    }
//]

}
