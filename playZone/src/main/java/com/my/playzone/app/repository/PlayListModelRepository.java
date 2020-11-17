package com.my.playzone.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.playzone.app.model.PlayListModel;

public interface PlayListModelRepository extends JpaRepository<PlayListModel, Integer> {

}
