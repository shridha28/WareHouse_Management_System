package com.warehouse.mapper;

import org.springframework.stereotype.Component;

import com.warehouse.dao.model.ArticleDao;
import com.warehouse.model.IArticleModel;

/**
 * Mapper class to map Inventory Dao and Model beans
 * 
 * @author Shridha S Jalihal
 */
@Component
public class InventoryMapper {

	public IArticleModel convertToModel(ArticleDao articleDao) {
		return IArticleModel.builder().artId(articleDao.getId()).name(articleDao.getName())
				.stock(articleDao.getStock()).build();

	}

	public ArticleDao convertToDao(IArticleModel articleModel) {
		return ArticleDao.builder().id(articleModel.getArtId()).name(articleModel.getName())
				.stock(articleModel.getStock()).build();

	}

}
