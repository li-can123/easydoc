(function(){var a=JUI.Class("Msg",{init:function(c){this._super(c)},setContent:function(c){this.getContentDiv().html(c)},getContentDiv:function(){return $("#"+this.opt("contentId"))}},JUI.Dialog);var b=function(e){var d="jui-msg-id-"+JUI.getId();$("body").append($('<div id="'+d+'" style="min-width:200px;"></div>'));var c=new a({contentId:d,onOk:function(){e&&e.call(this,true);this.hide()},onCancel:function(){e&&e.call(this,false);this.hide()},zindex:1000});c.setNextDo(function(){this.hideBtn(1)});return c};a.confirm=function(f,d,c){callback=c;var e=b(c);e.setTitle(f);e.setContent(d);e.show();e.setNextDo(function(){this.showBtn(1)});return e};a.alert=function(f,d,c){var e=a.confirm(f,d,c);e.setNextDo(function(){this.hideBtn(1)})};a.prompt=function(g,e,d){var c="jui-msg-promptinput-id"+JUI.getId();var e="<div>"+e+'</div><div><input id="'+c+'" type="text" style="width:250px;"/></div>';var f=a.confirm(g,e,function(h){var i=$("#"+c).val();d(h,i)});f.getContentDiv().width("auto")}})();