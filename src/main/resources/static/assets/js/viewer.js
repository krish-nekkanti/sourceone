var app = angular.module('angularViewer',['angularUtils.directives.dirPagination']);
app.controller('viewerController',function($scope, $http,$location,$window){
	$scope.users = []; //declare an empty array$http.get("mockJson/mock.json").success(function(response){ 
		$scope.select = [];
		$scope.child = [];
		$scope.search=[];
		$scope.patients=[];
		$scope.patient;
		$scope.selected=[];
		$scope.disabled= true;
		$scope.examreview =[];
		$scope.imagereview =[];
		$scope.currentImage=[];
		$scope.examImageId;
		var canvas = new fabric.Canvas('canvas');
   
         $scope.id  = $location.search()['id'];
         $scope.id1  = "/ivservice/comparePage#?id=" + $location.search()['id1'];
         $scope.id2  = "/ivservice/comparePage#?id=" + $location.search()['id2'];
         
         $scope.left  = "/#?id=" + $location.search()['id1'];
         $scope.right  = "/#?id=" + $location.search()['id1'];

        var selected = $scope.selected = [];
      
        $scope.ReadCookie = function (checked, id) {
               	  if (checked) {
				    $scope.selected.push(id);
				    
				  }
				  else {
				    var index = $scope.selected.indexOf(id);
				    $scope.selected.splice(index,1);
				   
		        }
		         if($scope.selected.length == 2)
				    	$scope.disabled= false;
				    else
				    	$scope.disabled = true;
        };

        $scope.Compare = function () {
        	alert('inside compare')
        	//console.log($scope.selected);
        	//if($scope.selected.count ==2)
        	window.location.replace("http://localhost:82/search_patient_compare.html#?id1=" + $scope.selected[0] +"&id2=" + $scope.selected[1]);
        };

		
       
        	$scope.getpatients = function (search) {
        	 if($scope.search.length >0)
        {

        	$.post("http://localhost:82/service/ah.asmx/PATIENT_MASTER",{"mrNumber":search})
           .success(function(data){ 
          
           	$scope.users = data;
           });

           }
        };

        	
        


      
	if($scope.left >0 || $scope.id2)
	{
		console.log("id1 values called" + $scope.id);
		$http.get("/ivservice/getDicomImageDataByID?id="+ $scope.id).success(function(child){ 
			 //	console.log($scope.child);
			$scope.patient = child.context.entity.patientExam.dicomPatient;
			$scope.examreview = child.context.entity.patientExam;
			$scope.imagereview = child.context.entity.patientExam.examImagesList;
			$scope.currentImage = child.context.entity;



		});

	}
 	if($scope.id >0){
 		$http.get("/ivservice/getDicomImageDataByID?id="+ $scope.id).success(function(child){ 
			 	//console.log($scope.child);
			$scope.patient = child.context.entity.patientExam.dicomPatient;
			$scope.examreview = child.context.entity.patientExam;
			$scope.imagereview = child.context.entity.patientExam.examImagesList;
			$scope.currentImage = child.context.entity;
			console.log('child.context.entity.examImageId--->'+child.context.entity.examImageId)
			$scope.setCanvas(child.context.entity.examImageId);
		});


	 }
	 else
	 {
	 	/*$http.get("/getDicomImageDataByID",{"id":$scope.id}).success(function(child){ 
			$scope.child = child.entity;
			$scope.imagereview = child.entity;
			$scope.examreview = child.entity;
		});*/
	 }

 $scope.GetReviews = function(){
		$http.get("http://localhost:82/service/ah.asmx/GET_IMAGE_REVIEW?imageId="+$scope.id).success(function(image){ 
			 	$scope.imagereview = image; 
			 	//console.log("Image Review");
			 //	console.log($scope.imagereview);

			});
    };


	 $scope.RemoveReview = function(imageid){
		 $http.get("http://localhost:82/service/ah.asmx/DELETE_IMAGE_REVIEW?imageId="+ imageid).success(function(gg){ 
		 $http.get("http://localhost:82/service/ah.asmx/GET_IMAGE_REVIEW?imageId="+$scope.id).success(function(image){ 
			 	$scope.imagereview = image; 
			 	//console.log("Image Review");
			 //	console.log($scope.imagereview);

			});
	});
    };
 	   $scope.user = {};
       $scope.submitForm = function() {
          $http({
          method  : 'POST',
          url     : 'http://localhost:82/service/ah.asmx/SAVE_ANNOTATION?ID=2&strAnno=thisis second',
          data    :  $scope.user,
          headers :  {'Content-Type': 'application/x-www-form-urlencoded'} 
         })
          .success(function(data) {
            if (data.errors) {
              console.log('anno saved');            
            } else {
              $scope.message = data.message;
            }
          });
        };
        
        $scope.setCanvas = function(examImageId){
        	debugger;
        	console.log(examImageId);
        	$scope.getImageData(examImageId);
        	canvas.setBackgroundImage('/ivservice/assets/img/eyes/' + examImageId
					+ '.png', canvas.renderAll.bind(canvas), {
				// Needed to position backgroundImage at 0/0
				originX : 'left',
				originY : 'top',
				width : '600',
				height : '480',
			});
        	$http.get("/ivservice/getAnnotationByImageID?imageId="+ examImageId).success(function(annotation){
        		if(annotation.entity=='Nothig'){
        			annotation.entity={};
        		}
        		canvas.loadFromJSON(annotation.entity, function() {
    				//canvas.renderAll();
    			});
        	});
        	
        	$scope.examImageId = examImageId;
        	
        	

			/*function getScope(ctrlName) {
				var sel = 'div[ng-controller="' + ctrlName + '"]';
				return angular.element(sel).scope();
			}*/
			

			

			
		
   			
        	
        	
        };
        
        $scope.getImageData = function(imageId){
        	$http.get("/ivservice/getDicomImageDataByID?id="+ imageId).success(function(child){ 
			$scope.patient = child.context.entity.patientExam.dicomPatient;
			$scope.examreview = child.context.entity.patientExam;
			$scope.imagereview = child.context.entity.patientExam.examImagesList;
			$scope.currentImage = child.context.entity;
		});
        };
        
        
        



		$scope.sort = function(keyname){
			$scope.sortKey = keyname;
			$scope.reverse = !$scope.reverse;
		}
		
		
		$("#load").click(
				function() {
					var name = GetParameterValues('id');
					function GetParameterValues(param) {
						var url = window.location.href
								.slice(window.location.href
										.indexOf('?') + 1);

					}
					$.post("http://localhost:82/service/ah.asmx/GET_ANNOTATION",
									{
										"ID" : name
									})
							.success(
									function(data) {
										canvas
												.loadFromJSON(
														data,
														function() {
															canvas
																	.renderAll();
															//alert(' this is a callback. invoked when canvas is loaded! ');
															console
																	.log(name);
														});
									});
				});
		$("#print").click(function() {
			
			var canvas = document.getElementById("canvas");
			var dataURL = canvas.toDataURL('image/png');
			console.log('print clicked');
			var image = canvas.toDataURL("image/png"); //Convert image to 'octet-stream' (Just a download, really)
			window.location.href = image;
		});
		
		$("#export")
		.click(
				function() {

					var canvas = document
							.getElementById("canvas");
					var dataURL = canvas
							.toDataURL('image/png');
					//console.log('export clicked');
					var image = canvas.toDataURL(
							"image/png").replace(
							"image/png",
							"image/octet-stream"); //Convert image to 'octet-stream' (Just a download, really)
					window.location.href = image;
				});
		
		
		var selectedObject = "none";
		var circle, isDown, origX, origY;

		canvas.on('mouse:down',function(o) {
							isDown = true;
							var pointer = canvas
									.getPointer(o.e);
							origX = pointer.x;
							origY = pointer.y;
							if (selectedObject == "circle") {
								circle = new fabric.Circle(
										{
											left : pointer.x,
											top : pointer.y,
											radius : 50,
											strokeWidth : 5,
											stroke : document
													.getElementById('border-color').value,
											selectable : false,
											fill : "",
											originX : 'center',
											originY : 'center'
										});
								circle.selectable = true;
								canvas.add(circle);
								canvas.renderAll();
							}
						});
		canvas.on('mouse:move', function(event) {
			// Defining the procedure

			if (!isMouseDown) {
				return;
			}
			//Getting yhe mouse Co-ordinates
			if (selectedObject = "rect") {
				var posX = divPos.left;
				var posY = divPos.top;

				refRect.setWidth(Math.abs((posX - refRect
						.get('left'))));
				refRect.setHeight(Math.abs((posY - refRect
						.get('top'))));
				canvas1.renderAll();
			} else {
				canvas1.on('mouse:move', function(o) {
					if (!isDown)
						return;
					var pointer = canvas1.getPointer(o.e);
					circle.set({
						radius : Math.abs(origX - pointer.x)
					});
					canvas1.renderAll();
				});
			}

		});
		canvas.on('mouse:up', function(o) {
			isDown = false;
		});

		$("#clear").click(function() {
			// clear canvas
			canvas.isDrawingMode = false;
			canvas.clear();
		});

		$("#remove_selected").click(function() {
			// remove currently selected object
			canvas.isDrawingMode = false;
			canvas.remove(canvas.getActiveObject());
		});

		$("#add_text")
				.click(
						function() {
							console.log("add text")
							selectedObject = "text";
							canvas.isDrawingMode = false;
							var fabicText = new fabric.IText(
									'Click to change Text',
									{
										left : 100,
										top : 100,
										fill : document
												.getElementById('border-color').value
									});
							fabicText
									.set({
										fill : document
												.getElementById('bg-color').value
									});
							canvas.add(fabicText);
							canvas.setActiveObject(fabicText);
							fabicText.enterEditing();

							fabicText.hiddenTextarea.focus();
							canvas.renderAll();
						});

		$("#add_line")
				.click(
						function() {
							canvas.isDrawingMode = true;
							selectedObject = "none";
							var baseBrush = new fabric.BaseBrush();
							baseBrush.width = 5;
							canvas.freeDrawingBrush.strokeStyle = document
									.getElementById('border-color').value;
							canvas.freeDrawingBrush.color = document
									.getElementById('bg-color').value;
						});

		$("#add_rect")
				.click(
						function() {
							var rect = new fabric.Rect(
									{
										width : 100,
										borderWidth : 5,
										cornerColor : 'black',
										cornerSize : 6,
										borderSize : 2,
										strokeWidth : 5,
										stroke : document
												.getElementById('border-color').value,
										height : 100,
										left : 50,
										top : 50,

									});

							canvas.add(rect);
							// add red rectangle
							canvas.isDrawingMode = false;
						});

		$("#add_circle").click(function() {
			// add green, half-transparent circle
			canvas.isDrawingMode = false;
			selectedObject = "circle";
		});

		$("#add_pan").click(function() {
			canvas.isDrawingMode = false;
			selectedObject = "pan";
			canvas.panning = false;
		});
		$("#zoomIn").click(function() {
			// add green, half-transparent circle
			canvas.isDrawingMode = false;
			canvas.setZoom(canvas.getZoom() * 1.1);
		});
		$("#zoomOut").click(function() {
			// add green, half-transparent circle
			canvas.isDrawingMode = false;
			canvas.setZoom(canvas.getZoom() / 1.1);
		});

		var svgSrc = "cobra_snake_head.svg";

		$("#loadSVG").click(function() {
			fabric.loadSVGFromURL(svgSrc, function(object) {
				var group = new fabric.PathGroup(object, {
					left : 165,
					top : 100,
					width : 295,
					height : 211,
				});
				//set source path for svg
				group.setSourcePath(svgSrc);

				canvas.add(group);
				canvas.renderAll();
			});
		});

		$("#exportSVG").click(function() {
			var json = JSON.stringify(canvas.toJSON());
			$("body").append("<div>" + json + "</div>");

		});
		$("#save")
				.click(
						function() {
							var json = JSON.stringify(canvas
									.toJSON());
							$
									.post("/addAnnatations", {
										"imageId" : $scope.examImageId,
										"annotations" : json
									})
									.success(
											function(data) {
												confirm("Annotation Added/Updated Successfully!");
												// window.location.replace("http://localhost:82/search_patient.html");

											});

						});
		$("#btnReview")
				.click(
						function() {

							var des = document
									.getElementById("txtReview");
							$
									.post(
											"http://localhost:82/service/ah.asmx/SaveImageReview",
											{
												"imageId" : name,
												"reviewDes" : document
														.getElementById("txtReview").value,
												"reviewBy" : "UI"
											})
									.success(
											function(d) {
												confirm("Review Added Successfully!");
												var $scope = getScope('viewerController');
												$scope
														.GetReviews();
												$scope.$apply();

											});

						});
		
});













