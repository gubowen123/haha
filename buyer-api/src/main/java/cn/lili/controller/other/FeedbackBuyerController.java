package cn.lili.controller.other;

import cn.lili.common.enums.ResultCode;
import cn.lili.common.security.context.UserContext;
import cn.lili.common.utils.ResultUtil;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.page.entity.dos.Feedback;
import cn.lili.modules.page.service.FeedbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 买家端,意见反馈接口
 *
 * @author Bulbasaur
 * @date 2020-05-5 15:10:16
 */
@RestController
@Api(tags = "买家端,意见反馈接口")
@RequestMapping("/buyer/feedback")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FeedbackBuyerController {

    /**
     * 意见反馈
     */
    private final FeedbackService feedbackService;

    @ApiOperation(value = "添加意见反馈")
    @PostMapping()
    public ResultMessage<Object> save(Feedback feedback) {
        feedback.setUserName(UserContext.getCurrentUser().getNickName());
        if (feedbackService.save(feedback)) {
            return ResultUtil.success(ResultCode.SUCCESS);
        }
        return ResultUtil.error(ResultCode.ERROR);
    }

}
