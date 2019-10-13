package cn.itsource.service.impl;

import cn.itsource.domain.Brand;
import cn.itsource.mapper.BrandMapper;
import cn.itsource.query.BrandQuery;
import cn.itsource.service.IBrandService;
import cn.itsource.util.PageList;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 品牌信息 服务实现类
 * </p>
 *
 * @author ClownWong
 * @since 2019-10-12
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {


    @Override
    public PageList<Brand> queryPage(BrandQuery query) {
        IPage<Brand> iPage =
                baseMapper.queryPage(new Page(query.getPage(), query.getRows()), query);//完成分页

        //把分页数据给pageList
        PageList<Brand> pageList = new PageList<>(iPage.getTotal(),iPage.getRecords());
        return pageList;
    }


}
