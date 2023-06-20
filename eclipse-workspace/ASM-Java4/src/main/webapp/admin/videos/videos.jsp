<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="col mt-4">
	<jsp:include page="/common/inform.jsp"></jsp:include>
	<ul class="nav nav-tabs" id="myTab" role="tablist">
		<li class="nav-item" role="presentation">
			<button class="nav-link active" id="videoEditing-tab"
				data-toggle="tab" data-target="#videoEditing" type="button"
				role="tab" aria-controls="videoEditing" aria-selected="true">Video
				Editing</button>
		</li>
		<li class="nav-item" role="presentation">
			<button class="nav-link" id="videoList-tab" data-toggle="tab"
				data-target="#videoList" type="button" role="tab"
				aria-controls="videoList" aria-selected="false">Video List</button>
		</li>

	</ul>
	<div class="tab-content" id="myTabContent">
		<div class="tab-pane fade show active" id="videoEditing"
			role="tabpanel" aria-labelledby="videoEditing-tab">
			<form action="" method="post" enctype="multipart/form-data">
				<div class="card">
					<div class="card-body">
						<div class="row">
							<div class="col-3">
								<div class="border p-3">
									<img src="${video.poster !=null?video.poster:'images/computer.png' }" alt="" class="img-fluid">
									<div class="input-group mb-3 mt-3">
										<div class="input-group-prepend">
											<span class="input-group-text">Upload</span>
										</div>
										<div class="custom-file">
											<input type="file" class="custom-file-input" id="cover"
												name="cover"> <label for="cover">Choose File</label>
										</div>
									</div>
								</div>
							</div>
							<div class="col-9">
								<div class="form-group">
									<label for="youtubeId">Youtube ID</label> <input type="text"
										class="form-control" name="videoId" id="youtubeId" value="${video.videoId}"
										aria-describedby="youtubeId" placeholder="Youtube ID">
									<small id="youtubeId" class="form-text text-muted">Youtube
										id is required</small>
								</div>
								<div class="form-group">
									<label for="videoTitle">Video Title</label> <input type="text"
										class="form-control" name="title" id="videoTitle" value="${video.title}"
										aria-describedby="videoTitleId" placeholder="Video Title">
									<small id="videoTitleId" class="form-text text-muted">Video
										title is required</small>
								</div>
								<div class="form-group">
									<label for="viewCount">View Count</label> <input type="text"
										class="form-control" name="views" id="viewCount" value="${video.views}"
										aria-describedby="viewCountId" placeholder="View Count">
									<small id="viewCountId" class="form-text text-muted">View 
										count is required</small>
								</div>
								<div class="form-check form-check-inline">
									<label for=""><input type="radio" name="active"
										id="status" class="form-check-input" value="true" ${video.active? 'checked':''}>
										Active</label> <label for=""><input type="radio" name="active"
										id="status" class="form-check-input" value="false" ${! video.active? 'checked':''}>
										InActive</label>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col">
								<label for="description">Desciption</label>
								<textarea name="description" id="description" cols="30" rows="4" 
									class="form-control">${video.description}</textarea>
							</div>
						</div>
					</div>
					<div class="card-footer text-muted">
						<button class="btn btn-primary" formaction="VideoManagement/create">Create</button>
						<button class="btn btn-warning" formaction="VideoManagement/update">Update</button>
						<button class="btn btn-danger" formaction="VideoManagement/delete">Delete</button>
						<button class="btn btn-info" formaction="VideoManagement/reset">Reset</button>
					</div>
				</div>
			</form>
		</div>
		<div class="tab-pane fade" id="videoList" role="tabpanel"
			aria-labelledby="videoList-tab">
			<table class="table table-stripe">
				<tr>
					<td>Youtube Id</td>
					<td>Video Title</td>
					<td>View Count</td>
					<td>Status</td>
					<td>&nbsp;</td>
				</tr>
				<c:forEach var="item" items="${videos }">
				<tr>
					<td>${item.videoId }</td>
					<td>${item.title }</td>
					<td>${item.views }</td>
					<td>${item.active? 'Active':'Deactive' }</td>
					<td><a href="VideoManagement/edit?videoId=${item.videoId }"><i class="fa fa-edit" aria-hidden="true"></i>Edit</a>
						<a href="VideoManagement/delete?videoId=${item.videoId }"><i class="fa fa-trash" aria-hidden="true"></i>Delete</a>
					</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>