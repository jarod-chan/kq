package cn.fyg.kq.application;

import cn.fyg.kq.domain.model.contract.Contract;

public interface ContractService {
	
	Contract find(Long id);

	Contract save(Contract contract);

}
