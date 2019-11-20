package cn.taobao.service.study;

import cn.taobao.entity.study.Study;
import cn.taobao.mapper.study.StudyMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName studyService
 * @Description TODO
 * @Author Pnorest
 * @Date 2019/11/14 14:49
 * @Version 1.0
 **/
@Service
public class StudyService {

    @Resource
    private StudyMapper studyMapper;


    public List<Study> findStudyInfo(String name) {
        return studyMapper.findStudyInfo(name);
    }

    public List<Study> findStudyInfoList() {
        return studyMapper.findStudyInfoList();
    }
}
