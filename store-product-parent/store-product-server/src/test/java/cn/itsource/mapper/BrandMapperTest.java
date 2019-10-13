package cn.itsource.mapper;

import cn.itsource.APP;
import cn.itsource.domain.Brand;
import cn.itsource.query.BrandQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = APP.class)
public class BrandMapperTest {


    @Autowired
    private BrandMapper brandMapper;

    @Test
    public void queryPage() {

        BrandQuery brandQuery = new BrandQuery();
        brandQuery.setPage(1);
        brandQuery.setRows(10);
        Brand id = brandMapper.selectById(1L);
        System.out.println("---------------------" + id);
        IPage<Brand> iPage = brandMapper.queryPage(new Page(brandQuery.getPage(), brandQuery.getRows()), brandQuery);
        System.out.println("1111111111111111111111111111111111111111");
        System.out.println("2222222222222222222222222"+iPage.getTotal());
        for (Brand brand : iPage.getRecords()) {
            System.out.println("============================"+brand);
            
        }
    }
}