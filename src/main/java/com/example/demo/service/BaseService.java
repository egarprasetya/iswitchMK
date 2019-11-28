package com.example.demo.service;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.demo.model.*;

public interface BaseService<alembic_version_configModel>
{
List<alembic_version_configModel> findAll();
	
	Page<alembic_version_configModel> findAll(Pageable pageable);
	
	alembic_version_configModel findById (String id);

	alembic_version_configModel save (alembic_version_configModel data);

	alembic_version_configModel update (String id, alembic_version_configModel data);
	
	void delete(String id);
	
	boolean isEmpty();
}
