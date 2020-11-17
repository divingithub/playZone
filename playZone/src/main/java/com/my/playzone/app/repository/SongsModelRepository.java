package com.my.playzone.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.playzone.app.model.SongsModel;

public interface SongsModelRepository extends JpaRepository<SongsModel, Integer> {

}
