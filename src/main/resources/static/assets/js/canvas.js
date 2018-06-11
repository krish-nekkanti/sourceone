$(document).ready(function() {
	alert('fist');

						$('#Button1').click(function() {
							location.reload();
						});

						var canvas = new fabric.Canvas('canvas');
						console.log('document ready function called');
						var name = GetParameterValues('id');

						function GetParameterValues(param) {
							var url = window.location.href
									.slice(window.location.href.indexOf('?') + 1);
							var id = url.split("=")[1];
							return id;

						}

						function UpdateImage() {
							location.reload();

						}
						//alert('name--->'+name);
						 if (name != null) {
debugger;
						

						} 

						var selectedObject = "none";
						var circle, isDown, origX, origY;

						canvas
								.on(
										'mouse:down',
										function(o) {
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
							alert('---->'+canvas.getActiveObject());
							canvas.isDrawingMode = false;
							canvas.remove(canvas.getActiveObject());
							canvas.renderAll();
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
							alert('canvas-->'+canvas)
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
														"imageId" : name,
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

						function getScope(ctrlName) {
							var sel = 'div[ng-controller="' + ctrlName + '"]';
							return angular.element(sel).scope();
						}
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

						$("#print").click(function() {

							var canvas = document.getElementById("canvas");
							var dataURL = canvas.toDataURL('image/png');
							console.log('print clicked');
							var image = canvas.toDataURL("image/png"); //Convert image to 'octet-stream' (Just a download, really)
							window.location.href = image;
						});

						$("#load")
								.click(
										function() {
											var name = GetParameterValues('id');
											function GetParameterValues(param) {
												var url = window.location.href
														.slice(window.location.href
																.indexOf('?') + 1);

											}
											$
													.post(
															"http://localhost:82/service/ah.asmx/GET_ANNOTATION",
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
					});