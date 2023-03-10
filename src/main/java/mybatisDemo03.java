import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pojo.User;

import java.io.InputStream;
import java.util.List;
//使用Mapper代理方法+包掃描SQL映射文件
public class mybatisDemo03 {
    public static void main(String[] args) throws Exception {
        String resource = "mybatis_config03.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper userMapper=session.getMapper(UserMapper.class);
        List<User> users=userMapper.selectAll2();
        System.out.println(users);
        session.close();




    }
}
