package cn.lili.controller.setting;

import cn.lili.common.enums.ResultCode;
import cn.lili.common.utils.PageUtil;
import cn.lili.common.utils.ResultUtil;
import cn.lili.common.vo.PageVO;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.message.entity.dos.WechatMessage;
import cn.lili.modules.message.service.WechatMessageService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 管理端,微信消息接口
 *
 * @author Chopper
 * @date 2020/12/2 10:40
 */
@RestController
@Api(tags = "管理端,微信消息接口")
@RequestMapping("/manager/message/wechat")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WechatMessageManageController {

    private final WechatMessageService wechatMessageService;


    @GetMapping(value = "/init")
    @ApiOperation(value = "初始化微信消息")
    public ResultMessage init() {
        wechatMessageService.init();
        return ResultUtil.success(ResultCode.SUCCESS);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "查看微信消息详情")
    public ResultMessage<WechatMessage> get(@PathVariable String id) {

        WechatMessage wechatMessage = wechatMessageService.getById(id);
        return ResultUtil.data(wechatMessage);
    }

    @GetMapping
    @ApiOperation(value = "分页获取微信消息")
    public ResultMessage<IPage<WechatMessage>> getByPage(PageVO page) {
        IPage<WechatMessage> data = wechatMessageService.page(PageUtil.initPage(page));
        return ResultUtil.data(data);
    }

    @PostMapping
    @ApiOperation(value = "新增微信消息")
    public ResultMessage<WechatMessage> save(WechatMessage wechatMessage) {

        if (wechatMessageService.save(wechatMessage)) {
            return ResultUtil.data(wechatMessage);
        }
        return ResultUtil.error(ResultCode.ERROR);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "更新微信消息")
    public ResultMessage<WechatMessage> update(@PathVariable String id, WechatMessage wechatMessage) {
        if (wechatMessageService.updateById(wechatMessage)) {
            return ResultUtil.data(wechatMessage);
        }
        return ResultUtil.error(ResultCode.ERROR);
    }

    @DeleteMapping(value = "/{ids}")
    @ApiOperation(value = "删除微信消息")
    public ResultMessage<Object> delAllByIds(@PathVariable List ids) {
        wechatMessageService.removeByIds(ids);
        return ResultUtil.success(ResultCode.SUCCESS);
    }
}