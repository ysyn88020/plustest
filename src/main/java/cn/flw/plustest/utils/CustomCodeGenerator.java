package cn.flw.plustest.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.util.*;

/**
 * <p>
 * CustomCodeGenerator代码生成器
 * </p>
 *
 * @author f_liwen
 * @since 2019-12-21
 */
public class CustomCodeGenerator {
    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
    public static void main(String[] args) {

        AutoGenerator mpg = new AutoGenerator();
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing

            }
        };

        //全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setFileOverride(true);
        gc.setSwagger2(true);
        gc.setActiveRecord(false);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        gc.setAuthor("f_liwen");
        mpg.setGlobalConfig(gc);

        // 自定义controller的代码模板
        String templatePath = "/templates/controller.java.ftl";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 + pc.getModuleName()
                String expand = projectPath + "/src/main/java/cn/flw/plustest/" + "controller";
                String entityFile = String.format((expand + File.separator + "%s" + ".java"), tableInfo.getControllerName());
                return entityFile;
            }
        });
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("entityUrl","cn.flw");
        cfg.setMap(map);
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        //自定义类型转换
        dsc.setTypeConvert(new MySqlTypeConvert(){
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                return super.processTypeConvert(globalConfig, fieldType);
            }
        });

        //数据库配置
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setUrl("jdbc:mysql://localhost:3306/screen");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix("T_");
        strategy.setEntityLombokModel(true);
        //设置时controller还是RestController注解
        strategy.setRestControllerStyle(true);
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        mpg.setStrategy(strategy);
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        //将默认生成Controller剔除，因自己自定义实现Controller
        templateConfig.setController(null);
        mpg.setTemplate(templateConfig);
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("cn.flw.plustest");
        mpg.setPackageInfo(pc);

        // 执行生成
        mpg.execute();
    }


}
