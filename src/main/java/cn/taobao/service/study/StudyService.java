package cn.taobao.service.study;

import cn.taobao.entity.study.Study;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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

//    private static final ComboPooledDataSource ds =  new ComboPooledDataSource();
//    public static DataSource getDataSource(){
//        try{
//        ds.setUser("root");
//        ds.setPassword("123456");
//        ds.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/chatwego?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC&autoReconnect=true");
//        ds.setDriverClass("com.mysql.jdbc.Driver");
////        ds.setInitialPoolSize();
////        ds.setAcquireIncrement();
////        ds.setMinPoolSize();
////        ds.setMaxPoolSize();
////        ds.setMaxStatements();
////        ds.setMaxIdleTime();
////        ds.setIdleConnectionTestPeriod();
////        ds.setAcquireRetryAttempts();
//        return ds;
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//    public Connection getConnection() {
//        try {
//            return ds.getConnection();
//        } catch (Exception e) {
//            throw new RuntimeException("can not get sms database connection  ", e);
//        }
//    }


//    @Resource
//    private StudyMapper studyMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    JdbcTemplate jdbcTemplate=new JdbcTemplate();


    public List<Study> findStudyInfoList() {
        String sql = "select * from study";
        return (List<Study>) jdbcTemplate.query(sql, new RowMapper<Study>() {//jdbctemplate为空
            @Override
            public Study mapRow(ResultSet rs, int rowNum) throws SQLException {
                Study study = new Study();
                study.setId(rs.getInt("id"));
                study.setName(rs.getString("name"));
                study.setPass(rs.getString("pass"));
                study.setUrl(rs.getString("url"));
                study.setStatus(rs.getInt("status"));
                return study;
            }
        });
    }



    public List<Study> findStudyInfo(String id) {
        String sql = "select * from study where id="+id;
        return (List<Study>) jdbcTemplate.query(sql, new RowMapper<Study>() {
            @Override
            public Study mapRow(ResultSet rs, int rowNum) throws SQLException {
                Study study = new Study();
                study.setId(rs.getInt("id"));
                study.setName(rs.getString("name"));
                study.setPass(rs.getString("pass"));
                study.setUrl(rs.getString("url"));
                study.setStatus(rs.getInt("status"));
                return study;
            }
        });
    }
}
