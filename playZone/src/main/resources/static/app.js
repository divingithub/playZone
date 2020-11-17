var app = angular.module('playzone', []);
app.controller('pzcntrl',function($scope,$http) 
{
			
			var url = window.location.href;
			var arr = url.split("/");
			$scope.HOST_NAME = arr[0] + "//" + arr[2] + "/" + arr[3];
			var socketAddress="//" + arr[2] + "/" + arr[3];
			var ws,connection=0,webScketStatus=true;

			var loadAllData=()=>{
				$http.get($scope.HOST_NAME+"/get").then((res)=>$scope.playLists=res.data)
			}
			
			angular.element(document).ready(()=>{ loadAllData();});
			
			$scope.savePlayList=()=>{
				
				$http.post($scope.HOST_NAME+"/playlist/post",$scope.playListModel).then(r=>{
					
					if(r.data!==null)
					{	
						$("#playlist").modal("hide")
						if(!webScketStatus)
						$scope.playLists.push(r.data);
						$scope.playListModel={};
					}
					else
						alert("Somthimg went wrong. Try again..!")
				})
				
			}
			$scope.songModal=(p)=>
			{
				$scope.playListModel=p;
				$("#addSongs").modal('show');
				
			}
			
			$scope.saveSongsToPlayList=()=>
			{
				
					$scope.songsModel.playListModel=$scope.playListModel;
					
					$http.post($scope.HOST_NAME+"/playlist/songs/post",$scope.songsModel).then(r=>{
					
					if(r.data!==null)
					{	
						$("#addSongs").modal("hide");
						if(!webScketStatus)
						$scope.playListModel.songs.push(r.data);
						
						$scope.playListModel={};
						$scope.songsModel={};
						
					}
					else
						alert("Somthimg went wrong. Try again..!")
				});
			}
			
			
			$scope.deletePlayList=(data,index)=>
			{
				console.log(index," => ",data)
				if(window.confirm("are you sure?"))
				{
					$http.post($scope.HOST_NAME+"/playlist/delete",data).then(r=>{
						console.log(r)
						if(r.data)
						{	if(!webScketStatus)
								$scope.playLists.splice(index,1);
						}
					});
					
				}
				
			}
			
			$scope.deleteSongFromPlayList=(plays,data,index)=>
			{
				console.log(index," => ",plays," => ",data)
				if(window.confirm("are you sure?"))
				{
					$http.post($scope.HOST_NAME+"/playlist/songs/delete",data).then(r=>{
						console.log(r)
						if(r.data)
						{	
							if(!webScketStatus)
							plays.songs.splice(index,1);
						}
					});
					
				}
				
			}
			
			
			
			function connect() 
			{
				console.log(socketAddress)
				var socket = new WebSocket('ws:'+socketAddress+'/notfication');
				ws = Stomp.over(socket);

				ws.connect({}, function(frame) {
					ws.subscribe("/update/errors", function(message) {
						console.log("Error " + message.body);
					});

					ws.subscribe("/update/get", function(message) {
						console.log("message",message);
						loadAllData();
						
					});
				}, function(error) {
					console.log("STOMP error " + error);
					if(connection<4)
						connect();
					else
						webScketStatus=false;
					connection++;
					
				});
			}
			connect();
			function disconnect() {
			    if (ws != null) {
			        ws.close();
			    }
			    setConnected(false);
			    console.log("Disconnected");
			}


			

			
			
			
			
			
			


			
});