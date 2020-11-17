package com.my.playzone.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.my.playzone.app.model.PlayListModel;
import com.my.playzone.app.model.SongsModel;
import com.my.playzone.app.repository.PlayListModelRepository;
import com.my.playzone.app.repository.SongsModelRepository;

import io.swagger.annotations.ApiOperation;

@Controller
public class PlayZoneController 
{
	
	
	  @Autowired 
	  PlayListModelRepository playListModelRepository;
	  
	  @Autowired 
	  SongsModelRepository songsModelRepository;
	  
	  @Autowired
		private SimpMessageSendingOperations messagingTemplate;
	 
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView frontPage()
	{
		return new ModelAndView("page");
	}
	
	@ApiOperation(value="View a list of available play lists",response=Iterable.class)
	@RequestMapping(value="/get", method = RequestMethod.GET,produces = "application/json")
	public @ResponseBody Object getAllList()
	{
		return playListModelRepository.findAll();
	}
	
	@ApiOperation(value="add a play lists")
	@RequestMapping(value={"/playlist/post"},method = RequestMethod.POST,produces = "application/json" )
	public @ResponseBody Object postPlayList(@RequestBody PlayListModel playListModel)
	{
		try
		{
			playListModelRepository.save(playListModel);
			messagingTemplate.convertAndSend("/update/get", "playListModel");
			return playListModel;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	@ApiOperation(value="delete a play lists")
	@RequestMapping(value={"/playlist/delete"},method = RequestMethod.POST ,produces = "application/json")
	public @ResponseBody boolean deletePlayList(@RequestBody PlayListModel playListModel)
	{
		try
		{
			playListModelRepository.delete(playListModel);
			messagingTemplate.convertAndSend("/update/get", "playListModel");
			return true;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	@ApiOperation(value="add a song to play lists")
	@RequestMapping(value="/playlist/songs/post",method = RequestMethod.POST,produces = "application/json")
	public @ResponseBody Object postSongToPlayList(@RequestBody SongsModel songsModel)
	{
		try
		{
			songsModelRepository.save(songsModel);
			messagingTemplate.convertAndSend("/update/get", "playListModel");
			return songsModel;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	@ApiOperation(value="delete a song from play lists")
	@RequestMapping(value="/playlist/songs/delete",method = RequestMethod.POST,produces = "application/json")
	public @ResponseBody boolean deleteSongToPlayList(@RequestBody SongsModel songsModel)
	{
		try
		{
			songsModelRepository.delete(songsModel);
			messagingTemplate.convertAndSend("/update/get", "playListModel");
			return true;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return true;
		}
	}
	
	
}
