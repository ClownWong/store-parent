package cn.itsource.controller;

import cn.itsource.util.VelocityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaticPageController {

    /**
     * 公共的接口-使用模板技术静态化页面

     *            数据
     * @param templatePath
     *            模板文件的路径
     * @param targetPath
     *            文件的输出路径
     */
    @PostMapping("/page")
    public void generateStaticPage(
            @RequestParam("templatePath") String templatePath,
            @RequestParam("targetPath")String targetPath,
            @RequestBody Object model){
        VelocityUtils.staticByTemplate(model,templatePath,targetPath);
    }


}