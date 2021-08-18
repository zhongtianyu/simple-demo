import com.sz.SqlCreateControllerApp;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chenjiahao
 * @description TODO
 * @date 2021/6/1 18:22
 */
@RunWith(SpringRunner.class)
//如果不写classes的话就需要将测试类放到相应的包目录下：com.sz
@SpringBootTest(classes = SqlCreateControllerApp.class)
@TestPropertySource({"classpath:application.properties"})
public class CreateTableServiceTest {


}
