YUI.add("aui-palette",function(e,t){var n=e.Lang,r=e.Widget.UI_SRC,i=e.getClassName,s=i("palette-container"),o=i("palette-item"),u=i("palette-item-hover"),a=i("palette-item-inner"),f=i("palette-item-selected"),l=i("palette-items-container"),c=i("palette-items-container-{index}"),h=e.Base.create("palette",e.Widget,[e.WidgetCssClass,e.WidgetToggle],{CONTAINER_TEMPLATE:'<div class="'+s+'">{content}</div>',ITEMS_CONTAINER_TEMPLATE:'<ul class="'+l+" "+c+'">{content}</ul>',ITEM_TEMPLATE:'<li class="'+o+' {selectedClassName}" data-column={column} data-index={index} data-row={row} data-value="{value}">'+'<a href="" class="'+a+'" onclick="return false;"></a>'+"</li>",_items:null,initializer:function(){var t=this;t.after("itemsChange",t._afterItemsChange,t),t.after("selectedChange",t._afterSelectedChange,t),e.after(t._bindUIPalette,t,"renderUI"),t.publish({enter:{defaultFn:t._defEnterFn},leave:{defaultFn:t._defLeaveFn},select:{defaultFn:t._defSelectFn},unselect:{defaultFn:t._defUnselectFn}})},renderUI:function(){var e=this;e._uiSetItems(e.get("items"))},getItem:function(e,t){var n=this;return n.getItemByIndex(e*n.get("columns")+t)},getItemByIndex:function(e){var t=this;return t._getIndexedItems().item(e)},getItemByValue:function(t){var r=this,i=-1,s;return n.isObject(t)&&(t=t.value),s=r.get("items"),e.Array.some(s,function(e,r){n.isObject(e)&&(e=e.value);if(e===t)return i=r,!0}),r.getItemByIndex(i)},select:function(e){var t=this;t.toggleSelection(e,!0)},toggleSelection:function(e,t){var n=this,r=n.getItemByIndex(e)||n.getItemByValue(e);r&&r.toggleClass(f,t)},unselect:function(e){var t=this;t.toggleSelection(e,!1)},_afterItemsChange:function(e){var t=this;t._items=null,t._uiSetItems(e.newVal)},_afterSelectedChange:function(e){var t=this;t.unselect(e.prevVal),t.select(e.newVal)},_bindUIPalette:function(){var e=this,t=e.get("boundingBox");t.delegate("click",e._onItemClick,"."+o,e),t.delegate("hover",e._onItemMouseEnter,e._onItemMouseLeave,"."+o,e)},_defEnterFn:function(e){e.item.addClass(u)},_defLeaveFn:function(e){e.item.removeClass(u)},_defSelectFn:function(e){var t=this;e.src===r&&t.set("selected",e.index)},_defUnselectFn:function(e){var t=this;e.src===r&&t.set("selected",-1)},_getContent:function(e,t){var n=this,r=n.get("formatter"),i=n.get("selected"),s,o,u=0,a="",f=e.length,l,c=Math.ceil(f/t);for(l=0;l<c;l++){o="";for(s=0;s<t;s++){u=l*t+s;if(u>=f)break;o+=r.call(n,e,u,l,s,i===u)}a+=n._getRowContent(e,u,l,o)}return n._getPaletteContent(e,a)},_getEventsPayload:function(e){var t=this,i=t.get("items"),s,o=e.currentTarget;return s=n.toInt(o.getAttribute("data-index")),{column:n.toInt(o.getAttribute("data-column")),item:o,index:s,row:n.toInt(o.getAttribute("data-row")),src:r,value:i[s]}},_getIndexedItems:function(){var e=this;return e._items||(e._items=e.get("contentBox").all("."+o)),e._items},_getPaletteContent:function(e,t){var r=this;return n.sub(r.CONTAINER_TEMPLATE,{className:s,content:t})},_getRowContent:function(e,t,r,i){var s=this;return n.sub(s.ITEMS_CONTAINER_TEMPLATE,{className:c,content:i,index:r})},_onItemClick:function(e){var t=this,r=t.get("selected"),i=t.get("toggleSelection"),s,o=e.currentTarget,u=n.toInt(o.getAttribute("data-index"));u!==r?s="select":i&&(s="unselect"),s&&t.fire(s,t._getEventsPayload(e))},_onItemMouseEnter:function(e){var t=this;t.fire("enter",t._getEventsPayload(e))},_onItemMouseLeave:function(e){var t=this;t.fire("leave",t._getEventsPayload(e))},_uiSetItems:function(e){var t=this,n,r=t.get("width");r?n=e.length:(n=t.get("columns"),n===-1&&(n=e.length)),t.get("contentBox").setHTML(t.get("containerNode")||t._getContent(e,n))},_valueFormatterFn:function(){return function(e,t,r,i,s){var o=this,u=e[t];return n.sub(o.ITEM_TEMPLATE,{column:i,index:t,row:r,selectedClassName:s?f:"",value:n.isObject(u)?u.value:u})}}},{HTML_PARSER:{containerNode:"."+s},ATTRS:{columns:{validator:n.isNumber,value:-1},containerNode:{},formatter:{validator:n.isFunction,valueFn:"_valueFormatterFn"},items:{validator:n.isArray,value:[]},selected:{validator:n.isNumber,value:-1},toggleSelection:{validator:n.isBoolean,value:!0}}});e.Palette=h},"3.0.1",{requires:["base-build","event-hover","widget","aui-classnamemanager","aui-base","aui-widget-cssclass","aui-widget-toggle"],skinnable:!0});
