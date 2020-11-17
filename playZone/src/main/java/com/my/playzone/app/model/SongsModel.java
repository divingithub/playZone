package com.my.playzone.app.model;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="pz_songs")
public class SongsModel 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Auto Generated Id")
	int id;
	
	@ApiModelProperty(notes = "Song Name",required = true)
	String name;
	
	@ApiModelProperty(notes = "Singer Name",required = true)
	String singer;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="play_id")
	@ApiModelProperty(notes = "Play List Name")
	PlayListModel playListModel;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public PlayListModel getPlayListModel() {
		return playListModel;
	}

	public void setPlayListModel(PlayListModel playListModel) {
		this.playListModel = playListModel;
	}

	@Override
	public String toString() {
		return "SongsModel [id=" + id + ", name=" + name + ", singer=" + singer + ", playListModel=" + playListModel
				+ "]";
	}
	
	
}
