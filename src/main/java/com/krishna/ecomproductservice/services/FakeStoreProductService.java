package com.krishna.ecomproductservice.services;

import com.krishna.ecomproductservice.dtos.ProductDto;
import com.krishna.ecomproductservice.models.Category;
import com.krishna.ecomproductservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements IProductService {

    private final RestTemplate restTemplate;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Product> getAllProducts() {
        ResponseEntity<List<ProductDto>> response = restTemplate.exchange(
                "https://fakestoreapi.com/products/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductDto>>() {}
        );
        List<Product> products = new ArrayList<>();
        for(ProductDto pdto : response.getBody()){
            Product product = convertProductDtoToProduct(pdto);
            products.add(product);
        }
        return products;
    }

    @Override
    public Product getSingleProduct(Long productId) {
        ResponseEntity<ProductDto> responseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products/" + productId, ProductDto.class);
        return convertProductDtoToProduct(responseEntity.getBody());
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        return null;
    }

    private Product convertProductDtoToProduct(ProductDto productDto) {
        return null;
//        Product product = new Product();
//        product.setId(productDto.getId());
//        product.setProductName(productDto.getTitle());
//        product.setDescription(productDto.getDescription());
//        product.setPrice(productDto.getPrice());
//        Category category = new Category();
//        category.setCategoryName(productDto.getCategory());
//        product.setCategory( category);
//        return product;
    }
}
