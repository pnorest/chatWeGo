package cn.taobao.mapper.study;

import cn.taobao.entity.study.Study;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudyMapper {
    List<Study> findStudyInfo();
}
