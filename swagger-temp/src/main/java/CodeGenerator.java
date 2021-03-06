import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.net.URL;

public class CodeGenerator {
    //代码生成器
    private static AutoGenerator mpg = new AutoGenerator();

    //全局配置
    private static GlobalConfig gc = new GlobalConfig();

    //作者、包名、去除表前缀
    private static final String author = "mp";
    private static final String package_name = "mybatisplus";
    private static final String TABLE_PREFIX = "tb_";

    //数据库
    private static final String url = "jdbc:mysql://localhost:3306/crm?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String driverName = "com.mysql.cj.jdbc.Driver";
    private static final String userName = "root";
    private static final String password = "root";
    private static final String table_name = "sys_user";


    public static void main(String[] args){

        // 数据源配置
        setDataSource();

        // 全局配置
        setGlobalConfig();

        // 策略配置
        setStrategy();

        //执行
        mpg.execute();
    }

    private static void setStrategy() {
        StrategyConfig strategy = new StrategyConfig();

        // 类名：Tb_userController -> TbUserController
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 属性名：start_time -> startTime
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // lombok 代替 setter/getter等方法
        strategy.setEntityLombokModel(true);
        // 设置Controller为RestController
        strategy.setRestControllerStyle(true);
        //由数据库该表生成
        strategy.setInclude(table_name);
        //去除表前缀
        strategy.setTablePrefix(TABLE_PREFIX);
        mpg.setStrategy(strategy);
    }

    private static void setGlobalConfig() {
        URL urlPath = Thread.currentThread().getContextClassLoader().getResource("");
      //  String projectPath = Objects.requireNonNull(urlPath).getPath().replace("target/classes", "src/main/java");
        String projectPath=System.getProperty("user.dir");
        gc.setOutputDir(projectPath+"/swagger-temp/src/main/java");//代码生成位置
        gc.setFileOverride(false);//覆盖已有文件
        gc.setAuthor(author);
        gc.setServiceName("%service");
        gc.setSwagger2(true);
        gc.setIdType(IdType.AUTO);//主键ID类型
        gc.setDateType(DateType.ONLY_DATE);//设置时间类型为Date
        mpg.setGlobalConfig(gc);


        PackageConfig pc = new PackageConfig();// 包配置
        pc.setModuleName("test");
        pc.setParent("com.mp.t");

        mpg.setPackageInfo(pc);
    }

    private static void setDataSource() {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        dsc.setDriverName(driverName);
        dsc.setUsername(userName);
        dsc.setPassword(password);
        mpg.setDataSource(dsc);
    }



}
