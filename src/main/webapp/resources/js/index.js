$(function(){
	$.post('menusController.do?menusList',function(result){
		var menus = result.menus;//一级菜单数据
		var submenus = result.submenus;//二级菜单数据
		$.each(menus,function(i,item){
			var subcontent = '';
				subcontent += '<ul>';
			$.each(submenus,function(i,subitem){
				if(item.id==subitem.menuid){
					var k=i+Math.floor(Math.random()*10000+1);
					subcontent +='<li><div onclick="(function(){var tabTitle = $(\'#text'+k+'\').text();var url = $(\'#sub'+k+'\').attr(\'href\');var icon=\''+subitem.iconCls+'\';addTab(tabTitle,url,icon);})();return false;"><a id="sub'+k+'"' 
					+ 'class="abcd" href="' + subitem.url + '"><span class="icon ' + subitem.iconCls
	                + '" >&nbsp;</span><span id="text'+k+'">'+subitem.subname+'</span></a></div></li>';
				}
			});
			subcontent += '</ul>';
			$('#aa').accordion('add',{
				title: item.menuname,
				iconCls: item.iconCls,
				content: subcontent,
				selected: false,
			});
		});
		$('#aa').accordion('select',0);
	},'json');
	
	//删除学员
	$('#delstu').click(function (){
		var row =$('#dg').datagrid('getSelected');
		if(row){
			$.messager.confirm("系统提示","您确定要删除这条记录吗?",function(r){
				if(r){
					$.post('index.do?delstu',{id:row.id},function(result){
						if(result){
							$.messager.alert("系统提示","删除成功","info");
							$('#dg').datagrid('reload');
						}
					},'json');
				}
			});
		}else{
			$.messager.alert("系统提示","请选择要删除的学员!","info");
		}
	});
	
	var url;
	
	//新增学员
	$('#newstu').click(function(){
		$('#dlg').dialog('open').dialog('setTitle','添加学员');
		$('#fm').form('clear');
		$("input[name='sex']").get(0).checked=true;
		url='index.do?addstu';
	});
	
	//编辑学员
	$('#editstu').click(function(){
		var row =$('#dg').datagrid('getSelected');
		if(row){
				$('#dlg').dialog('open').dialog('setTitle','修改学员');
				$('#fm').form('load',row);
				url='index.do?upstu&id='+row.id;
		}else{
			$.messager.alert("系统提示","请选择要编辑的学员!","info");
		}
	});
	
	//保存学员
	$('#addstu').click(function(){
		$('#fm').form('submit',{
			url:url,
			onSubmit:function(){
				return $(this).form('validate');
			},
			success:function(result){
				var result = eval("(" + result + ")");
				if(result){
					$.messager.alert("系统提示","保存成功","info");
					$('#dlg').dialog('close');
					$("#dg").datagrid("reload");
				}
			}
		});
	});
	
	//表头右键菜单
	$('#dg').datagrid({
		 onHeaderContextMenu: function(e, field){
			 e.preventDefault();
			 if (!cmenu){
				 createColumnMenu();
			 }
			 cmenu.menu('show', {
			 	left:e.pageX,
			 	top:e.pageY
			 });
		}
	});
	var cmenu;
	function createColumnMenu(){
		cmenu = $('<div/>').appendTo('body');
		cmenu.menu({
			onClick: function(item){
				if (item.iconCls == 'icon-ok'){
					$('#dg').datagrid('hideColumn', item.name);
					cmenu.menu('setIcon', {
						target: item.target,
						iconCls: 'icon-empty'
					});
				} else {
					$('#dg').datagrid('showColumn', item.name);
					cmenu.menu('setIcon', {
						target: item.target,
						iconCls: 'icon-ok'
					});
				}
			}
		});
		var fields = $('#dg').datagrid('getColumnFields');
		for(var i=0; i<fields.length; i++){
			var field = fields[i];
			var col = $('#dg').datagrid('getColumnOption', field);
			if(!col.hidden){
				cmenu.menu('appendItem', {
					text: col.title,
					name: field,
					iconCls: 'icon-ok'
				});
			}else{
				cmenu.menu('appendItem', {
					text: col.title,
					name: field,
					iconCls: 'icon-empty'
				});
			}
		}
	}
	
	//绑定右键菜单事件
		//关闭当前
		$('#mm-tabclose').click(function(){
			var currtab_title = $('#mm').data("currtab");
			$('#tabs').tabs('close',currtab_title);
		});
		//全部关闭
		$('#mm-tabcloseall').click(function(){
			$('.tabs-inner span').each(function(i,n){
				var t = $(n).text();
				$('#tabs').tabs('close',t);
			});	
		});
		//关闭除当前之外的TAB
		$('#mm-tabcloseother').click(function(){
			var currtab_title = $('#mm').data("currtab");
			$('.tabs-inner span').each(function(i,n){
				var t = $(n).text();
				if(t!=currtab_title)
					$('#tabs').tabs('close',t);
			});	
		});
		//关闭当前右侧的TAB
		$('#mm-tabcloseright').click(function(){
			var nextall = $('.tabs-selected').nextAll();
			if(nextall.length==0){
				//msgShow('系统提示','后边没有啦~~','error');
				alert('后边没有啦~~');
				return false;
			}
			nextall.each(function(i,n){
				var t=$('a:eq(0) span',$(n)).text();
				$('#tabs').tabs('close',t);
			});
			return false;
		});
		//关闭当前左侧的TAB
		$('#mm-tabcloseleft').click(function(){
			var prevall = $('.tabs-selected').prevAll();
			if(prevall.length==0){
				alert('到头了，前边没有啦~~');
				return false;
			}
			prevall.each(function(i,n){
				var t=$('a:eq(0) span',$(n)).text();
				$('#tabs').tabs('close',t);
			});
			return false;
		});

		//退出
		$("#mm-exit").click(function(){
			$('#mm').menu('hide');
		});

	//弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
	function msgShow(title, msgString, msgType) {
		$.messager.alert(title, msgString, msgType);
	}
})
