import com.sz.service.CleanDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chenjiahao
 * @description TODO
 * @date 2021/6/4 15:31
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class testConfig {


    @Autowired
    CleanDataService cleanDataService;

    @Test
    public void testO(){

        String s = cleanDataService.cleanData();
        System.out.println(s);
    }
}
