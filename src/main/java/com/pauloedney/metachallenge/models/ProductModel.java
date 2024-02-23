package com.pauloedney.metachallenge.models;

import java.util.Date;

import com.pauloedney.metachallenge.dto.ProductRequestDTO;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection = "products")
@Data
public class ProductModel {
    @Id
    private String id;

    @Field(name = "user_id")
    private String userId;

    private String name;

    @Indexed(unique = true)
    private String code;

    private boolean sent;

    @Field(name = "created_at")
    private Date createdAt = new Date();

    public ProductModel(){}

    public ProductModel(ProductRequestDTO data){
        this.userId = data.userId();
        this.name = data.name();
        this.code = data.code();
        this.sent = false;
        this.createdAt = new Date();
    }

    @Override
    public String toString() {
        return String.format(
            "ProductModel[id=%s, userId='%s', name='%s, code='%s', sent='%s', createdAt='%s']",
            id, userId, name, code, sent, createdAt);
    }
}
