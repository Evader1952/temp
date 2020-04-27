package com.mp.test;

import com.mp.common.generator.CodeGenerator;
import com.mp.test.entity.PayRefundApply;

public class Main {
    public static void main(String[] args) {
        String basePack = Main.class.getPackage().getName();
        CodeGenerator codeGenerator = new CodeGenerator();
        codeGenerator.generateMybatisXml(basePack, PayRefundApply.class);
        codeGenerator.generateDao(basePack, PayRefundApply.class);
        codeGenerator.generateService(basePack, PayRefundApply.class);
    }
}
