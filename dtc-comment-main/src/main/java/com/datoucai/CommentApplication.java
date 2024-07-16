package com.datoucai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动类-评论项目
 *
 */
@SpringBootApplication(scanBasePackages = "com.datoucai")
public class CommentApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(CommentApplication.class,args);
    }
}
