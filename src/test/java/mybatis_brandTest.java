import mapper.BrandMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.*;
import pojo.Brand;

import java.io.InputStream;
import java.util.List;

public class mybatis_brandTest {
    static SqlSession session;
    static BrandMapper brandMapper;
    @BeforeClass
    public static void beforeclass() throws Exception{

        System.out.println(session);
        String resource = "mybatis_config04_brand.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        session = sqlSessionFactory.openSession();
        brandMapper=session.getMapper(BrandMapper.class);
    }

    @AfterClass
    public static void afterclass(){
        session.commit();
        session.close();
    }

    @Test
    public void testselectAll() throws Exception {
        List<Brand> brands=brandMapper.selectAll();
        System.out.println(brands);
    }@Test
    public void testselectAll_annotation() throws Exception {
        List<Brand> brands=brandMapper.selectAll_annotation();
        System.out.println(brands);
    }

    @Test
    public void testselectById() throws Exception {
        Brand brand=brandMapper.selectById(3);
        System.out.println(brand);
    }

    @Test
    public void testselectById_annotation() throws Exception {
        Brand brand=brandMapper.selectById_annotation(3);
        System.out.println(brand);
    }
    @Test
    public void testselectByCondition() throws Exception {
        String testbrandName= null;
        String testcompanyName= null;
        Integer testordered= null;
        String testdescription= null;
        Integer teststatus = null;
        //----------測試條件寫這邊
//        testbrandName="2";
//        testcompanyName="3";
//        testordered=444;
        testdescription="1";
        teststatus=0;
        //----------測試條件寫上面

        if (testbrandName != null) {testbrandName="%"+testbrandName+"%";}
        if (testcompanyName != null) {testcompanyName="%"+testcompanyName+"%";}
        if (testdescription != null) {testdescription="%"+testdescription+"%";}
        Brand testbrand=new Brand(testbrandName,testcompanyName,testordered,testdescription,teststatus);
        List<Brand> brands=brandMapper.selectByCondition(testbrand);
        System.out.println(brands);
    }
    @Test
    public void testinsert() throws Exception {
        String testbrandName= "testBrandName";
        String testcompanyName= "testCompanyName";
        Integer testordered= 123;
        String testdescription= "testInsertdescription";
        Integer teststatus = 9;
        Brand testbrand=new Brand(testbrandName,testcompanyName,testordered,testdescription,teststatus);
        brandMapper.insert(testbrand);
        System.out.println("插入數據的ID為："+testbrand.getId());
        System.out.println("----------------");
        System.out.println("插入數據後資料如下：");
        List<Brand> brands=brandMapper.selectAll();
        System.out.println(brands);
    }

    @Test
    public void testupdate() throws Exception {
        String testbrandName= null;
        String testcompanyName= null;
        Integer testordered= null;
        String testdescription= null;
        Integer teststatus = null;

        //----------測試更新條件寫這邊
        Integer testid = 10;
//        testbrandName= "updateBrandName";
//        testcompanyName= "updateCompanyName";
        testordered= 4321;
//        testdescription= "updateInsertdescription";
        teststatus = 65535;
        //----------測試條件寫上面

        Brand testbrand=new Brand(testid,testbrandName,testcompanyName,testordered,testdescription,teststatus);
        brandMapper.update(testbrand);
        System.out.println("----------------");
        System.out.println("更新數據後資料如下：");
        List<Brand> brands=brandMapper.selectAll();
        System.out.println(brands);
    }

    @Test
    public void deleteById() throws Exception {
        brandMapper.deleteById(10);

        System.out.println("----------------");
        System.out.println("刪除數據後資料如下：");
        List<Brand> brands=brandMapper.selectAll();
        System.out.println(brands);
    }

    @Test
    public void deleteById_annotation() throws Exception {
        brandMapper.deleteById_annotation(1);

        System.out.println("----------------");
        System.out.println("刪除數據後資料如下：");
        List<Brand> brands=brandMapper.selectAll();
        System.out.println(brands);
    }

    @Test
    public void deleteByIds() throws Exception {

        //----------測試批量刪除條件寫這邊
        int[] ids={1,3};
        //----------測試條件寫上面

        brandMapper.deleteByIds(ids);

        System.out.println("----------------");
        System.out.println("批量刪除數據後資料如下：");
        List<Brand> brands=brandMapper.selectAll();
        System.out.println(brands);
    }
}
