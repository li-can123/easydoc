var $docVontent = $('#docVontent');
var $operateBar = $('#operateBar');
var $name = $('#name');
var $parentResSel = $('#parentResSel');

var resId = ApiUtil.getParam('resId');
var projectId = ApiUtil.getParam('projectId');
var opt = ApiUtil.getParam('opt');
var isNew = opt == 'new';
var editor;

var apiTpl;

$(function(){
	if(!projectId) {
		alert('项目不存在,请前往[我的空间]创建一个新项目');
		return;
	}
	if(opt == 'update') {
		postRes(resId);
	} else {
		initNew();
	}
	
	// 请求资源
	function postRes(resId) {
		if(resId) {
			ApiUtil.post('resource.get',{id:resId},function(resp){
				var res = resp.data;
				var parentId = res.parentId;
				
				$name.val(res.name);
				$docVontent.html(res.content);
				
				$operateBar
					.html('<button type="button" class="btn btn-primary" onclick="saveRes('+resId+')">保存</button>' 
						+ ' <button type="button" class="btn btn-danger" onclick="cancelRes('+resId+')">取消</button>'
						+ '<hr/>'
						).show();
				
				initMarkdown();
				
				initFolder(projectId,parentId);
			});
		}
	}
	
	// 初始化
	function initNew() {
		initMarkdown();
		initFolder(projectId);
		
		$operateBar
		.html('<button type="button" class="btn btn-primary" onclick="saveRes('+resId+')">保存</button>' 
			+ ' <button type="button" class="btn btn-danger" onclick="cancelRes('+resId+')">取消</button>'
			+ '<hr/>'
			).show();
	}
	
	
	function initMarkdown() {
		editor = editormd("editormdView", {
	        width   : "100%",
	        height  : 640,
	        syncScrolling : "single",
//	        autoHeight      : true,
	        path    : "../editormd/lib/"
	    });
	}
	
	function initFolder(projectId,parentId) {
		ApiUtil.post('user.resource.folder.list',{id:projectId},function(resp){
			var folderList = resp.data;
			var html = [];
			for (var i = 0, iLen = folderList.length; i < iLen; i++) {
				var folder = folderList[i];
				var selectStr;
				if(parentId) {
					selectStr = folder.id == parentId ? 'selected="selected"' : '';
				} else {
					selectStr = i == 0 ? 'selected="selected"' : '';
				}
				html.push('<option value="' + folder.id + '" ' + selectStr + '>' + folder.name + '</option>');
			}
			
			$parentResSel.html(html.join(''));
		});
	}
	
	$('#newFolderBtn').click(function(){
		var name = prompt("请输入目录名称","");
		if(!name) {
			alert('目录名称不能为空');
			return false;
		}
		ApiUtil.post('resource.folder.create',{name:name,projectId:projectId},function(resp){
			var res = resp.data;
			initFolder(projectId,res.id);
		});
	});
	
	$('#btn-insertApiTpl').click(function(){
		if(!apiTpl) {
			ApiUtil.post('template.system.get',{id:1},function(resp){
				var template = resp.data;
				apiTpl = template.content;
				setMarkDown(apiTpl);
			});
		} else {
			setMarkDown(apiTpl);
		}
	});
	
	function setMarkDown(content) {
		var oldContent = editor.getMarkdown();
		editor.setMarkdown(oldContent + content);
	}
	
}); // init end~


// 保存
function saveRes(resId) {
	var url = isNew ? 'resource.create' : 'resource.update'
	var markdown = editor.getMarkdown();
	var data = {
		id:resId
		,name:$.trim($name.val())
		,content:ApiUtil.htmlEncode(markdown)
		,parentId:$parentResSel.val()
	};
	if(isNew) {
		data.projectId = projectId;
	}
	ApiUtil.post(url,data,function(resp){
		ApiUtil.goMain();
	});
}

function cancelRes(resId) {
	ApiUtil.goMain();
}