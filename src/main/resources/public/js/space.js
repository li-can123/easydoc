var $title = $('.page-header');
var $myProject_content = $('#myProject_content');

$(function(){
	$('.func-page').click(function(){
		var id = this.id;
		var $li = $(this);
		var $span = $li.find('span').eq(0);
		$title.html($span.html());
		
		var $content = $('#' + id + "_content");
		$content.show().siblings('.page-content').hide();
		
		Menu[id]();
	});
	
	JUI.dir('../jui/dest/')
		.config({Grid:{serverRowsName:'list', requestPageIndexName :'pageIndex', requestPageSizeName :'pageSize'}})
		.use(['DialogForm','Grid'],function(){
		projectGrid = new JUI.Grid({
			renderId:'grid-project'
			,striped:true // 斑马线
			,pagination :true // 开启分页
			,loader:function(config,callback) {
				var param = config.data;
				ApiUtil.post('project.list',param,function(resp){
					var respData = resp.data;
					callback(respData);
				});
			}
			,columns : [ [
				{
					field : 'name',
					title : '项目名称'
				},
				{
					field : 'description',
					title : '项目描述'
				},
				{
					field : 'gmtCreate',
					title : '创建时间',formatter:function(time) {
						return ApiUtil.formatTime(time);
					}
				}
				,{field:'_opt',title:'操作',formatter:function(val,row) {
					var btns = [
						'<a href="#" class="btn-updateProject">编辑项目</a>',
						'<a href="#" class="btn-addUser">添加成员</a>',
						'<a href="#" class="btn-delUser">删除成员</a>'
					]
					var btn = (row.permissionValue == 2 ? btns.join(' | ') : '');
					return btn;
				}}
				] 
			]
		});
		
		projectGrid.on('ClickCell',function(e){
			var $target = $(e.target); // $target即<a>
			if($target.hasClass('btn-addUser')) {
				var row = e.row;
				addUserWin.setTitle('添加用户['+row.name+']')
				addUserWin.setData({projectId:row.id, permissionValue:1});
				addUserWin.show();
			}
			if($target.hasClass('btn-delUser')) {
				var row = e.row;
				JUI.Msg.prompt('删除用户', '输入用户名：', function(r,name){
					if(r) {
						if(name) {
							var data = {projectId:row.id,username:name}
							ApiUtil.post('project.user.remove',data,function(resp){
								JUI.Msg.alert('提示','删除成功');
							});
						} else {
							JUI.Msg.alert('提示','用户名不能为空');
						}
					}
				});
			}
			if($target.hasClass('btn-updateProject')) {
				var row = e.row;
				projectWin.url = 'project.update';
				projectWin.setTitle('编辑项目');
				projectWin.setData(row);
				projectWin.show();
			}
		});
		
		addUserWin = new JUI.DialogForm({
			contentId:'addUserWin'
			,formId:'addUserFrm'
			,title:'添加用户'
			,width:300
			,onOk:function() {
				var b = this.validate();
				if(b) {
					var data = this.getData();
					ApiUtil.post('project.user.add',data,function(resp){
						JUI.Msg.alert('提示','添加成功');
						addUserWin.hide();
					});
				}
			}
		});
		
		projectWin = new JUI.DialogForm({
			contentId:'projectWin'
			,formId:'projectFrm'
			,title:'添加项目'
			,width:400
			,onOk:function() {
				var b = this.validate();
				if(b) {
					var data = this.getData();
					ApiUtil.post(projectWin.url,data,function(resp){
						projectWin.hide();
						projectGrid.reload();
					});
				}
			}
		});
	});
	
	$('#btn-project').click(function(){
		projectWin.setTitle('添加项目');
		projectWin.url = 'project.create';
		projectWin.clear();
		projectWin.show();
	});
	
}); // jquery end~


var Menu = {
	myProject:function() {
		projectGrid.reload();
	}
}
