
$(function(){
	var resId = ApiUtil.getParam('resId');
	var projectId = ApiUtil.getParam('projectId');
	
	var $btnTop = $('#btn-top');
	var $menu = $('#side-menu');
	var $title = $('.page-header');
	var $docTextarea = $('#docContent');
	var $editormdView = $('#editormdView');
	var $permissionValue = $('#permissionValue');
	var $operateBar = $('#operateBar');
	
	var $projectSel = $('#projectSel');
	
	// 获取项目列表
	ApiUtil.post('project.listall',{},function(resp){
		// [{"description":"记录一些文档信息3","gmtCreate":1519959084000,"gmtUpdate":1519959084000,"id":6,"name":"文档系统3"},]
		var projectList = resp.data;
		
		var html = [];
		for (var i = 0,len = projectList.length; i < len; i++) {
			var project = projectList[i];
			var selectStr = i == 0 ? 'selected="selected"' : ''
			html.push('<option value="' + project.id + '" ' + selectStr + ' permission_value="'+project.permissionValue+'">' + project.name + '</option>');
		}
		$projectSel.html(html.join(''));
		
		if(projectId) {
			$projectSel.val(projectId);
		}
		// 初始化权限
		loadPermission();
		// 加载资源树
		loadResourceTree(resId);
	});
	
	/*
	 * <li class="active">
	        <a href="#"><i class="fa fa-folder-open"></i> 你好<span class="fa arrow"></span></a>
	        <ul class="nav nav-second-level">
	            <li>
	                <a href="#"><i class="fa fa-file-o"></i> 啊啊啊</a>
	            </li>
	        </ul>
	        <!-- /.nav-second-level -->
	    </li>
	 */
	function loadResourceTree(resId) {
		var projectId = $projectSel.val();
		if(!projectId) {
			return ;
		}
		ApiUtil.post('project.resource.list',{id:projectId},function(resp){
			clear();
			
			var resourceList = resp.data;
			if(resourceList.length == 0) {
				return;
			}
			
			var tree = sonsTree(resourceList,0);
			
			var temp = [];
			for(var i=0;i<tree.length;i++){
			    var item = tree[i],u = "";
			    if(i>0){
			        u = "</ul>";
			    }
			    if(item['lev']==0){
			        temp.push(u+'<li class="tree-folder" res_id = "'+item.id+'"><a href="javascript:void(0)"><i class="fa fa-folder-open"></i> '+item.name+'</a><ul class="nav nav-second-level sons">');
			    }else{
			        temp.push('<li class="tree-page" res_id = "'+item.id+'"><a href="javascript:void(0)"><i class="fa fa-file-o"></i> '+item.name+'</a></li>')
			    }
			    if(i+1==tree.length){
			        temp.push("</ul>")
			    }
			}
			// 加载tree
			$menu.html(temp.join(''));
			
			initTreeEvent(); // 初始化事件
			
			selectDoc(resId); // 选择资源
		});
	}
	
	function initTreeEvent() {
		// 点击事件
		$menu.find('.tree-folder').click(function(){
			var $this = $(this);
			var $son = $this.find('ul').eq(0);
			var isSonsVisiable = $son.is(':visible');
			
			if(isSonsVisiable) {
				$this.find('i').eq(0).removeClass('fa-folder-open').addClass('fa-folder');
				$son.hide();
			} else {
				$this.find('i').eq(0).removeClass('fa-folder').addClass('fa-folder-open');
				$son.show();
			}
		});
		
		$menu.find('.tree-page').click(function(){
			var $li = $(this);
			selectLi($li);
			return false;
		});
	}
	
	function loadPermission() {
		var $option = $projectSel.find("option:selected").eq(0);
		$permissionValue.val($option.attr('permission_value'));
		
		if(couldUpdate()) {
			$btnTop.html('<button id="newDocBtn" type="button" class="btn btn-info">新建文档</button>');
			$('#newDocBtn').click(function(){
				param = {
					projectId:$('#projectSel').val()
					,opt:'new'
				}
				var queryString = $.param(param);
				window.location = 'form.html?' + queryString;
			});
		} else {
			$btnTop.html(' ');
		}
	}
	
	function selectDoc(resId) {
		if(resId) {
			var $li = $menu.find('li[res_id='+resId+']').eq(0);
			selectLi($li);
		}
	}
	
	function selectLi($li) {
		var id = $li.attr('res_id');
		
		showDoc(id);
		
		$li.siblings().find('a').removeClass('active');
		$li.find('a').addClass('active');
	}
	
	// 显示文档
	function showDoc(resId) {
		if(!resId) {
			return;
		} 
			
		ApiUtil.post('resource.get',{id:resId},function(resp){
			var res = resp.data;
			$title.text(res.name);
			document.title = res.name;
			$editormdView.text(''); // 清空
			
			editormdView  = editormd.markdownToHTML("editormdView", {
                markdown        : ApiUtil.htmlDecode(res.content) ,
                //htmlDecode      : "style,script,iframe",  // you can filter tags decode
                tocm            : true,    // Using [TOCM]
                emoji           : true,
                taskList        : true,
                tex             : true,  // 默认不解析
                flowChart       : true,  // 默认不解析
                sequenceDiagram : true,  // 默认不解析
            });

			if(couldUpdate()) { // 能否修改
				$operateBar
					.html('<button type="button" class="btn btn-primary" onclick="updateRes('+resId+')">修改</button>' 
							+ ' <button type="button" class="btn btn-danger" onclick="delRes('+resId+')">删除</button>'
							+ '<hr/>'
							).show();
			} else {
				$operateBar.html('').hide();
			}
		});
	}
	
	//子孙树，从顶级往下找到是有的子子孙孙
	function sonsTree(arr,id){
	    var temp = [],lev=0;
	    var forFn = function(arr, id,lev){
	        for (var i = 0; i < arr.length; i++) {
	            var item = arr[i];
	            if (item.parentId==id) {
	                item.lev=lev;
	                temp.push(item);
	                forFn(arr,item.id,lev+1);
	            }
	        }
	    };
	    forFn(arr, id,lev);
	    return temp;
	}
	
	function clear() {
		$menu.html('');
		$title.html('');
		$editormdView.text(''); // 清空
		$operateBar.html('').hide();
	}
	
	// 能否修改
	function couldUpdate() {
		return $permissionValue.val() == 2;
	}
	
	// ---------初始化事件---------
	
	$projectSel.change(function(){
		loadPermission();
		loadResourceTree();
	});
});

// 更新资源
function updateRes(resId) {
	var param = {
		resId:resId
		,projectId:$('#projectSel').val()
		,opt:'update'
	}
	var queryString = $.param(param);
	window.location = 'form.html?' + queryString;
}

// 删除资源
function delRes(resId) {
	if(confirm("确定要删除吗?")) {
		ApiUtil.post('resource.delete',{id:resId},function(resp){
			window.location.reload();
		});
	}
}
