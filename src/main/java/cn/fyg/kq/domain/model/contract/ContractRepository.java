package cn.fyg.kq.domain.model.contract;

import org.springframework.data.repository.Repository;

public interface ContractRepository extends Repository<Contract,Long> {
	
	Contract save(Contract contract);
	
	Contract findOne(Long id);
}
