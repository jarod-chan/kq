package cn.fyg.kq.application.common;

import cn.fyg.kq.domain.shared.verify.Result;


public interface CommitValidator<T> {
	
	Result verifyForCommit(T t);

}
