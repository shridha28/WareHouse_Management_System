package com.warehouse.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.warehouse.dao.model.PArticleDao;
import com.warehouse.model.PArticleModel;

/**
 * Mapper class to map Product Dao and Model beans
 * 
 * @author Shridha S Jalihal
 */
@Component
public class ProductMapper {
	
	public List<PArticleModel> convertDaoToModel(List<PArticleDao> list ) {

		List<PArticleModel> listOfProductDaos = new ArrayList<>();
		
		list.forEach(article-> 
					 listOfProductDaos.add(PArticleModel.builder()
					                       .availabilty(article.getAvailability())
										   .artId(article.getArtId())
										   .amountOf(article.getAmountOf())
										   .build()));
		return listOfProductDaos;

	}
	
	public List<PArticleDao> convertModelToDao(List<PArticleModel> list ) {

		List<PArticleDao> listOfProductDaos = new ArrayList<>();
		
		list.forEach(article-> 
		listOfProductDaos.add(PArticleDao.builder()
										.availability(article.getAvailabilty())
										.artId(article.getArtId())
										.amountOf(article.getAmountOf())
										.build()));
		return listOfProductDaos;

	}
}
