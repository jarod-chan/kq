package cn.fyg.kq.domain.model.kaoqin;

import org.apache.commons.lang.StringUtils;

import cn.fyg.kq.domain.model.kaoqin.busi.Kaoqin;
import cn.fyg.kq.domain.model.kaoqin.busi.KaoqinItem;
import cn.fyg.kq.domain.shared.verify.Result;
import cn.fyg.kq.domain.shared.verify.CommonResult;
import cn.fyg.kq.domain.shared.verify.CommonValidator;

/**
 *提交校验
 */
public class KaoqinCommitVld extends CommonValidator<Kaoqin> {
	


	@Override
	public Result doVerify() {
		CommonResult result = newResult();
		Kaoqin kaoqin = this.obj;
		for (KaoqinItem kaoqinItem : kaoqin.getKaoqinItems()) {
			verifyItem(kaoqinItem,result);
		}
		return result;
	}

	private void verifyItem(KaoqinItem kaoqinItem, CommonResult result) {
		String messsage=String.format("序号为%s的【事由】不能为空", kaoqinItem.getSn());
		if(StringUtils.isBlank(kaoqinItem.getReason())){
			//result.append(messsage);
			return;
		}
	}

	@Override
	public void doUpdate() {
	}

}
