package cn.lili.modules.order.cart.render.impl;

import cn.lili.modules.goods.service.CategoryService;
import cn.lili.modules.order.cart.entity.dto.TradeDTO;
import cn.lili.modules.order.cart.entity.enums.RenderStepEnums;
import cn.lili.modules.order.cart.entity.vo.CartSkuVO;
import cn.lili.modules.order.cart.entity.vo.CartVO;
import cn.lili.modules.order.cart.render.CartRenderStep;
import cn.lili.modules.promotion.service.KanjiaActivityGoodsService;
import cn.lili.modules.promotion.service.PointsGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 购物车渲染，将购物车中的各个商品，拆分到每个商家，形成购物车VO
 *
 * @author Chopper
 * @see CartVO
 */
@Service
public class CartPriceRender implements CartRenderStep {


    /**
     * 商品分类
     */
    @Autowired
    private CategoryService categoryService;
    /**
     * 积分商品
     */
    @Autowired
    private PointsGoodsService pointsGoodsService;
    /**
     * 砍价商品
     */
    @Autowired
    private KanjiaActivityGoodsService kanjiaActivityGoodsService;

    @Override
    public RenderStepEnums step() {
        return RenderStepEnums.CART_PRICE;
    }

    @Override
    public void render(TradeDTO tradeDTO) {
        //构造cartVO
        this.buildCartPrice(tradeDTO);
        this.buildTradePrice(tradeDTO);
    }

    /**
     * 购物车价格
     *
     * @param tradeDTO 购物车展示信息
     */
    void buildCartPrice(TradeDTO tradeDTO) {
        //购物车列表
        List<CartVO> cartVOS = tradeDTO.getCartList();

        cartVOS.forEach(cartVO -> {
            cartVO.getPriceDetailDTO().accumulationPriceDTO(
                    cartVO.getSkuList().stream().map(CartSkuVO::getPriceDetailDTO).collect(Collectors.toList()));
        });
    }


    /**
     * 初始化购物车
     *
     * @param tradeDTO 购物车展示信息
     */
    void buildTradePrice(TradeDTO tradeDTO) {
        tradeDTO.getPriceDetailDTO().accumulationPriceDTO(
                tradeDTO.getCartList().stream().map(CartVO::getPriceDetailDTO).collect(Collectors.toList()));
    }

}
