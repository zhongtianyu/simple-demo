import com.sz.FtpControllerApp;
import com.sz.service.FtpServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chenjiahao
 * @description TODO
 * @date 2020/10/19 16:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FtpControllerApp.class)
public class ftpDownLoadTest {

    @Autowired
    FtpServiceImpl ftpServiceImp;

    @Test
    public void testDownLoad(){
        ftpServiceImp.downLoad();
    }
}
