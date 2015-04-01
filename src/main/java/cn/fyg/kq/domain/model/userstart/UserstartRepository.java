package cn.fyg.kq.domain.model.userstart;

import org.springframework.data.repository.Repository;

public interface UserstartRepository extends Repository<Userstart, Long> {
	
	Userstart save(Userstart userstart);

}
