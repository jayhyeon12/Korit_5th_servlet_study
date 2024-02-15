package com.study.servlet_study.test;

import java.util.function.Consumer;
import com.study.servlet_study.entity.Author;


class Print<T> implements Consumer<T> { 
	@Override
	public void accept(T t) {
		System.out.println(t);
	}
}

public class LambdaMain {
	
	public static void main(String[] args) {
		Consumer<Author> print0 = new Print<Author>();
		
		Consumer<Author> print1 = new Consumer<Author>() {
			@Override
			public void accept(Author author) {
				System.out.println(author);
			}
		};
		
		Consumer<Author> print2 = (author) -> System.out.println(author);
		
		print0.accept(Author.builder().authorId(1).authorName("000").build());
		
		forEach(print2);
		
		forEach(author -> {
			
			System.out.println(author);
		});
	 
	}
	
	public static void forEach(Consumer<Author> action) {
		action.accept(Author.builder().authorId(0).authorName(null).build());
	}

}