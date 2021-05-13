package cn.lili.controller.promotion;

import cn.lili.common.utils.ResultUtil;
import cn.lili.common.vo.PageVO;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.promotion.entity.dos.PointsGoodsCategory;
import cn.lili.modules.promotion.entity.vos.PointsGoodsSearchParams;
import cn.lili.modules.promotion.entity.vos.PointsGoodsVO;
import cn.lili.modules.promotion.service.PointsGoodsCategoryService;
import cn.lili.modules.promotion.service.PointsGoodsService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 买家端,积分商品接口
 *
 * @author paulG
 * @date 2021/1/19
 **/
@RestController
@Api(tags = "买家端,积分商品接口")
@RequestMapping("/buyer/promotion/pointsGoods")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PointsGoodsBuyerController {

    private final PointsGoodsService pointsGoodsService;

    private final PointsGoodsCategoryService pointsGoodsCategoryService;

    @GetMapping
    @ApiOperation(value = "分页获取积分商品")
    public ResultMessage<IPage<PointsGoodsVO>> getPointsGoodsPage(PointsGoodsSearchParams searchParams, PageVO page) {
        IPage<PointsGoodsVO> pointsGoodsByPage = pointsGoodsService.getPointsGoodsByPage(searchParams, page);
        return ResultUtil.data(pointsGoodsByPage);
    }

    @GetMapping("/category")
    @ApiOperation(value = "获取积分商品分类分页")
    public ResultMessage<IPage<PointsGoodsCategory>> page(String name, PageVO page) {
        return ResultUtil.data(pointsGoodsCategoryService.getCategoryByPage(name, page));
    }

}