var app = angular.module('angularSearch',['angularUtils.directives.dirPagination']);


app.controller('searchController',function($scope, $http,$location,$window){
	$scope.users = [];  
		$scope.select = [];
		$scope.child = [];
		$scope.search=[];
		$scope.patients=[];
		$scope.selected=[];
		$scope.disabled= true;
		$scope.examreview =[];
		$scope.imagereview =[];
   
		 //alert("Query Id is - " + $location.search()['id']);
         $scope.id  = $location.search()['id'];
         $scope.id1  = "./ivservice/viewer1.html#?id=" + $location.search()['id1'];
         $scope.id2  = "./ivservice/viewer1.html#?id=" + $location.search()['id2'];

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
        	
        	//console.log($scope.selected);
        	//if($scope.selected.count ==2)
        	window.location.replace("/ivservice/compare.html#?id1=" + $scope.selected[0] +"&id2=" + $scope.selected[1]);
        };

		
       
        	$scope.getpatients = function (search) {
        		alert('getPatients called');
        	 if($scope.search.length >0)
        {

        	$.post("http://localhost:82/service/ah.asmx/PATIENT_MASTER",{"mrNumber":search})
           .success(function(data){ 
          
           	$scope.users = data;
           });

           }
        };

        	
        



	
 	if($scope.id >0){
 		
 		alert('inside iffff')
		$http.get("/ivservice/getDicomDataByID"+ $scope.id).success(function(child){ 
			 	//console.log($scope.child);
			$scope.child = child;  

			$http.get("http://localhost:82/service/ah.asmx/GET_IMAGE_DETAILS?ID="+$scope.id).success(function(review){ 
			 	$scope.examreview = review; 
			 	//console.log($scope.examreview);

			});

			$http.get("http://localhost:82/service/ah.asmx/GET_IMAGE_REVIEW?imageId="+$scope.id).success(function(image){ 
			 	$scope.imagereview = image; 
			 //	console.log("Image Review");
			 	//console.log($scope.imagereview);

			});



		});


	 }
	 else
	 {
		 $http.get("/ivservice/getPatients").success(function(patients){ 
		 	$scope.patients = patients;
			
		});
	 }
		$scope.sort = function(keyname){
			$scope.sortKey = keyname;
			$scope.reverse = !$scope.reverse;
		}
		
		$scope.loadExams = function (patientId) {
			$http.get('/ivservice/getExamsByPatientID?patientId='+patientId).success(function(data) {
				var examReviews = {};
				examReviews.patientId =patientId;
				examReviews.reviews=data;
				alert(JSON.stringify(examReviews));
		       $scope.examreview = examReviews;
		     });
		  };
		  
		  $scope.loadImages = function (examId) {
				$http.get('/ivservice/getImagesByExamID?examId='+examId).success(function(data) {
			       $scope.imagereview = data;
			     });
			  };  

});













