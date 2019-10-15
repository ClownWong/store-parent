package cn.itsource.service;

import cn.itsource.domain.ProductType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 商品目录 服务类
 * </p>
 *
 * @author ClownWong
 * @since 2019-10-12
 */
public interface IProductTypeService extends IService<ProductType> {
    List<ProductType> loadTypeTree();


    void genHomePage();
}
