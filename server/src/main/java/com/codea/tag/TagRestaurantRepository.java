package com.codea.tag;

import org.mapstruct.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.OptionalDouble;


public interface TagRestaurantRepository extends JpaRepository<TagRestaurant, Long> {

}