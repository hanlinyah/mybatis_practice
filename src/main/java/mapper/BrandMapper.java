package mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import pojo.Brand;

import java.util.List;

public interface BrandMapper {
    List<Brand> selectAll();


    @ResultMap("brandResultMap")
    @Select(value = "select * from tb_brand")
    List<Brand> selectAll_annotation();
    Brand selectById(Integer id);

    @ResultMap("brandResultMap")
    @Select(value = "select * from tb_brand where id =#{id}")
    Brand selectById_annotation(Integer id);
    List<Brand> selectByCondition(Brand brand);
    void insert(Brand brand);
    void update(Brand brand);

    @Delete(value = "delete  from tb_brand where id =#{id}")
    void deleteById_annotation(Integer id);
    void deleteById(Integer id);
    void deleteByIds(int[] ids);

}
