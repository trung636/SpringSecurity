package com.spring.security.demo.mapping;


public interface IMapping<T, D> {

	D toDTO(T t);

	T toEntity(D d);
	

}
