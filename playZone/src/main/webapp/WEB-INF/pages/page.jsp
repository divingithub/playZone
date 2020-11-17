
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PlayZone</title>
</head>

<style>
	table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
  padding: 10px;
  margin: 10px
}

.container {
  height: 50px;
  position: relative;
  border: 3px solid green;
}

.center {
  margin: 0;
  position: absolute;
  top: 50%;
  left: 50%;
  -ms-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
}

.green {
	    color: green;
}

</style>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script
  src="https://code.jquery.com/jquery-3.5.1.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" ></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.5/angular.min.js"></script>
<script type="text/javascript" src="./app.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js" ></script>


<body ng-app="playzone" ng-controller="pzcntrl">
	<h1>PlayZone</h1>
	
	

<!-- Modal -->
<div id="playlist" class="modal fade" role="playlist" data-keyboard="false" data-backdrop="static">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Add Play List</h4>
        <!-- <button type="button" class="close" data-dismiss="modal">&times;</button> -->
      </div>
      <form ng-submit="savePlayList()">
      <div class="modal-body">
      <label>Name</label>
        <input type="text" maxlength="255" ng-model="playListModel.name" required>
      </div>
      <div class="modal-footer">
        <button type="submit"  class="btn btn-default" >add</button>
        <a  class="btn btn-default" ng-click="playListModel={}" data-dismiss="modal">Close</a>
      </div>
      </form>
      
    </div>

  </div>
</div>

<div id="addSongs" class="modal fade" role="songs" data-keyboard="false" data-backdrop="static">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Add Sons List to <span class="green"> {{playListModel.name}}</span></h4>
       <!--  <button type="button" class="close" data-dismiss="modal">&times;</button> -->
      </div>
       <form ng-submit="saveSongsToPlayList()">
      <div class="modal-body">
      <label>Song</label>
        <input type="text" maxlength="255" ng-model="songsModel.name" required>
        <label>Singer</label>
         <input type="text" maxlength="255" ng-model="songsModel.singer" required>
      </div>
      <div class="modal-footer">
        <button type="submit"  class="btn btn-default" >add</button>
        <a  class="btn btn-default" data-dismiss="modal">Close</a>
      </div>
      </form>
    </div>

  </div>
</div>

	
		<table class="col-12" >
		<thead>
				<th>Name</th>
				<th>Songs</th>
				<th>Action</th>
			</thead>
			<tbody>
			
				<tr ng-repeat="p in playLists">
					<td  class="coll-4">{{p.name}}</td>
					<td  class="coll-5">
						<span class="col-12" ng-hide="p.songs.length!==0">No Songs Added</span>
						<ul class="col-12" ng-show="p.songs.length!==0">
							<li class="row col-12" ng-repeat="s in p.songs">
								<div class="col-5">{{s.name}}</div>
								<div class="col-5">{{s.singer}}</div>
								<div class="col-2"><a href ng-click="deleteSongFromPlayList(p,s,$index)" >Delete</a></div>
								
							</li>
						</ul>
					</td>
					<td class="coll-3">
						<a href ng-click="songModal(p)" class="col-6">Add</a>
						<a href class="col-6" ng-click="deletePlayList(p,$index)">Delete</a></td>
				</tr>
				</tbody>
				<tfoot>
					<tr >
						<td colspan='3' class="container"  >
		
    <a href data-toggle="modal" class="center" data-target="#playlist">Add new play list</a>

</td>
					</tr>
				</tfoot>
		</table>
</body>
</html>