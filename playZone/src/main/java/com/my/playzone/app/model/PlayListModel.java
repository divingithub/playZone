package com.my.playzone.app.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModelProperty;


@Entity
@Table(name = "pz_play_lists")
public class PlayListModel 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Auto generated Id")
	int id;
	
	@ApiModelProperty(notes = "Play List Name",required = true)
	String name;
	
	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "playListModel")
	@ApiModelProperty(notes = "Songs")
	List<SongsModel> songs=new ArrayList<SongsModel>();

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

	public List<SongsModel> getSongs() {
		return songs;
	}

	public void setSongs(List<SongsModel> songs) {
		this.songs = songs;
	}

	@Override
	public String toString() {
		return "PlayListModel [id=" + id + ", name=" + name + ", songs=" + songs + "]";
	}
	
	
}
