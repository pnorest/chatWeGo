package cn;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @ClassName ChatTao
 * @Description TODO
 * @Author Pnorest
 * @Date 2019/11/7 14:22
 * @Version 1.0
 **/
@ServletComponentScan
@SpringBootApplication
public class ChatTao {
    public static  void  main(String args[]){
        SpringApplication.run(ChatTao.class,args);
    }
}
