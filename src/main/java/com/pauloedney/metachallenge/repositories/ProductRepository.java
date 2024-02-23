package com.pauloedney.metachallenge.repositories;

import com.pauloedney.metachallenge.models.ProductModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ProductRepository extends MongoRepository<ProductModel, String> {
  ProductModel findByCode(String code);
  List<ProductModel> findByUserId(String userId);
}
