package cn.itsource.service;

import cn.itsource.domain.Brand;
import cn.itsource.query.BrandQuery;
import cn.itsource.util.PageList;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 品牌信息 服务类
 * </p>
 *
 * @author ClownWong
 * @since 2019-10-12
 */
public interface IBrandService extends IService<Brand> {

    /**
     * 分页高级查询
     * @param query
     * @return
     */
    PageList<Brand> queryPage(BrandQuery query);



}
