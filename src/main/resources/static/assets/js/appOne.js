var app = angular.module('angularTable',['angularUtils.directives.dirPagination']);


app.controller('listdata',function($scope, $http,$location,$window){
	$scope.users = []; //declare an empty array$http.get("mockJson/mock.json").success(function(response){ 
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
         $scope.id1  = "./viewer1.html#?id=" + $location.search()['id1'];
         $scope.id2  = "./viewer1.html#?id=" + $location.search()['id2'];

        var selected = $scope.selected = [];
      
        $scope.ReadCookie = function (checked, id) {
        	alert('read cookieee apponee');
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
        	window.location.replace("http://localhost:82/search_patient_compare.html#?id1=" + $scope.selected[0] +"&id2=" + $scope.selected[1]);
        };

		
       
        	$scope.getpatients = function (search) {
        	 if($scope.search.length >0)
        {
        		 alert('getpatients')

        	$.post("/getDicomDataByID/1",{"mrNumber":search})
           .success(function(data){ 
          alert('hll')
           	$scope.users = data;
           });

           }
        };

        	
        


  
	if($scope.id1 >0 || $scope.id2)
	{
		//console.log("id1 values called" + $scope.id1);
		$http.get("/getDicomDataByID"+ $scope.id1).success(function(child){ 
			 //	console.log($scope.child);
			$scope.child = child;  

			$http.get("http://localhost:82/service/ah.asmx/GET_IMAGE_DETAILS?ID="+$scope.id).success(function(review){ 
			 	$scope.examreview = review; 
			 	//console.log($scope.examreview);

			});

			$http.get("http://localhost:82/service/ah.asmx/GET_IMAGE_REVIEW?imageId="+$scope.id).success(function(image){ 
			 	$scope.imagereview = image; 
			 	//console.log("Image Review");
			 //	console.log($scope.imagereview);

			});



		});

	}
 	if($scope.id >0){
 		alert('inside if')
		$http.get("/getDicomDataByID"+ $scope.id).success(function(child){ 
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
		 alert('inside appOne')
	 	$http.get("/getDicomDataByID").success(function(child){ 
		 	
			$scope.child = child;  
		});
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
    	   alert('llll')
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



		$scope.sort = function(keyname){
			$scope.sortKey = keyname;
			$scope.reverse = !$scope.reverse;
		}
});













