import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author chengruyuan
 * @Date 2019/12/17 11:39
 * @Version 1.0.0
 **/
//@SpringBootTest()
@RunWith(SpringRunner.class)
public class TestBCrypt {

    @Test
    public void t1(){
//        $2a$10$rQ17BMtNJdyyobafTLuE0eI2Rv0vhBLZ4NySAUNyzOlPJEQqGr76.
        String hashpw = BCrypt.hashpw("456", BCrypt.gensalt());
        System.out.println("pwd: " +hashpw);
        boolean checkpw = BCrypt.checkpw("123", hashpw);
        System.out.println("正确：" + checkpw);
    }
}
