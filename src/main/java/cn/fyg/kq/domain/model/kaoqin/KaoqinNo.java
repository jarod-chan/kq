package cn.fyg.kq.domain.model.kaoqin;

import org.springframework.stereotype.Component;

import cn.fyg.kq.domain.model.kaoqin.busi.Kaoqin;
import cn.fyg.kq.domain.model.nogenerator.generator.Pattern;
import cn.fyg.kq.domain.model.nogenerator.generator.impl.AbstractPattern;
import cn.fyg.kq.domain.model.nogenerator.generator.impl.AbstractPatternFactory;
import cn.fyg.kq.domain.model.nogenerator.norecord.NoKey;
import cn.fyg.kq.infrastructure.tool.date.DateUtil;
import cn.fyg.kq.infrastructure.tool.fmt.Fmt;

@Component("kaoqinNo")
public class KaoqinNo extends AbstractPatternFactory<Kaoqin> {


	private class PatternImp extends AbstractPattern<Kaoqin>{

		@Override
		public String getNo() {
			return this.t.getNo();
		}

		@Override
		public void setNo(String no) {
			this.t.setNo(no);
		}

		@Override
		public Long initLimmit() {
			return Long.valueOf(9999);
		}

		@Override
		public void initNoKey(NoKey noKey, Kaoqin kaoqin) {
			noKey.setSys("K");
			noKey.setFlag("");
			String yearmonth=Fmt.toStr(DateUtil.today(), "yyyyMM");
			String pref=yearmonth.substring(2);		
			noKey.setPref(pref);
		}
	}

	@Override
	public Pattern<Kaoqin> doCreate(Kaoqin t) {
		return new PatternImp().init(t);
	}

}
