package cn.lili.controller.other;

import cn.lili.common.enums.ResultCode;
import cn.lili.common.utils.PageUtil;
import cn.lili.common.utils.ResultUtil;
import cn.lili.common.vo.PageVO;
import cn.lili.common.vo.ResultMessage;
import cn.lili.common.vo.SearchVO;
import cn.lili.modules.base.entity.dos.VerificationSource;
import cn.lili.modules.base.service.VerificationSourceService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 管理端,验证码资源维护接口
 *
 * @author Chopper
 * @date: 2020/12/7 11:33
 */
@RestController
@Api(tags = "管理端,验证码资源维护接口")
@RequestMapping("/manager/verificationSource")
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VerificationSourceController {

    private final VerificationSourceService verificationSourceService;

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "查看验证码资源维护详情")
    public ResultMessage<VerificationSource> get(@PathVariable String id) {

        VerificationSource verificationSource = verificationSourceService.getById(id);
        return ResultUtil.data(verificationSource);
    }

    @GetMapping
    @ApiOperation(value = "分页获取验证码资源维护")
    public ResultMessage<IPage<VerificationSource>> getByPage(VerificationSource entity,
                                                              SearchVO searchVo,
                                                              PageVO page) {
        IPage<VerificationSource> data = verificationSourceService.page(PageUtil.initPage(page), PageUtil.initWrapper(entity, searchVo));
        return ResultUtil.data(data);
    }

    @PostMapping
    @ApiOperation(value = "新增验证码资源维护")
    public ResultMessage<VerificationSource> save(VerificationSource verificationSource) {

        if (verificationSourceService.save(verificationSource)) {
            verificationSourceService.initCache();
            return ResultUtil.data(verificationSource);
        }
        return ResultUtil.error(ResultCode.ERROR);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "更新验证码资源维护")
    public ResultMessage<VerificationSource> update(@PathVariable String id, VerificationSource verificationSource) {
        verificationSource.setId(id);
        if (verificationSourceService.updateById(verificationSource)) {
            verificationSourceService.initCache();
            return ResultUtil.data(verificationSource);
        }
        return ResultUtil.error(ResultCode.ERROR);
    }

    @DeleteMapping(value = "/{ids}")
    @ApiOperation(value = "删除验证码资源维护")
    public ResultMessage<Object> delAllByIds(@PathVariable List ids) {

        verificationSourceService.removeByIds(ids);
        verificationSourceService.initCache();
        return ResultUtil.success(ResultCode.SUCCESS);
    }
}