package com.mp.common.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author lvlu
 * @date 2019-05-15 17:05
 **/
public class CodeGenerator {


    private String currentPack;

    public CodeGenerator() {
        String packageName = PackageUtils.class.getPackage().getName();
        currentPack = packageName.substring(0, packageName.lastIndexOf("."));
    }

    public void generateAllByEntitys(String entityPack){
        List<String> classes = PackageUtils.getClassName(entityPack, false);
        String basePack = entityPack.substring(0, entityPack.lastIndexOf("."));
        for (String s : classes) {
            if (!s.contains("$")) {
                try {
                    Class classz = Class.forName(s);
                    generateDao(basePack,classz);
                    generateService(basePack,classz);
                    generateMybatisXml(basePack,classz);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void generateAllByEntityClass(String basePack,Class classz){
//        List<String> classes = PackageUtils.getClassName(entityPack, false);
//        String basePack = entityPack.substring(0, entityPack.lastIndexOf("."));
        generateDao(basePack,classz);
        generateService(basePack,classz);
        generateMybatisXml(basePack,classz);
    }

    public void generateEntity(String basePack, String...classNames) {
        if (classNames != null && classNames.length > 0) {
            for(int i=0;i<classNames.length;i++) {
                String className = classNames[i];
                generateEntity(basePack, className);
            }
        }
    }

    public void generateEntity(String basePack, String className) {
        String root = Thread.currentThread().getContextClassLoader().getResource("./").getPath().replace("/target/classes/", "/");
        String str = basePack.replaceAll("\\.", "\\\\");
        String baseDir = root.substring(1) + "\\src\\main\\java\\" + str;
        Map<String, Object> rootMap = new HashMap<>();
        rootMap.put("className", className);
        rootMap.put("user", System.getProperty("user.name"));
        rootMap.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        rootMap.put("package", basePack);
        rootMap.put("commonPack", currentPack);
        String filePath = baseDir + "\\entity";
        File dir = new File(filePath);
        //检查目录是否存在，不存在则创建
        if (!dir.exists()) {
            dir.mkdirs();
        }
        Configuration cfg = new Configuration();
        cfg.setClassForTemplateLoading(CodeGenerator.class, "/template/");
        Template temp = null;
        try {
            temp = cfg.getTemplate("entityTemplate.ftl");
            File docFile = new File(baseDir + "\\entity\\" + className + ".java");
            Writer docout = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            //输出文件
            temp.process(rootMap, docout);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }


    public void generateDao(String basePack, Class classz) {
        String root = Thread.currentThread().getContextClassLoader().getResource("./").getPath().replace("/target/classes/", "/");
        String str = basePack.replaceAll("\\.", "\\\\");
        String baseDir = root.substring(1) + "\\src\\main\\java\\" + str;
        Map<String, Object> rootMap = new HashMap<String, Object>();
        String className = classz.getSimpleName();
        rootMap.put("className", lowerCase(className.substring(0, 1)) + className.substring(1));
        rootMap.put("fullClassName", classz.getName());
        rootMap.put("user", System.getProperty("user.name"));
        rootMap.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        rootMap.put("package", basePack);
        rootMap.put("commonPack", currentPack);
        String filePath = baseDir + "\\mapper";
        File dir = new File(filePath);
        //检查目录是否存在，不存在则创建
        if (!dir.exists()) {
            dir.mkdirs();
        }
        Configuration cfg = new Configuration();
        cfg.setClassForTemplateLoading(CodeGenerator.class, "/template/");
        Template temp = null;
        try {
            temp = cfg.getTemplate("daoTemplate_zh_CN.ftl");
            File docFile = new File(baseDir + "\\mapper\\" + className + "Mapper.java");
            Writer docout = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            //输出文件
            temp.process(rootMap, docout);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

        System.out.println("==============文件生产成功===============");
    }

    public void generateMybatisXml(String basePack, Class classz) {
        String root = Thread.currentThread().getContextClassLoader().getResource("./").getPath().replace("/target/classes/", "/");
        ;
        String str = basePack.replaceAll("\\.", "\\\\");
        String baseDir = root.substring(1) + "\\src\\main\\java\\" + str;
        Map<String, Object> rootMap = new HashMap<String, Object>();
        String className = classz.getSimpleName();
        rootMap.put("className", lowerCase(className.substring(0, 1)) + className.substring(1));
        rootMap.put("fullClassName", classz.getName());
        rootMap.put("user", System.getProperty("user.name"));
        rootMap.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        rootMap.put("package", basePack);
        rootMap.put("model_columns", getColumns(classz));
        String filePath = baseDir + "\\mapper";
        File dir = new File(filePath);
        //检查目录是否存在，不存在则创建
        if (!dir.exists()) {
            dir.mkdirs();
        }
        Configuration cfg = new Configuration();
        cfg.setClassForTemplateLoading(CodeGenerator.class, "/template/");
        Template temp = null;
        try {
            temp = cfg.getTemplate("mapperxml.ftl");
            File docFile = new File(baseDir + "\\mapper\\" + className + "Mapper.xml");
            Writer docout = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            //输出文件
            temp.process(rootMap, docout);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

        System.out.println("==============文件生产成功===============");
    }

    public void generateCreateSqlForPackage(String basePack) {
        String root = Thread.currentThread().getContextClassLoader().getResource("./").getPath().replace("/target/classes/", "/");
        String baseDir = root.substring(1) + "\\src\\main\\resources\\sql\\";
        Map<String, Object> rootMap = new HashMap<String, Object>();
        List<String> classes = PackageUtils.getClassName(basePack, false);
        List<Map<String, Object>> maps = new ArrayList<>();
        for (String s : classes) {
            if (!s.contains("$")) {
                try {
                    Class classz = Class.forName(s);
                    List<Map<String, Object>> columns = getColumns(classz);
                    Map<String, Object> table = new HashMap<>();
                    table.put("name", classz.getSimpleName());
                    table.put("columns", columns);
                    maps.add(table);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        rootMap.put("model_tables", maps);
        if(File.separator.equals("/")){
            baseDir = baseDir.replaceAll("\\\\","/");
        }
        File dir = new File(baseDir);
        //检查目录是否存在，不存在则创建
        if (!dir.exists()) {
            dir.mkdirs();
        }
        Configuration cfg = new Configuration();
        cfg.setClassForTemplateLoading(CodeGenerator.class, "/template/");
        Template temp = null;
        try {
            temp = cfg.getTemplate("createSql.ftl");
            File docFile = new File(baseDir + "db.sql");
            Writer docout = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            //输出文件
            temp.process(rootMap, docout);
            System.out.println("==============文件生产成功===============");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    public List<Map<String, Object>> getColumns(Class classz) {
        Field[] fields = classz.getDeclaredFields();
        List<Map<String, Object>> list = new ArrayList<>();
        for (Field field : fields) {
            String name = field.getName();
            String jdbcType;
            String dbType;
            Class<?> typeClass = field.getType();
            if (typeClass.isAssignableFrom(Enum.class)) {
                jdbcType = "INTEGER";
                dbType = "tinyint(2)";
            } else {
                jdbcType = javaToJdbcType.get(typeClass);
                if (jdbcType == null) {
                    continue;
                }
                switch (jdbcType) {
                    case "VARCHAR":
                        dbType = "varchar(255)";
                        break;
                    case "DECIMAL":
                        dbType = "decimal";
                        break;
                    case "DOUBLE":
                        dbType = "double(20,4)";
                        break;
                    case "SMALLINT":
                        dbType = "int(8)";
                        break;
                    case "BIGINT":
                        dbType = "bigint(20)";
                        break;
                    case "INTEGER":
                        dbType = "int(11)";
                        break;
                    case "FLOAT":
                        dbType = "float(8,2)";
                        break;
                    case "TIMESTAMP":
                        dbType = "datetime";
                        break;
                    default:
                        dbType = "varchar(255)";
                        break;
                }
            }
            Map<String, Object> map = new HashMap<>();
            map.put("name", name);
            map.put("jdbcType", jdbcType);
            map.put("dbType", dbType);
            list.add(map);
        }
        return list;
    }


    private static Map<Class, String> javaToJdbcType;

    static {
        javaToJdbcType = new HashMap<>();
        javaToJdbcType.put(String.class, "VARCHAR");
        javaToJdbcType.put(BigDecimal.class, "DECIMAL");
        javaToJdbcType.put(Double.class, "DOUBLE");
        javaToJdbcType.put(double.class, "DOUBLE");
        javaToJdbcType.put(Short.class, "SMALLINT");
        javaToJdbcType.put(short.class, "SMALLINT");
        javaToJdbcType.put(Long.class, "BIGINT");
        javaToJdbcType.put(long.class, "BIGINT");
        javaToJdbcType.put(Integer.class, "INTEGER");
        javaToJdbcType.put(int.class, "INTEGER");
        javaToJdbcType.put(Float.class, "FLOAT");
        javaToJdbcType.put(float.class, "FLOAT");
        javaToJdbcType.put(Date.class, "TIMESTAMP");
    }

    public void generateService(String basePack, Class classz) {
        String root = Thread.currentThread().getContextClassLoader().getResource("./").getPath().replace("/target/classes/", "/");
        String str = basePack.replaceAll("\\.", "\\\\");
        String baseDir = root.substring(1) + "\\src\\main\\java\\" + str;
        Map<String, Object> rootMap = new HashMap<>();
        String className = classz.getSimpleName();
        rootMap.put("className", lowerCase(className.substring(0, 1)) + className.substring(1));
        rootMap.put("fullClassName", classz.getName());
        rootMap.put("user", System.getProperty("user.name"));
        rootMap.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        rootMap.put("package", basePack);
        rootMap.put("commonPack", currentPack);
        File dir = new File(baseDir + "\\service\\impl");
        //检查目录是否存在，不存在则创建
        if (!dir.exists()) {
            dir.mkdirs();
        }
        Configuration cfg = new Configuration();
        cfg.setClassForTemplateLoading(CodeGenerator.class, "/template/");
        Template temp = null;
        try {
            temp = cfg.getTemplate("serviceTemplate.ftl");
            File serviceFile = new File(baseDir + "\\service\\" + className + "Service.java");
            Writer docout = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(serviceFile)));
            //输出文件
            temp.process(rootMap, docout);

            temp = cfg.getTemplate("serviceImplTemplate.ftl");
            File serviceImplFile = new File(baseDir + "\\service\\impl\\" + className + "ServiceImpl.java");
            Writer docout1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(serviceImplFile)));
            //输出文件
            temp.process(rootMap, docout1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

        System.out.println("==============文件生产成功===============");
    }

    public static String upperCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    public static String lowerCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'A' && ch[0] <= 'Z') {
            ch[0] = (char) (ch[0] + 32);
        }
        return new String(ch);
    }

}
