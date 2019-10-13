package cn.itsource.mapper;

import cn.itsource.domain.Brand;
import cn.itsource.query.BrandQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 品牌信息 Mapper 接口
 * </p>
 *
 * @author ClownWong
 * @since 2019-10-12
 */
@Component
public interface BrandMapper extends BaseMapper<Brand> {


    /*高级查询+分页*/
    IPage<Brand> queryPage(Page page, @Param("query") BrandQuery query);
}
