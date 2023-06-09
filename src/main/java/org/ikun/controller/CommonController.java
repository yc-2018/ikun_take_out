//仰晨study 创建时间2023/6/10 0:55 星期六
package org.ikun.controller;

import lombok.extern.slf4j.Slf4j;
import org.ikun.common.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件的上传和下载
 */
@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {
    @Value("${reggit.path}")
    private String basePath;

    /**
     * 文件上传
     * @param file 是一个临时文件，需要转存到指定位置，否则本次请求完成后临时文件会删除
     */
    @PostMapping("/upload")
    public R<String> upLoad(MultipartFile file) throws IOException {
        //原始文件名
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));  //后缀名

        log.info("文件上传:"+ originalFilename);

        //UUID生成文件名，防止文件被覆盖
        String fileName = UUID.randomUUID() + suffix;

        //判断目录是否存在
        File dir = new File(basePath);
        if (!dir.exists()) {
            //不存在，要创建
            dir.mkdirs();
        }

        file.transferTo(new File( basePath + fileName));

        return R.success(fileName);
    }

    /**
     * 文件下载
     * @param name 文件名
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) throws IOException {
        //通过输入流读取文件内容
        FileInputStream fileInputStream = new FileInputStream(basePath + name);

        //通过输出流把文件响应给浏览器，在浏览器展示
        ServletOutputStream outputStream = response.getOutputStream();

        response.setContentType("image/jpeg");

        int len;
        byte[] bytes = new byte[1024];
        while ((len = fileInputStream.read(bytes)) != -1) {
            outputStream.write(bytes,0,len);
        }

        //关闭资源
        fileInputStream.close();
        outputStream.close();
    }

}
