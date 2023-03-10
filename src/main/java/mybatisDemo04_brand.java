import mapper.BrandMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pojo.Brand;

import java.io.InputStream;
import java.util.List;

//練習資料庫與pojo欄位不同之CRUD，並練習使用SQL映射文件(含動態SQL)與註解進行開發
public class mybatisDemo04_brand {
    static SqlSession session;
    static BrandMapper brandMapper;
    public static void main(String[] args) throws Exception {
        System.out.println(session);
        String resource = "mybatis_config04_brand.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        session = sqlSessionFactory.openSession();
        brandMapper=session.getMapper(BrandMapper.class);


        System.out.println("----1.1查詢全部----");
        List<Brand> brands=brandMapper.selectAll();
        System.out.println(brands);

        System.out.println("----1.2使用註解查詢全部----");
        brands=brandMapper.selectAll_annotation();
        System.out.println(brands);

        System.out.println("----1.3查詢特定ID----");
        Brand brand=brandMapper.selectById(3);
        System.out.println(brand);

        System.out.println("----1.4使用註解查詢特定ID----");
        brand=brandMapper.selectById_annotation(3);
        System.out.println(brand);

        System.out.println("----1.5多條件模糊查詢----");
        String testbrandName= null;
        String testcompanyName= null;
        Integer testordered= null;
        String testdescription= null;
        Integer teststatus = null;
        testdescription="1";
        teststatus=0;
        if (testbrandName != null) {testbrandName="%"+testbrandName+"%";}
        if (testcompanyName != null) {testcompanyName="%"+testcompanyName+"%";}
        if (testdescription != null) {testdescription="%"+testdescription+"%";}
        Brand testbrand=new Brand(testbrandName,testcompanyName,testordered,testdescription,teststatus);
        brands=brandMapper.selectByCondition(testbrand);
        System.out.println(brands);


        System.out.println("----2.插入數據----");
        testbrandName= "testBrandName";
        testcompanyName= "testCompanyName";
        testordered= 123;
        testdescription= "testInsertdescription";
        teststatus = 9;
        testbrand=new Brand(testbrandName,testcompanyName,testordered,testdescription,teststatus);
        brandMapper.insert(testbrand);
        session.commit();
        System.out.println("插入數據的ID為："+testbrand.getId());
        System.out.println("插入數據後資料如下：");
        brands=brandMapper.selectAll();
        System.out.println(brands);

        System.out.println("----3.多條件更新數據----");
        Integer testid = 3;
        testbrandName= "updateBrandName";
        testcompanyName= "updateCompanyName";
        testordered= 4321;
        testdescription= "updateInsertdescription";
        teststatus = 65535;
        testbrand=new Brand(testid,testbrandName,testcompanyName,testordered,testdescription,teststatus);
        brandMapper.update(testbrand);
        session.commit();
        System.out.println("更新數據後資料如下：");
        brands=brandMapper.selectAll();
        System.out.println(brands);


        System.out.println("----4.1刪除單筆數據----");
        brandMapper.deleteById(5);
        session.commit();
        System.out.println("刪除數據後資料如下：");
        brands=brandMapper.selectAll();
        System.out.println(brands);


        System.out.println("----4.2使用註解刪除單筆數據----");
        brandMapper.deleteById_annotation(4);
        session.commit();
        System.out.println("刪除數據後資料如下：");
        brands=brandMapper.selectAll();
        System.out.println(brands);

        System.out.println("----4.3刪除多筆數據----");
        int[] ids={1,2};
        brandMapper.deleteByIds(ids);
        session.commit();
        System.out.println("刪除數據後資料如下：");
        brands=brandMapper.selectAll();
        System.out.println(brands);

        session.close();
    }
}
