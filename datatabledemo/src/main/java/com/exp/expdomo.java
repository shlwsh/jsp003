package com.exp;

/**
 * Created by smq on 16/8/29.
 */


//import junit.framework.TestCase;
import org.apache.commons.jexl2.*;
import org.apache.commons.jexl3.*;
import org.junit.*;

//import static junit.framework.TestCase.assertFalse;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class expdomo {

    public expdomo() {
    }

    /**
     *
     * @desc <pre>
     * 需求描述：数据库中有如下表：test,列定义如下：
     * --------
     * c1 c2 c3
     * --------
     * 1 2 3
     * 2 1 0
     * ...
     * --------
     * 业务需要在用户录入数据后，进行数据校验，对不符合条件的数据，禁止保存；
     *
     * 条件1：如c1>c2，则c3=0;否则c3值不变；
     * 此问题可以使用客户端或服务器端简单实现，但是对于这样的表达式有几千个，
     * 并且需求经常发生变更时，代码维护量将成指数级增加，需要考虑优化算法。
     *
     * 解决办法：
     * 使用jexl表达式，将各条件保存为表达式，数据保存时，校验即可。
     * commons-jexl-2.1.1.jar
     */
    public  void testExpr() {
        Expression expr;
        org.apache.commons.jexl2.JexlContext ctxt = new org.apache.commons.jexl2.MapContext();
        org.apache.commons.jexl2.JexlEngine jexl = new org.apache.commons.jexl2.JexlEngine();
        // 表达式
        String exps = "if(c1>c2) {c3=0}";
        expr = jexl.createExpression(exps);

        // case 1
        ctxt.set("c1", 1);
        ctxt.set("c2", 2);
        expr.evaluate(ctxt);
        assertFalse((Integer) ctxt.get("c3") == (Integer) (0));

        // case 2
        ctxt.set("c1", 2);
        ctxt.set("c2", 1);
        expr.evaluate(ctxt);
        assertTrue((Integer) ctxt.get("c3") == (Integer) (0));
    }
//    public static MyMath {
//        public double cos(double x) {
//            return Math.cos(x);
//        }
//    }
    public void DemoExl3() {
        Map<String, Object> funcs = new HashMap<String, Object>();
        funcs.put("math", new mymath());
        org.apache.commons.jexl3.JexlEngine jexl = new org.apache.commons.jexl3.JexlBuilder().namespaces(funcs).create();

        org.apache.commons.jexl3.JexlContext jc = new org.apache.commons.jexl3.MapContext();
        jc.set("pi", Math.PI);

        JexlExpression e = jexl.createExpression("math:cos(pi)");
        Object o = e.evaluate(jc);
        System.out.printf(o.toString());
//        assertEquals(Double.valueOf(-1), o);
    }
}