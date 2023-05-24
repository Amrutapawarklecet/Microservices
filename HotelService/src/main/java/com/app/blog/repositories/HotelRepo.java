package com.app.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.blog.entities.Hotel;


@Repository
public interface HotelRepo extends JpaRepository<Hotel, String>{


}