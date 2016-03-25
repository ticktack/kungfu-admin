package com.demo.blog;

import com.jfinal.plugin.activerecord.Page;

public interface BlogAPI {
	
	Page<Blog> paginate(int pageNumber, int pageSize);
	
	Blog findById(int id);

	boolean deleteById(int id);
}
