package cn.itsource.service.impl;

import cn.itsource.client.RedisClient;
import cn.itsource.client.StaticPageClient;
import cn.itsource.domain.ProductType;
import cn.itsource.mapper.ProductTypeMapper;
import cn.itsource.service.IProductTypeService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品目录 服务实现类
 * </p>
 *
 * @author ClownWong
 * @since 2019-10-12
 */
@Service
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType> implements IProductTypeService {

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private StaticPageClient staticPageClient;

    /*静态化首页*/
    @Override
    public void genHomePage() {
        String templatePath="E:\\IdeaProject\\store-parent\\store-product-parent\\store-product-server\\src\\main\\resources\\template\\product.type.vm";
        String targetPath="E:\\IdeaProject\\store-parent\\store-product-parent\\store-product-server\\src\\main\\resources\\template\\product.type.vm.html";
        List<ProductType> productTypes = loadTypeTree();
        staticPageClient.generateStaticPage(templatePath,targetPath,productTypes);

        //再根据home.vm生成html.html
        templatePath = "E:\\IdeaProject\\store-parent\\store-product-parent\\store-product-server\\src\\main\\resources\\template\\home.vm";
        targetPath = "E:\\IdeaProject\\store-web-parent\\ecommerce\\static\\home.html";
        Map<String,Object> model = new HashMap<>();
        model.put("staticRoot","E:\\IdeaProject\\store-parent\\store-product-parent\\store-product-server\\src\\main\\resources\\");

        staticPageClient.generateStaticPage(templatePath, targetPath, model);
    }

    /**
     * 加载类型树
     * @return
     */
    @Override
    public List<ProductType> loadTypeTree() {
        //先查询Redis中的数据 java对象存储到redis中的方案- json字符串
        String productTypesStr = redisClient.get("productTypes");
        System.out.println("查询Redis.....");
        //判断redis中是否有数据
        if(productTypesStr!=null){
        //返回给前端 -- json字符串转java对象
            List<ProductType> productTYpes =
                    JSONArray.parseArray(productTypesStr,ProductType.class);//转java集合(productTypesStr);
            return productTYpes;
        }else{
        //查询数据库
            List<ProductType> productTypes = loadTypeTreeLoop2();
            System.out.println("查询数据库..........");
        //把数据缓存到redis中-json字符串
            productTypesStr =
                    JSON.toJSONString(productTypes);//转json字符串(productTypes);
            redisClient.set("productTypes",productTypesStr);
        //返回给前端
            return productTypes;
        }
    }


    private List<ProductType> loadTypeTreeLoop2(){
        //先查询所有
        List<ProductType> allProductTypes =
                baseMapper.selectList(null); //1000
        //再组装数据
        List<ProductType> firstLevelTypes = new
                ArrayList<>();
        Map<Long,ProductType> productTypeMap = new
                HashMap<>();
        //将所有的productType存入map中
        for (ProductType productType :
                allProductTypes) {
            productTypeMap.put(productType.getId(),productType);
        }
        //再循环组装数据
        for (ProductType productType :
                allProductTypes) {
        //如果是一级
            if(productType.getPid()==0){
                firstLevelTypes.add(productType);
            }else{
        //如果不是一级
                ProductType parent =
                        productTypeMap.get(productType.getPid());
                parent.getChildren().add(productType);
            }
        }
        return firstLevelTypes;
    }

    /*每次增删改 都要从db同步到redis*/
    @Override
    public boolean save(ProductType entity) {
        boolean save = super.save(entity);
        synchronizedOption();
        return save;
         
    }

    @Override
    public boolean removeById(Serializable id) {
        synchronizedOption();
        return super.removeById(id);
    }

    @Override
    public boolean updateById(ProductType entity) {
        synchronizedOption();
        return super.updateById(entity);
    }

    /**
     * 增删改的同步操作
     */
    private void synchronizedOption(){
        //同步redis的数据
        List<ProductType> productTypes = loadTypeTreeLoop2();
        String productTypesStr = JSON.toJSONString(productTypes);
        redisClient.set("productTypes",productTypesStr);
        //生成home.html静态页面
        //genHomePage();
    }
}
