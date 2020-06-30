(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d0da951"],{"6be4":function(e,t,r){"use strict";r.r(t);var n=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",[r("v-row",[r("v-toolbar-title",[r("h1",[e._v("Course")]),r("h6",[e._v("课程管理，用于录入成绩")])]),r("v-spacer"),r("v-btn",{staticClass:"mx-2",attrs:{color:"secondary"},on:{click:e.openAddDialog}},[r("v-icon",[e._v("mdi-plus")]),e._v("addCourse ")],1)],1),r("v-divider"),r("v-row",e._l(e.courses,(function(t,n){return r("v-col",{key:n},[r("v-card",{staticClass:"ma-auto",attrs:{width:"300"}},[r("v-card-text",[r("p",{staticClass:"display-1 text--primary"},[e._v(" "+e._s(t.name)+" ")]),r("p",[e._v("课程")]),r("div",{staticClass:"text--primary"},[e._v(" 所占权重"),r("br"),e._v(" "+e._s(t.weight)+" ")])]),r("v-card-actions",[r("v-btn",{attrs:{icon:"",color:"secondary"},on:{click:function(r){return e.deleteCourse(t.id)}}},[r("v-icon",[e._v("close")])],1),r("v-spacer"),r("v-btn",{attrs:{text:"",color:"secondary accent-4"},on:{click:function(r){return e.router(t.id)}}},[e._v(" More "),r("v-icon",{attrs:{right:"",dark:""}},[e._v("mdi-arrow-right")])],1)],1)],1)],1)})),1),r("v-dialog",{attrs:{persistent:"","max-width":"600px"},model:{value:e.dialog,callback:function(t){e.dialog=t},expression:"dialog"}},[r("v-card",[r("v-card-title",[r("v-spacer"),r("v-btn",{attrs:{color:"black darken-1",text:""},on:{click:e.openAddDialog}},[r("v-icon",[e._v(" mdi-close")])],1)],1),r("v-form",{model:{value:e.valid,callback:function(t){e.valid=t},expression:"valid"}},[r("v-container",[r("v-row",[r("v-col",{attrs:{cols:"12",md:"4"}},[r("v-text-field",{attrs:{counter:10,rules:e.nameRules,label:"课程名",required:""},model:{value:e.newcourse.name,callback:function(t){e.$set(e.newcourse,"name",t)},expression:"newcourse.name"}})],1),r("v-col",{attrs:{cols:"12",md:"4"}},[r("v-text-field",{attrs:{rules:e.weightRules,counter:2,label:"权重"},model:{value:e.newcourse.weight,callback:function(t){e.$set(e.newcourse,"weight",t)},expression:"newcourse.weight"}})],1),r("v-col",{attrs:{cols:"12",md:"4"}},[r("v-text-field",{attrs:{counter:20,label:"说明"},model:{value:e.newcourse.extra,callback:function(t){e.$set(e.newcourse,"extra",t)},expression:"newcourse.extra"}})],1),r("v-col",{staticClass:"mx-auto",attrs:{cols:"12",md:"4"}},[r("v-btn",{staticClass:"mx-auto",attrs:{color:"secondary",disabled:!e.valid},on:{click:function(t){return e.addCourse(e.newcourse)}}},[r("v-icon",[e._v("check")]),e._v("Submit ")],1)],1)],1)],1)],1)],1)],1)],1)},a=[],o=(r("96cf"),r("1da1")),s=r("65b7"),c={data:function(){return{valid:!1,weightRules:[function(e){return e<=10||"必须小于等于10"}],nameRules:[function(e){return""!=e||"不能为空"}],dialog:!1,newcourse:{name:"",weight:0,extra:""},courses:[]}},computed:{},components:{},created:function(){this.getCourses()},mounted:function(){},methods:{openAddDialog:function(){this.dialog=0==this.dialog},router:function(e){this.$router.push("/backstage/courses/".concat(e))},getCourses:function(){var e=this;return Object(o["a"])(regeneratorRuntime.mark((function t(){var r;return regeneratorRuntime.wrap((function(t){while(1)switch(t.prev=t.next){case 0:return t.next=2,s["a"].get("teacher/courses");case 2:r=t.sent,e.courses=r.data.courses;case 4:case"end":return t.stop()}}),t)})))()},addCourse:function(e){var t=this;return Object(o["a"])(regeneratorRuntime.mark((function r(){var n;return regeneratorRuntime.wrap((function(r){while(1)switch(r.prev=r.next){case 0:return r.next=2,s["a"].post("teacher/courses",e);case 2:n=r.sent,t.courses=n.data.courses,t.openAddDialog();case 5:case"end":return r.stop()}}),r)})))()},deleteCourse:function(e){var t=this;return Object(o["a"])(regeneratorRuntime.mark((function r(){var n;return regeneratorRuntime.wrap((function(r){while(1)switch(r.prev=r.next){case 0:return r.next=2,s["a"].delete("teacher/courses/".concat(e));case 2:n=r.sent,t.courses=n.data.courses;case 4:case"end":return r.stop()}}),r)})))()}}},i=c,u=r("2877"),l=Object(u["a"])(i,n,a,!1,null,"3f103902",null);t["default"]=l.exports}}]);