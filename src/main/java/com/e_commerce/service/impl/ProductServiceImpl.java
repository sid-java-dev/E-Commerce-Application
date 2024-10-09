package com.e_commerce.service.impl;

import com.e_commerce.exception.ProductException;
import com.e_commerce.model.Brand;
import com.e_commerce.model.Category;
import com.e_commerce.model.Product;
import com.e_commerce.repository.BrandRepository;
import com.e_commerce.repository.CategoryRepository;
import com.e_commerce.repository.ProductRepository;
import com.e_commerce.service.ProductService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger= LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, BrandRepository brandRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }

    // save product
    @Transactional
    public Product saveProduct(Product product){

        //Input validation
        if(product.getName()==null || product.getName().isEmpty()){
            throw new ProductException("product name can not be null or empty");
        }
        if(product.getPrice()<0){
            throw new ProductException("Product price can not be negative");
        }

        //validate Brand
        if(product.getBrand()==null || product.getBrand().getId()==null){
            throw new ProductException("Brand must be specified");
        }

        Optional<Brand> brandOpt=brandRepository.findById(product.getBrand().getId());
        if(brandOpt.isEmpty()){
            throw new ProductException("specified brand does not exists");
        }
     //validate category

        if(product.getCategory()==null || product.getCategory().getId()==null){
            throw new ProductException("category must be specified");
        }
        Optional<Category>categoryOpt=categoryRepository.findById(product.getCategory().getId());
        if(categoryOpt.isEmpty()){
            throw new ProductException("specified category does not exists");
        }

        //  check product is already present or not
        Optional<Product> op=productRepository.findByName(product.getName());

        if(op.isPresent()){
            throw new ProductException("product is already added");
        }
        //Log the product saving action
        logger.info("saving product : {}",product);
        //save product
        product.setBrand(brandOpt.get());
        product.setCategory(categoryOpt.get());
        return productRepository.save(product);

    }


}
