package com.warehouse.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.dao.model.ArticleDao;
import com.warehouse.dao.model.PArticleDao;
import com.warehouse.dao.model.ProductDao;
import com.warehouse.enums.ArticleStock;
import com.warehouse.exception.OutOfStockException;
import com.warehouse.exception.ProductNotFoundException;
import com.warehouse.mapper.ProductMapper;
import com.warehouse.model.Product;
import com.warehouse.repo.InventoryRepository;
import com.warehouse.repo.ProductRepository;

@Service
public class ProductService {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	private ProductMapper mapper;
	
	private ProductRepository productRepo;
	
	private InventoryRepository articlesRepo;
	
	@Autowired
	public ProductService(ProductMapper mapper, ProductRepository productRepo, InventoryRepository articlesRepo) {
		this.mapper = mapper;
		this.productRepo = productRepo;
		this.articlesRepo = articlesRepo;
	}


   /*
	* Method to save products in Products Collection
	* 
	* @param listOfProducts of type Product
	* 
    */
	public void saveProducts(List<Product> products) {
		products.forEach(product -> productRepo.save(ProductDao.builder()
													 .id(UUID.randomUUID().toString())
													 .name(product.getName())
													 .price(product.getPrice())
													 .articles(mapper.convertModelToDao(product.getArticles())).build()));

	}

	/*
	* Method to get all products with availability stocks from Products collection
	* 
	* @return listOfProducts of type Product
	* 
    */
	public List<Product> getAllProducts() {

		List<Product> listOfProducts = new ArrayList<>();
		List<ProductDao> list = productRepo.findAll();

		setAvailability(list);
		list.forEach(product -> listOfProducts.add(
				Product.builder()
				       .id(product.getId())
				       .name(product.getName())
				       .price(product.getPrice())
				       .articles(mapper.convertDaoToModel(product.getArticles())).build()));
		return listOfProducts;
	}
	

   /*
	* Method to delete a product and update stock of it's articles
	* 
	* @param String productId product to be sold/deleted
	* 
	* @return boolean to indicate the product successfully deleted
	* 
    */
	public boolean deleteProduct(String productId) {

		Optional<ProductDao> product = productRepo.findById(productId);
		if (product.isPresent()) {
			product.get().getArticles().forEach(article -> {
				Optional<ArticleDao> articleDao = articlesRepo.findById(article.getArtId());
				if(articleDao.isPresent() && article.getAmountOf()<= articleDao.get().getStock()) {
					 articleDao.get().setStock(articleDao.get().getStock()-article.getAmountOf());
				}
				else {
					logger.error("Insufficient stocks found with articleId: {} for product :{}",article.getArtId(),productId);
					throw new OutOfStockException("Insufficient stocks: " + article.getArtId() +" for the product: " + productId);
				}
				articlesRepo.save(articleDao.get());
				
			});
		}
		else {
			logger.error("Product With Id: {} does not exist",productId);
			throw new ProductNotFoundException("Product With Id: "+ productId +" does not exist");
		}
		
		productRepo.deleteById(productId);
		return true;
	}


	

   /*
	* Method to set availability of stocks for articles
	* 
	* @param List<ProductDao> products 
	* 
	*/
	private void setAvailability(List<ProductDao> products) {

		Map<String, Long> remainingStock = new HashMap<>();

		products.forEach(product -> {
			for (PArticleDao pArticle : product.getArticles()) {
				if (remainingStock.get(pArticle.getArtId()) == null) {
					Optional<ArticleDao> article = articlesRepo.findById(pArticle.getArtId());
					if (article.isPresent() && article.get().getStock() >= pArticle.getAmountOf()) {
						pArticle.setAvailability(ArticleStock.INSTOCK.getAvailability());
						remainingStock.put(pArticle.getArtId(), article.get().getStock() - pArticle.getAmountOf());
					} else
						pArticle.setAvailability(ArticleStock.OUTOFSTOCK.getAvailability());
				} else {
					long remStck = remainingStock.get(pArticle.getArtId());
					if (remStck >= pArticle.getAmountOf()) {
						pArticle.setAvailability(ArticleStock.INSTOCK.getAvailability());
						remainingStock.put(pArticle.getArtId(), remStck - pArticle.getAmountOf());
					} else
						pArticle.setAvailability(ArticleStock.OUTOFSTOCK.getAvailability());
				}
			}
		});

	}
	
	
	/*
	* Method to get a product with availability stocks from Products collection
	* 
	* @return product of type Product
	* 
    */
	public Product getProduct(String productId) {

		Optional<ProductDao> productDao = productRepo.findById(productId);
		
		if(productDao.isPresent()) {
			setAvailability(Arrays.asList(productDao.get()));
			return Product.builder()
				       .id(productDao.get().getId())
				       .name(productDao.get().getName())
				       .price(productDao.get().getPrice())
				       .articles(mapper.convertDaoToModel(productDao.get().getArticles())).build();
		}
    	logger.error("Product With Id: {} does not exist",productId);
		throw new ProductNotFoundException("Product With Id: "+ productId +" does not exist");
	}
	
	
}
