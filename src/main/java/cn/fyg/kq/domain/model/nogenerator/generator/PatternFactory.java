package cn.fyg.kq.domain.model.nogenerator.generator;

public interface PatternFactory<T> {
	
	Pattern<T> create(T t);

}
