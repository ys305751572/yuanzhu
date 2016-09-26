package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * Created by Administrator on 2016/9/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/springmvc-servlet.xml"})
@Transactional
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
public class DemoTest {

    @Test
    public void loginTest() {
        System.out.println("result:");
    }
}
