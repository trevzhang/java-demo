package com.trevzhang.demo.test;

import com.trevzhang.demo.test.model.Bean;
import com.trevzhang.demo.test.model.SuperBean;

/**
 * 验证:父类做流程编排,子类重写了方法,并且实例也是子类实例的话,调用流程编排方法,会走重写后的子类方法
 *
 * @author 春火
 * @since 2021/9/22 11:25 上午
 */
public class ThisSuperTest {

    public static void main(String[] args) {
        SuperBean bean = new Bean();
        bean.hello("Trevor");
    }
}
