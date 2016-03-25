package com.demo.adtadmdiv;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;

public interface AdtAdmDivAPI {
	
	Page<AdtAdmDiv> paginate(int pageNumber, int pageSize, String pcode);
	
	List<AdtAdmDiv> getList();
	
	boolean save(AdtAdmDiv admDiv);

	boolean update(AdtAdmDiv admDiv);

	AdtAdmDiv findById(int id);

	boolean deleteById(int id);

}
