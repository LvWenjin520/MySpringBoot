/**
 * 
 */
/**
 * 
 */

$(function(){
	//日历控件
	date();
	
	var testEditor;
	//打开编辑器
	openEditor();
	//发布文章
	publishArticle();
	
	//预览md
	showMdArticle();
	
	//获取所有的文章
	getMyArticle();
	
	
})


function getMyArticle(){
	$.ajax({
		type:"get",
		url:"/blog/blog",
		success:function(data){
			if(data == undefined){
				return;
			}
			for(var i=data.length-1;i>=0;i--){
				var title = "<h5 class='card-title'>"+data[i].title+"</h5>";
				var article = "<p class='card-text'>"+data[i].arcitle.substr(0,30)+'...'+"</p>";
				var readMore = "<a href='#' class='card-link'>Read more-></a>";
				var modification = "<a href='#' class='card-link'>modification</a>";
				var deleteArticle = "<a id="+data[i].id+" href='#' class='card-link text-danger deleteArticleLink'>delete</a>";
				var date = "<span class='float-right'>"+data[i].createDate+"</span>";
				$(".blogparent").append(
					$("<div>",{
						"class":"card",
						"style":"width: 100%;margin-bottom:20px;",
					}).append(
						$("<div>",{
							"class":'card-body'
						}).append(title,article,readMore,modification,deleteArticle,date)
					)
				)
			}
			
			var count = 0;
			if(data.length == 1){
				var dateinfo = "<a href='#'>"+date+"</a>"
				var articleCount = "<span class='badge badge-primary badge-pill'>1</span>"
				$(".datelist").append($("<li>",{
						"class":"list-group-item d-flex justify-content-between align-items-center"
					}).append(dateinfo,articleCount)
				);
			}else{
				
				
				var dateSet = [];
				
				for(var i =0 ;i<data.length;i++){
					if($.inArray(data[i].createDate,dateSet) == -1){
						dateSet.push(data[i].createDate);
					}
				}
				
				while(dateSet.length > 0){
					for(var i =0 ;i<data.length;i++){
						if(data[i].createDate == dateSet[0]){
							count++;
						}
					}
					var dateinfo = "<a href='#'>"+dateSet[0]+"</a>"
					var articleCount = "<span class='badge badge-primary badge-pill'>"+count+"</span>"
					$(".datelist").append($("<li>",{
							"class":"list-group-item d-flex justify-content-between align-items-center"
						}).append(dateinfo,articleCount)
					);
					count = 0;
					dateSet.shift();
				}
				
				/*for(var i= 0;i<=data.length;i++){
					if(date == data[i].createDate){
						count++;
					}else if(date != data[i].createDate || data[i] == undefined){
						var dateinfo = "<a href='#'>"+date+"</a>"
						var articleCount = "<span class='badge badge-primary badge-pill'>"+count+"</span>"
						$(".datelist").append($("<li>",{
								"class":"list-group-item d-flex justify-content-between align-items-center"
							}).append(dateinfo,articleCount)
						);
						date = data[i].createDate;
						count=0;
					}
				}*/
			}
			
			
			
			deleteArticleEven();
		}
	});
}

//为删除按钮绑定事件
function deleteArticleEven(){
	$(".deleteArticleLink").on('click',function(){
		
		var articleId = $(this).attr('id');
		
		var elem = $(this);
		
		//console.log(elem.parent().parent());
		
		$("#exampleModalCenter").modal("show");
		
		$(".ensureDelete").on('click',function(){
			$.ajax({
				url:"/blog/blog/"+articleId,
				type : "DELETE",
				success:function(data){
					alert(data.msg);
					location.href="/hello";
				}
			});
		});
	});
}

//日历控件
function date(){
	$(".date_input").datetimepicker({
		weekStart: 0, //一周从哪一天开始
        todayBtn:  1, //
        autoclose: 1,
        todayHighlight: 1,
        todayBtn:'linked',
        startView: 2,
        minView : "month",
        forceParse: 0,
        showMeridian: 1,
        //language:'zh-CN',
        format : 'yyyy-mm-dd',//设置日期格式,
    });
}




//发布文章
function publishArticle(){
	$(".report_btn").on("click",function(){
		
		var articleTitle = $("#writeblog").val();
		
		var article = testEditor.getMarkdown();
		
		if(articleTitle == ""){
			alert("请输入标题");
			return;
		}
		
		if(article.length<=30){
			alert("文章内容不得少于30个字");
			return;
		}
		
		$.ajax({
			type:"post",
			url:"/blog/blog",
			data:{
				articleTitle:$("#writeblog").val(),
				article:testEditor.getMarkdown()
			},
			success:function(data){
				alert(data.msg);
				location.href="/hello";
			}
		});
	});
}

//预览md
function showMdArticle(){
	var showEditor;
	$(".preview").on("click",function(){
		
		//先清空预览内容
		$("#showMd").empty();
		
		//再追加div
		$("#showMd").append(
			$("<textarea>",{
				"style":"display: none;"
			})	
		);
		
		//获取md格式的数据
		var preview = testEditor.getMarkdown();
		
		//插入标题
		$(".preview_article_title").text($(".articleTitle").val());
		
		//插入md格式的数据
		$("#showMd textarea").text(preview);
		
		//解析并显示
		showEditor = editormd.markdownToHTML("showMd", {//注意：这里是上面DIV的id
	        htmlDecode : "style,script,iframe",
	        emoji : true,
	        taskList : true,
	        tex : true, // 默认不解析
	        flowChart : true, // 默认不解析
	        sequenceDiagram : true, // 默认不解析
	        codeFold : true
	    });
	})
}



//打开md输入框
//md输入框的
function openEditor(){
	
	testEditor = editormd({
		id:"editormd",//注意：这里是上面DIV的id
		width:"90%",
		height:"600px",
		syncScrolling: "single",
		path:"../../asstes/plugins/mdEditor/lib/",
		emoji : true,
		imageUpload : false,
		autoFocus: false
	});
}