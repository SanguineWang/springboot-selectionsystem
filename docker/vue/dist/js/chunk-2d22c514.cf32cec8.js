(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d22c514"],{f35f:function(t,e,n){"use strict";n.r(e);var r=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[n("v-toolbar",{attrs:{flat:""}},[n("v-toolbar-title",[n("h3",[t._v(t._s(t.direction.name))])]),n("v-subheader",[t._v(" "+t._s(t.direction.weight)+" ")]),n("v-spacer"),n("div",[n("v-btn",[n("v-icon",[t._v("build")])],1)],1)],1),n("v-divider"),n("v-simple-table",{scopedSlots:t._u([{key:"default",fn:function(){return[n("thead",[n("tr",[n("th",{staticClass:"text-left"},[t._v("count")]),n("th",{staticClass:"text-left"},[t._v("学号")]),n("th",{staticClass:"text-left"},[t._v("name")]),n("th",{staticClass:"text-left"},[t._v("score")])])]),n("tbody",t._l(t.students,(function(e,r){return n("tr",{key:e.id},[n("td",[t._v(t._s(r+1))]),n("td",[t._v(t._s(e.user.number))]),n("td",[t._v(t._s(e.user.name))]),n("td",[t._v(t._s(e.grade))])])})),0)]},proxy:!0}])})],1)},i=[],a=(n("96cf"),n("1da1")),s=n("65b7"),c={props:["did"],data:function(){return{students:[],direction:{name:"",weight:""}}},components:{},created:function(){this.getDirectionDetail(this.did),this.getDirectionStudents(this.did)},mounted:function(){},methods:{getDirectionDetail:function(t){var e=this;return Object(a["a"])(regeneratorRuntime.mark((function n(){var r;return regeneratorRuntime.wrap((function(n){while(1)switch(n.prev=n.next){case 0:return n.next=2,s["a"].get("teacher/directions/".concat(t));case 2:r=n.sent,e.direction=r.data.direction;case 4:case"end":return n.stop()}}),n)})))()},updateDirection:function(t){var e=this;return Object(a["a"])(regeneratorRuntime.mark((function n(){var r;return regeneratorRuntime.wrap((function(n){while(1)switch(n.prev=n.next){case 0:return n.next=2,s["a"].patch("teacher/directions/".concat(t));case 2:r=n.sent,e.direction=r.data.direction;case 4:case"end":return n.stop()}}),n)})))()},getDirectionStudents:function(t){var e=this;return Object(a["a"])(regeneratorRuntime.mark((function n(){var r;return regeneratorRuntime.wrap((function(n){while(1)switch(n.prev=n.next){case 0:return n.next=2,s["a"].get("teacher/directions/".concat(t,"/students"));case 2:r=n.sent,e.students=r.data.students;case 4:case"end":return n.stop()}}),n)})))()}}},o=c,u=n("2877"),d=Object(u["a"])(o,r,i,!1,null,"4a84e2b9",null);e["default"]=d.exports}}]);