package com.store.management.storemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.management.storemanagement.domain.Like;

public interface LikeRepository  extends JpaRepository<Like, Long>{
	public Like save(Like like);
}
