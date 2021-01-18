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
	
	
})


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
	$(".publish_btn").on("click",function(){
		
		var articleTitle = $(".articleTitle").val();
		
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
			url:"../../article/article",
			data:{
				articleTitle:$(".articleTitle").val(),
				article:testEditor.getMarkdown()
			},
			success:function(data){
				alert(data.flag);
				url = window.location.pathname;
				url = url.substring(url.lastIndexOf('/') + 1, url.length);
				window.location.href="../course/"+url;
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