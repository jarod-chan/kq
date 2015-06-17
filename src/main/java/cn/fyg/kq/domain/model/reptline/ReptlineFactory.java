package cn.fyg.kq.domain.model.reptline;

import cn.fyg.kq.domain.shared.kq.Comp;

public class ReptlineFactory {
	
	public static Reptline create(Comp comp){
		Reptline reptline = new Reptline();
		reptline.setComp(comp);
		return reptline;
	}

}
