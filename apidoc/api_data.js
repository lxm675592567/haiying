define({ "api": [
  {
    "type": "Post",
    "url": "/physical/heightAndWeight/findHeightAndWeightGraphData/{guid}",
    "title": "findHeightAndWeightGraphData",
    "group": "体格发育",
    "description": "<p>百分位统计表-根据guid获取体格发育数据曲线图(暂时不用)</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "guid",
            "description": "<p>档案号</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "传参示例",
          "content": "{\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\"}",
          "type": "String"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/main/java/haiying/service/physical/controller/HeightAndWeightController.java",
    "groupTitle": "体格发育",
    "name": "PostPhysicalHeightandweightFindheightandweightgraphdataGuid"
  },
  {
    "type": "Post",
    "url": "/physical/heightAndWeight/findHeightAndWeightHistoryPagination",
    "title": "findHeightAndWeightHistoryPagination",
    "group": "体格发育",
    "description": "<p>历史记录-根据传递的身高体重数据保存体格发育历史数据(暂未用到)</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Json",
            "optional": false,
            "field": "HeightAndWeight",
            "description": "<p>身高体重实体类</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "传参示例",
          "content": "{\"guid\":\"TQDV26PDGS15VAV58JA68OU5VX86GG81\",\"height\":100,\"weight\":43}",
          "type": "Json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/main/java/haiying/service/physical/controller/HeightAndWeightController.java",
    "groupTitle": "体格发育",
    "name": "PostPhysicalHeightandweightFindheightandweighthistorypagination"
  },
  {
    "type": "Post",
    "url": "/physical/heightAndWeight/getHeightAndWeightDateCurve",
    "title": "getHeightAndWeightDateCurve",
    "group": "体格发育",
    "description": "<p>获取曲线图数据</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "guid",
            "description": "<p>档案号</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "传参示例",
          "content": "{\"guid\":\"TQDV26PDGS15VAV58JA68OU5VX86GG81\"}",
          "type": "String"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "返回值示例",
          "content": "[\n    {\n        \"createTime\": \"2020-04-29 \",\n        \"weight\": 43.0,\n        \"height\": 100.0\n    },\n   {\n        \"createTime\": \"2020-04-30 \",\n        \"weight\": 44.0,\n        \"height\": 100.0\n    }\n]",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/main/java/haiying/service/physical/controller/HeightAndWeightController.java",
    "groupTitle": "体格发育",
    "name": "PostPhysicalHeightandweightGetheightandweightdatecurve"
  },
  {
    "type": "Post",
    "url": "/physical/heightAndWeight/saveOrEditHeightAndWeight",
    "title": "saveOrEditHeightAndWeight",
    "group": "体格发育",
    "description": "<p>保存-根据传递的身高体重数据保存体格发育数据</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Json",
            "optional": false,
            "field": "guid",
            "description": "<p>(必填) guid</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "height",
            "description": "<p>(必填) 身高</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "weight",
            "description": "<p>(必填) 体重</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "传参示例",
          "content": "{\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"height\":100,\"weight\":43}",
          "type": "Json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "返回值示例",
          "content": "{\"status\":0,\"msg\":\"ok\",\"result\":{\"hwId\":\"1260856430453874688\",\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"height\":100.0,\"weight\":43.0,\"heightEvaluation\":\"上异常\",\"weightEvaluation\":\"上异常\",\"monthAgeInt\":12,\"age\":\"1岁0月27天\",\"createTime\":\"2020-05-14 16:56:13\",\"monthIntHeightEntry\":[12,100.0],\"monthIntWeightEntry\":[12,43.0],\"correctMonthHeightEntry\":[null,100.0],\"monthHeightEntry\":[null,100.0],\"monthWeightEntry\":[null,43.0],\"correctMonthWeightEntry\":[null,43.0]},\"success\":true}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/main/java/haiying/service/physical/controller/HeightAndWeightController.java",
    "groupTitle": "体格发育",
    "name": "PostPhysicalHeightandweightSaveoreditheightandweight"
  },
  {
    "type": "Post",
    "url": "/feed/findFeed",
    "title": "findFeed",
    "group": "喂养记录",
    "description": "<p>首页-喂养记录-统计 获取宝宝实时喂养记录信息</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "guid",
            "description": "<p>档案号</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "createTime",
            "description": "<p>查询的时间</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "传参示例",
          "content": "{\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"createTime\":\"2020-04-23\"}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "census",
            "optional": false,
            "field": "typeName",
            "description": "<p>类型名称</p>"
          },
          {
            "group": "Success 200",
            "type": "census",
            "optional": false,
            "field": "type",
            "description": "<p>1母乳亲喂  2瓶装母乳  3配方奶  4辅食  5换尿布  6睡眠</p>"
          },
          {
            "group": "Success 200",
            "type": "census",
            "optional": false,
            "field": "total",
            "description": "<p>次数</p>"
          },
          {
            "group": "Success 200",
            "type": "census",
            "optional": false,
            "field": "record",
            "description": "<p>统计记录结果</p>"
          },
          {
            "group": "Success 200",
            "type": "census",
            "optional": false,
            "field": "selectTypeName",
            "description": "<p>换尿布选择类型</p>"
          },
          {
            "group": "Success 200",
            "type": "census",
            "optional": false,
            "field": "selectType",
            "description": "<p>换尿布选择类型 1嘘嘘 2便便  3嘘嘘+便便</p>"
          },
          {
            "group": "Success 200",
            "type": "census",
            "optional": false,
            "field": "selectCount",
            "description": "<p>换尿布次数</p>"
          },
          {
            "group": "Success 200",
            "type": "total",
            "optional": false,
            "field": "urineShape",
            "description": "<p>换尿布次数</p>"
          },
          {
            "group": "Success 200",
            "type": "total",
            "optional": false,
            "field": "createTime",
            "description": "<p>开始时间(单次时间)</p>"
          },
          {
            "group": "Success 200",
            "type": "total",
            "optional": false,
            "field": "endTime",
            "description": "<p>结束时间</p>"
          },
          {
            "group": "Success 200",
            "type": "total",
            "optional": false,
            "field": "urineshape",
            "description": "<p>嘘嘘性状</p>"
          },
          {
            "group": "Success 200",
            "type": "total",
            "optional": false,
            "field": "shitShape",
            "description": "<p>便便性状</p>"
          },
          {
            "group": "Success 200",
            "type": "total",
            "optional": false,
            "field": "nurseContent",
            "description": "<p>喂奶量</p>"
          },
          {
            "group": "Success 200",
            "type": "total",
            "optional": false,
            "field": "lactation",
            "description": "<p>哺乳行为</p>"
          },
          {
            "group": "Success 200",
            "type": "total",
            "optional": false,
            "field": "spacing",
            "description": "<p>睡眠时间</p>"
          },
          {
            "group": "Success 200",
            "type": "total",
            "optional": false,
            "field": "time",
            "description": "<p>小时分钟</p>"
          },
          {
            "group": "Success 200",
            "type": "total",
            "optional": false,
            "field": "state",
            "description": "<p>最近记录时间</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回值示例",
          "content": "{\"total\":[{\"urineShape\":\"正常\",\"selectTypeName\":\"嘘嘘+便便\",\"shapeType\":\"3\",\"createTime\":\"2020-04-23 16:15\",\"typeName\":\"换尿布\",\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"selectType\":\"3\",\"endTime\":\"2020-04-23 16:17:05\",\"shitShape\":\"绿色\",\"time\":\"16:15\",\"state\":\"嘘嘘+便便:正常/绿色\",\"type\":\"5\"},{\"urineShape\":\"正常\",\"selectTypeName\":\"嘘嘘\",\"createTime\":\"2020-04-23 16:15:06\",\"typeName\":\"换尿布\",\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"selectType\":\"1\",\"endTime\":\"2020-04-23 16:27:05\",\"shitShape\":\"绿色\",\"time\":\"16:15\",\"state\":\"嘘嘘:正常\",\"type\":\"5\",\"nurseContent\":0},{\"urineShape\":\"正常\",\"selectTypeName\":\"便便\",\"createTime\":\"2020-04-23 16:15:06\",\"typeName\":\"换尿布\",\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"selectType\":\"2\",\"endTime\":\"2020-04-23 16:37:05\",\"shitShape\":\"绿色\",\"time\":\"16:15\",\"state\":\"便便:绿色\",\"type\":\"5\",\"nurseContent\":0},{\"urineShape\":\"正常\",\"selectTypeName\":\"嘘嘘+便便\",\"shapeType\":\"3\",\"createTime\":\"2020-04-23 16:07:05\",\"typeName\":\"换尿布\",\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"selectType\":\"3\",\"endTime\":\"2020-04-23 16:47:05\",\"shitShape\":\"绿色\",\"time\":\"16:07\",\"state\":\"嘘嘘+便便:正常/绿色\",\"type\":\"5\"},{\"duration\":2,\"createTime\":\"2020-04-23 16:06:08\",\"lactation\":\"左侧哺乳\",\"typeName\":\"母乳亲喂\",\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"endTime\":\"2020-04-23 16:07:05\",\"time\":\"16:06\",\"state\":\"母乳亲喂:2分钟\",\"type\":\"1\"},{\"createTime\":\"2020-04-23 16:06:05\",\"typeName\":\"睡眠\",\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"endTime\":\"2020-04-23 16:35:05\",\"time\":\"16:06\",\"state\":\"睡眠:0小时29分钟\",\"type\":\"6\"},{\"foodName\":\"米粉\",\"createTime\":\"2020-04-23 16:05:15\",\"foodDescribe\":\"少量\",\"typeName\":\"辅食\",\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"time\":\"16:05\",\"state\":\"辅食:米粉\",\"type\":\"4\"},{\"duration\":3,\"createTime\":\"2020-04-23 16:05:08\",\"lactation\":\"左侧哺乳\",\"typeName\":\"母乳亲喂\",\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"endTime\":\"2020-04-23 16:07:05\",\"time\":\"16:05\",\"state\":\"母乳亲喂:3分钟\",\"type\":\"1\"},{\"createTime\":\"2020-04-23 16:05:06\",\"typeName\":\"配方奶\",\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"time\":\"16:05\",\"state\":\"配方奶:120ml\",\"type\":\"3\",\"nurseContent\":120},{\"createTime\":\"2020-04-23 16:05:05\",\"typeName\":\"瓶装母乳\",\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"time\":\"16:05\",\"state\":\"瓶装母乳:100ml\",\"type\":\"2\",\"nurseContent\":100}],\"census\":[{\"total\":2,\"record\":\"5分钟\",\"typeName\":\"母乳亲喂\",\"type\":\"1\"},{\"total\":1,\"record\":\"100ml\",\"typeName\":\"瓶装母乳\",\"type\":\"2\"},{\"total\":1,\"record\":\"120ml\",\"typeName\":\"配方奶\",\"type\":\"3\"},{\"total\":1,\"typeName\":\"辅食\",\"type\":\"4\"},{\"total\":4,\"record\":[{\"selectTypeName\":\"嘘嘘\",\"selectCount\":1,\"selectType\":\"1\"},{\"selectTypeName\":\"便便\",\"selectCount\":1,\"selectType\":\"2\"},{\"selectTypeName\":\"嘘嘘+便便\",\"selectCount\":2,\"selectType\":\"3\"}],\"typeName\":\"换尿布\",\"type\":\"5\"},{\"total\":1,\"spacing\":\"1小时13分钟\",\"typeName\":\"睡眠\",\"type\":\"6\"}]}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/main/java/haiying/service/feed/controller/FeedController.java",
    "groupTitle": "喂养记录",
    "name": "PostFeedFindfeed"
  },
  {
    "type": "Post",
    "url": "/feed/getDateCurve",
    "title": "getDateCurve",
    "group": "喂养记录",
    "description": "<p>首页-喂养记录-统计(图标) 获取宝宝喂养记录曲线图</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "guid",
            "description": "<p>档案号</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "createTime",
            "description": "<p>查询的时间</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "feedtype",
            "description": "<p>喂奶:1 辅食:2 换尿布:3 睡眠:4</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "传参示例",
          "content": "{\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"createTime\":\"2020-04\",\"feedtype\":\"1\"}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "json",
            "optional": false,
            "field": "nurseTotal",
            "description": "<p>喂奶量</p>"
          },
          {
            "group": "Success 200",
            "type": "json",
            "optional": false,
            "field": "count",
            "description": "<p>次数</p>"
          },
          {
            "group": "Success 200",
            "type": "json",
            "optional": false,
            "field": "createTime",
            "description": "<p>时间</p>"
          },
          {
            "group": "Success 200",
            "type": "json",
            "optional": false,
            "field": "spacing",
            "description": "<p>睡眠时长</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回值示例",
          "content": "[{\"nurseTotal\":220,\"createTime\":\"2020-04-23 \",\"count\":4},{\"nurseTotal\":0,\"createTime\":\"2020-04-24 \",\"count\":1}]",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/main/java/haiying/service/feed/controller/FeedController.java",
    "groupTitle": "喂养记录",
    "name": "PostFeedGetdatecurve"
  },
  {
    "type": "Post",
    "url": "/feed/saveFeed",
    "title": "saveFeed",
    "group": "喂养记录",
    "description": "<p>保存宝宝实时喂养记录信息</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "feed",
            "description": "<p>喂养记录实体类</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "guid",
            "description": "<p>(必传) 档案号</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "type",
            "description": "<p>(必传) 类型  1母乳亲喂  2瓶装母乳  3配方奶  4辅食  5换尿布  6睡眠</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "typeName",
            "description": "<p>(必传) 类型名称 母乳亲喂  瓶装母乳  配方奶  辅食  换尿布  睡眠</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "createTime",
            "description": "<p>(必传) 开始时间</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "endTime",
            "description": "<p>结束时间</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "duration",
            "description": "<p>哺乳时长</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "nurseContent",
            "description": "<p>喂奶量</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "foodName",
            "description": "<p>辅食名称</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "foodDescribe",
            "description": "<p>辅食描述</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "foodPhoto",
            "description": "<p>辅食照片</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "selectType",
            "description": "<p>换尿布选择类型 1嘘嘘 2便便  3嘘嘘+便便</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "selectTypeName",
            "description": "<p>换尿布选择类型</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "urineShape",
            "description": "<p>嘘嘘性状</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "shitShape",
            "description": "<p>便便性状</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "传参示例",
          "content": "{\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"type\":\"1\",\"typeName\":\"母乳亲喂\",\"createTime\":\"2020-04-23 16:05\",\"endTime\":\"2020-04-23 16:07\",\"lactation\":\"左侧哺乳\",\"duration\":\"2\"}\n{\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"type\":\"2\",\"typeName\":\"瓶装母乳\",\"createTime\":\"2020-04-23 16:05\",\"nurseContent\":\"120\"}\n{\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"type\":\"3\",\"typeName\":\"配方奶\",\"createTime\":\"2020-04-23 16:05\",\"nurseContent\":\"120\"}\n{\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"type\":\"4\",\"typeName\":\"辅食\",\"createTime\":\"2020-04-23 16:05\",\"foodName\":\"米粉\",\"foodDescribe\":\"少量\",\"foodPhoto\":\"\"}\n{\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"type\":\"5\",\"typeName\":\"换尿布\",\"diaperTime\":\"16:29\",\"selectTypeName\":\"嘘嘘+便便\",\"selectType\":\"3\",\"urineShape\":\"正常\",\"shitShape\":\"绿色\"}\n{\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"type\":\"6\",\"typeName\":\"睡眠\",\"createTime\":\"2020-04-23 16:05\",\"endTime\":\"2020-04-23 16:35\"}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "返回值示例",
          "content": "{\"status\":0,\"msg\":\"ok\",\"result\":{\"id\":\"1260848390719692800\",\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"typeName\":\"换尿布\",\"type\":\"5\",\"createTime\":\"2020-04-23 16:15\",\"duration\":0,\"nurseContent\":0,\"selectTypeName\":\"嘘嘘+便便\",\"urineShape\":\"正常\",\"shitShape\":\"绿色\"},\"success\":true}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/main/java/haiying/service/feed/controller/FeedController.java",
    "groupTitle": "喂养记录",
    "name": "PostFeedSavefeed"
  },
  {
    "type": "Get",
    "url": "/user/findAreaList",
    "title": "findAreaList",
    "group": "平台",
    "description": "<p>地区查询接口</p>",
    "success": {
      "examples": [
        {
          "title": "返回值示例",
          "content": "{\n\t\"resultData\": [{\n\t\t\"areaCode\": \"11\",\n\t\t\"areaName\": \"北京\",\n\t\t\"level\": 1,\n\t\t\"fullName\": \"北京\",\n\t\t\"originalCode\": \"110000\",\n\t\t\"childLevel\": 2,\n\t\t\"nodeId\": \"11\",\n\t\t\"isLeaf\": false,\n\t\t\"parentId\": \"0\"\n       }],\n\t\"success\": true,\n\t\"message\": \"操作成功\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/main/java/haiying/service/auth/controller/UserController.java",
    "groupTitle": "平台",
    "name": "GetUserFindarealist"
  },
  {
    "type": "Get",
    "url": "/user/hospital/select",
    "title": "select",
    "group": "平台",
    "description": "<p>医院查询接口(取tenantName,tenantId)</p>",
    "success": {
      "examples": [
        {
          "title": "返回值示例",
          "content": "{\"success\":true,\"resultData\":[{\"id\":\"1183574326192406530\",\"createUser\":\"1123598821738675201\",\"createDept\":\"1123598813738675201\",\"createTime\":\"2019-10-14 02:44:44\",\"updateUser\":\"1123598821738675201\",\"updateTime\":\"2019-10-14 02:44:44\",\"status\":1,\"isDeleted\":0,\"tenantId\":\"884605\",\"tenantName\":\"北大人民医院\",\"domain\":null,\"backgroundUrl\":null,\"linkman\":\"Tom\",\"contactNumber\":\"123456789\",\"address\":\"北大人民医院\",\"accountNumber\":null,\"expireTime\":null},{\"id\":\"1187616183406903298\",\"createUser\":\"1123598821738675201\",\"createDept\":\"1123598813738675201\",\"createTime\":\"2019-10-25 06:25:38\",\"updateUser\":\"1123598821738675201\",\"updateTime\":\"2019-10-25 06:25:38\",\"status\":1,\"isDeleted\":0,\"tenantId\":\"123807\",\"tenantName\":\"儿童营养\",\"domain\":null,\"backgroundUrl\":null,\"linkman\":\"儿童\",\"contactNumber\":\"\",\"address\":\"\",\"accountNumber\":null,\"expireTime\":null},{\"id\":\"1188654792096415746\",\"createUser\":\"1123598821738675201\",\"createDept\":\"1123598813738675201\",\"createTime\":\"2019-10-28 03:12:41\",\"updateUser\":\"1123598821738675201\",\"updateTime\":\"2019-10-28 03:12:41\",\"status\":1,\"isDeleted\":0,\"tenantId\":\"081681\",\"tenantName\":\"泰安高铁医院\",\"domain\":null,\"backgroundUrl\":null,\"linkman\":\"admin\",\"contactNumber\":\"22211442\",\"address\":\"\",\"accountNumber\":null,\"expireTime\":null},{\"id\":\"1188694440776572929\",\"createUser\":\"1123598821738675201\",\"createDept\":\"1123598813738675201\",\"createTime\":\"2019-10-28 05:50:14\",\"updateUser\":\"1123598821738675201\",\"updateTime\":\"2019-10-28 05:50:14\",\"status\":1,\"isDeleted\":0,\"tenantId\":\"948459\",\"tenantName\":\"孕期营养\",\"domain\":null,\"backgroundUrl\":null,\"linkman\":\"admin\",\"contactNumber\":\"123456\",\"address\":\"\",\"accountNumber\":null,\"expireTime\":null}]}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/main/java/haiying/service/auth/controller/UserController.java",
    "groupTitle": "平台",
    "name": "GetUserHospitalSelect"
  },
  {
    "type": "Post",
    "url": "/archives/record/getGrowUpArchives",
    "title": "getGrowUpArchives",
    "group": "成长档案",
    "description": "<p>成长档案-获取宝宝基本</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "guid",
            "description": "<p>guid</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "传参示例",
          "content": "{\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\"}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "返回值示例",
          "content": "{\"feed\":[{\"spacing\":\"8小时22分钟\",\"typeName\":\"瓶装母乳\"},{\"spacing\":\"4小时20分钟\",\"typeName\":\"换尿布\"},{\"spacing\":\"1小时21分钟\",\"typeName\":\"睡眠\"}],\"record\":{\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"cardId\":\"20190515000003\",\"name\":\"刘静\",\"sex\":\"女\",\"birthday\":\"2019-04-17\",\"openId\":\"ojP-tvwqOu-kwE4qxKqhFJSC3KOw\"},\"heightAndWeight\":{\"weight\":43.0,\"bmi\":43.0,\"height\":100.0}}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/main/java/haiying/service/archives/controller/RecordController.java",
    "groupTitle": "成长档案",
    "name": "PostArchivesRecordGetgrowuparchives"
  },
  {
    "type": "Get",
    "url": "/archives/record/getBabyArchives",
    "title": "getBabyArchives",
    "group": "档案信息",
    "description": "<p>我的-已登入 获取所有宝宝基本信息</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "openId",
            "description": "<p>微信openId</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "传参示例",
          "content": "{\"guid\":\"TQDV26PDGS15VAV58JA68OU5VX86GG81\"}",
          "type": "String"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "json",
            "optional": false,
            "field": "record",
            "description": "<p>宝宝基础信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回值示例",
          "content": "[{\"record\":{\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"cardId\":\"20190515000003\",\"name\":\"刘静\",\"sex\":\"女\",\"birthday\":\"2019-04-17\",\"ageDetail\":{\"year\":1,\"month\":0,\"day\":27,\"monthAge\":12.9,\"monthAgeInt\":12,\"ageDetail\":\"1岁0月27天\"},\"openId\":\"ojP-tvwqOu-kwE4qxKqhFJSC3KOw\"},\"name\":\"刘静\",\"age\":\"1岁0月27天\"},{\"record\":{\"guid\":\"TQDV26PDGS15VAV58JA68OU5VX86GG83\",\"cardId\":\"20200506000003\",\"name\":\"李大白\",\"sex\":\"1\",\"birthday\":\"2019-04-10\",\"ageDetail\":{\"year\":1,\"month\":1,\"day\":4,\"monthAge\":13.1,\"monthAgeInt\":13,\"ageDetail\":\"1岁1月4天\"},\"openId\":\"ojP-tvwqOu-kwE4qxKqhFJSC3KOw\",\"avatar\":\"http://39.100.115.122:9000/bladex-chengde/upload/20200304/8ea6b9efc40f40f194c33a845f833dc3.jpg\",\"birthHeight\":\"20\",\"birthWeight\":\"10\",\"pregnancySecond\":\"1\",\"yieldSecond\":\"1\"},\"name\":\"李大白\",\"age\":\"1岁1月4天\"}]",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/main/java/haiying/service/archives/controller/RecordController.java",
    "groupTitle": "档案信息",
    "name": "GetArchivesRecordGetbabyarchives"
  },
  {
    "type": "Get",
    "url": "/user/findOne/{openId}",
    "title": "findOne",
    "group": "档案信息",
    "description": "<p>首页通过guid获取妈妈档案信息</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "openId",
            "description": "<p>档案号</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "返回值示例",
          "content": "{\"address\":\"\",\"ageDetail\":null,\"avatar\":\"http://39.100.115.122:9000/bladex-chengde/upload/20200304/8ea6b9efc40f40f194c33a845f833dc3.jpg\",\"birthHeight\":\"20\",\"birthWeight\":\"10\",\"birthday\":null,\"cardId\":\"20200521000001\",\"createTime\":null,\"guid\":\"TQDV26PDGS15VAV58JA68OU5VX86GG86\",\"idnumber\":\"\",\"name\":\"杜甫\",\"onlyChild\":\"\",\"openId\":\"\",\"pregnancySecond\":\"1\",\"pregnantWeek\":\"39周+1天\",\"sex\":\"1\",\"status\":\"\",\"unionId\":\"\",\"yieldSecond\":\"1\"}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/main/java/haiying/service/auth/controller/UserController.java",
    "groupTitle": "档案信息",
    "name": "GetUserFindoneOpenid"
  },
  {
    "type": "Post",
    "url": "/archives/record/saveUpdateRecord",
    "title": "saveUpdateRecord",
    "group": "档案信息",
    "description": "<p>我的-修改宝宝信息</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "guid",
            "description": "<p>(必传) guid</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "birthday",
            "description": "<p>(必传) 生日</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "openId",
            "description": "<p>(必传) openId</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "name",
            "description": "<p>(必传) 姓名</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "sex",
            "description": "<p>(必传)  性别(1男2女)</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "birthHeight",
            "description": "<p>出生身高(cm)</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "birthWeight",
            "description": "<p>出生体重(kg)</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "pregnancySecond",
            "description": "<p>第几孕</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "yieldSecond",
            "description": "<p>第几产</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "pregnantWeek",
            "description": "<p>出生孕周</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "avatar",
            "description": "<p>头像</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "传参示例",
          "content": "{\"guid\":\"TQDV26PDGS15VAV58JA68OU5VX86GG84\",\"name\":\"李小白\",\"sex\":\"1\",\"birthHeight\":\"20\",\"birthWeight\":\"10\",\"pregnancySecond\":\"1\",\"yieldSecond\":\"1\",\"avatar\":\"http://39.100.115.122:9000/bladex-chengde/upload/20200304/8ea6b9efc40f40f194c33a845f833dc3.jpg\",\"pregnantWeek\":\"39周+1天\"}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "返回值示例",
          "content": "{\"status\":0,\"msg\":\"ok\",\"result\":{\"guid\":\"TQDV26PDGS15VAV58JA68OU5VX86GG84\",\"name\":\"李小白\",\"sex\":\"1\",\"pregnantWeek\":\"39周+1天\",\"avatar\":\"http://39.100.115.122:9000/bladex-chengde/upload/20200304/8ea6b9efc40f40f194c33a845f833dc3.jpg\",\"birthHeight\":\"20\",\"birthWeight\":\"10\",\"pregnancySecond\":\"1\",\"yieldSecond\":\"1\"},\"success\":true}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/main/java/haiying/service/archives/controller/RecordController.java",
    "groupTitle": "档案信息",
    "name": "PostArchivesRecordSaveupdaterecord"
  },
  {
    "type": "Put",
    "url": "/user/updateUserInfo",
    "title": "updateUserInfo",
    "group": "档案信息",
    "description": "<p>我的-设置妈妈信息</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Json",
            "optional": false,
            "field": "openId",
            "description": "<p>(必传) openId</p>"
          },
          {
            "group": "Parameter",
            "type": "Json",
            "optional": false,
            "field": "nickName",
            "description": "<p>(必传) 微信用户姓名</p>"
          },
          {
            "group": "Parameter",
            "type": "Json",
            "optional": false,
            "field": "phone",
            "description": "<p>(必传) 手机号</p>"
          },
          {
            "group": "Parameter",
            "type": "Json",
            "optional": false,
            "field": "birthday",
            "description": "<p>(必传) 生日</p>"
          },
          {
            "group": "Parameter",
            "type": "Json",
            "optional": false,
            "field": "tenantId",
            "description": "<p>(必传) 租户id</p>"
          },
          {
            "group": "Parameter",
            "type": "Json",
            "optional": false,
            "field": "tenantName",
            "description": "<p>(必传) 租户名称</p>"
          },
          {
            "group": "Parameter",
            "type": "Json",
            "optional": false,
            "field": "occupation",
            "description": "<p>职业</p>"
          },
          {
            "group": "Parameter",
            "type": "Json",
            "optional": false,
            "field": "menstruation",
            "description": "<p>末次月经</p>"
          },
          {
            "group": "Parameter",
            "type": "Json",
            "optional": false,
            "field": "height",
            "description": "<p>身高(cm)</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "传参示例",
          "content": "{\"openId\":\"ojP-tvwqOu-kwE4qxKqhFJSC3KOw\",\"nickName\":\"王维\",\"phone\":\"13210698888\",\"menstruation\":\"2019年12月21日\",\"birthday\":\"1990年12月21日\",\"height\":\"170\",\"tenantId\":\"121\",\"tenantName\":\"1223\"}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "返回值示例",
          "content": "{\"status\":0,\"msg\":\"ok\",\"result\":{\"openId\":\"ojP-tvwqOu-kwE4qxKqhFJSC3KOw\",\"nickName\":\"王维\",\"gender\":0,\"loginType\":0,\"birthday\":\"1990年12月21日\",\"phone\":\"13210698888\",\"height\":\"170\",\"menstruation\":\"2019年12月21日\"},\"success\":true}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/main/java/haiying/service/auth/controller/UserController.java",
    "groupTitle": "档案信息",
    "name": "PutUserUpdateuserinfo"
  },
  {
    "type": "Post",
    "url": "/breastMilk/findBreastMilk",
    "title": "findBreastMilk",
    "group": "母乳测量",
    "description": "<p>获取母乳测量记录信息</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "guid",
            "description": "<p>档案号</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "pageSize",
            "description": "<p>每页固定数</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "pageNum",
            "description": "<p>开始</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "传参示例",
          "content": "{\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"pageSize\": 5,\"pageNum\": 1}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "json",
            "optional": false,
            "field": "openId",
            "description": "<p>openId</p>"
          },
          {
            "group": "Success 200",
            "type": "json",
            "optional": false,
            "field": "breastType",
            "description": "<p>母乳类型</p>"
          },
          {
            "group": "Success 200",
            "type": "json",
            "optional": false,
            "field": "fat",
            "description": "<p>脂肪</p>"
          },
          {
            "group": "Success 200",
            "type": "json",
            "optional": false,
            "field": "lactose",
            "description": "<p>乳糖</p>"
          },
          {
            "group": "Success 200",
            "type": "json",
            "optional": false,
            "field": "protein",
            "description": "<p>蛋白质</p>"
          },
          {
            "group": "Success 200",
            "type": "json",
            "optional": false,
            "field": "energy",
            "description": "<p>能量</p>"
          },
          {
            "group": "Success 200",
            "type": "json",
            "optional": false,
            "field": "water",
            "description": "<p>水分</p>"
          },
          {
            "group": "Success 200",
            "type": "json",
            "optional": false,
            "field": "mineral",
            "description": "<p>矿物质</p>"
          },
          {
            "group": "Success 200",
            "type": "json",
            "optional": false,
            "field": "density",
            "description": "<p>密度</p>"
          },
          {
            "group": "Success 200",
            "type": "json",
            "optional": false,
            "field": "createTime",
            "description": "<p>时间</p>"
          },
          {
            "group": "Success 200",
            "type": "json",
            "optional": false,
            "field": "result",
            "description": "<p>结果</p>"
          },
          {
            "group": "Success 200",
            "type": "json",
            "optional": false,
            "field": "proposal",
            "description": "<p>指导建议</p>"
          },
          {
            "group": "Success 200",
            "type": "json",
            "optional": false,
            "field": "age",
            "description": "<p>年龄</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回值示例",
          "content": "[{\"proposal\":\"非常好\",\"density\":\"1.041\",\"openId\":\"ojP-tvwqOu-kwE4qxKqhFJSC3KOw\",\"lactose\":\"7.86\",\"water\":\"91.52\",\"result\":\"正常\",\"createTime\":\"2020-04-24 13:20:09\",\"breastType\":\"过渡乳\",\"protein\":\"1.52\",\"fat\":\"3.80\",\"mineral\":\"0.29\",\"age\":\"3个月零4周\",\"energy\":\"63.66\"},{\"proposal\":\"非常好\",\"density\":\"1.041\",\"openId\":\"ojP-tvwqOu-kwE4qxKqhFJSC3KOw\",\"lactose\":\"7.86\",\"water\":\"91.52\",\"result\":\"正常\",\"createTime\":\"2020-04-24 13:20:09\",\"breastType\":\"过渡乳\",\"protein\":\"1.52\",\"fat\":\"3.80\",\"mineral\":\"0.29\",\"age\":\"3个月零4周\",\"energy\":\"63.66\"}]",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/main/java/haiying/service/breastmilk/controller/BreastMilkController.java",
    "groupTitle": "母乳测量",
    "name": "PostBreastmilkFindbreastmilk"
  },
  {
    "type": "Post",
    "url": "/breastMilk/saveBreastMilk",
    "title": "saveBreastMilk",
    "group": "母乳测量",
    "description": "<p>保存母乳测量信息</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "openId",
            "description": "<p>openId</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "breastType",
            "description": "<p>母乳类型</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "fat",
            "description": "<p>脂肪</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "lactose",
            "description": "<p>乳糖</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "protein",
            "description": "<p>蛋白质</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "energy",
            "description": "<p>能量</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "water",
            "description": "<p>水分</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "mineral",
            "description": "<p>矿物质</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "density",
            "description": "<p>密度</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "createTime",
            "description": "<p>时间</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "result",
            "description": "<p>结果</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "proposal",
            "description": "<p>指导建议</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "age",
            "description": "<p>年龄</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "传参示例",
          "content": "{\n\t\"openId\": \"ojP-tvwqOu-kwE4qxKqhFJSC3KOw\",\n\t\"breastType\": \"过渡乳\",\n\t\"fat\": \"3.80\",\n\t\"lactose\": \"7.86\",\n\t\"protein\": \"1.52\",\n\t\"energy\": \"63.66\",\n\t\"water\": \"91.52\",\n\t\"mineral\": \"0.29\",\n\t\"density\": \"1.041\",\n\t\"createTime\": \"2020-04-24 13:20:09\",\n\t\"result\": \"正常\",\n\t\"proposal\": \"非常好\",\n\t\"age\": \"3个月零4周\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "返回值示例",
          "content": "{\"status\":0,\"msg\":\"ok\",\"result\":{\"id\":\"1267755868787073024\",\"openId\":\"ojP-tvwqOu-kwE4qxKqhFJSC3KOw\",\"breastType\":\"过渡乳\",\"fat\":\"3.80\",\"lactose\":\"7.86\",\"protein\":\"1.52\",\"energy\":\"63.66\",\"water\":\"91.52\",\"mineral\":\"0.29\",\"density\":\"1.041\",\"createTime\":\"2020-04-24 13:20:09\",\"result\":\"正常\",\"proposal\":\"非常好\",\"age\":\"3个月零4周\"},\"success\":true}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/main/java/haiying/service/breastmilk/controller/BreastMilkController.java",
    "groupTitle": "母乳测量",
    "name": "PostBreastmilkSavebreastmilk"
  },
  {
    "type": "Get",
    "url": "/user/auth",
    "title": "auth",
    "group": "用户权限",
    "description": "<p>通过code获取openid等基本信息</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>微信code</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "传参示例",
          "content": "{\n \"code\": \"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "json",
            "optional": false,
            "field": "loginType",
            "description": "<p>登入状态(0注册1登入)</p>"
          },
          {
            "group": "Success 200",
            "type": "json",
            "optional": false,
            "field": "gender",
            "description": "<p>性别(0未知1男2女)</p>"
          },
          {
            "group": "Success 200",
            "type": "json",
            "optional": false,
            "field": "city",
            "description": "<p>城市</p>"
          },
          {
            "group": "Success 200",
            "type": "json",
            "optional": false,
            "field": "country",
            "description": "<p>国家</p>"
          },
          {
            "group": "Success 200",
            "type": "json",
            "optional": false,
            "field": "province",
            "description": "<p>省份</p>"
          },
          {
            "group": "Success 200",
            "type": "json",
            "optional": false,
            "field": "avatarUrl",
            "description": "<p>用户图片头像url</p>"
          },
          {
            "group": "Success 200",
            "type": "json",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p>"
          },
          {
            "group": "Success 200",
            "type": "json",
            "optional": false,
            "field": "birthday",
            "description": "<p>出生日期</p>"
          },
          {
            "group": "Success 200",
            "type": "json",
            "optional": false,
            "field": "phone",
            "description": "<p>手机号</p>"
          },
          {
            "group": "Success 200",
            "type": "json",
            "optional": false,
            "field": "height",
            "description": "<p>身高</p>"
          },
          {
            "group": "Success 200",
            "type": "json",
            "optional": false,
            "field": "menstruation",
            "description": "<p>末次月经</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回值示例",
          "content": "{\"userId\":0,\"openId\":\"ojP-tvwqOu-kwE4qxKqhFJSC3KOw\",\"gender\":0,\"city\":0,\"loginType\":0,\"sessionKey\":\"tiihtNczf5v6AKRyjwEUhQ==\"}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/main/java/haiying/service/auth/controller/UserController.java",
    "groupTitle": "用户权限",
    "name": "GetUserAuth"
  },
  {
    "type": "post",
    "url": "/user/getPhoneNumber",
    "title": "getPhoneNumber",
    "group": "用户权限",
    "description": "<p>用户权限 手机号解密</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "encryptedData",
            "description": "<p>加密数据</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "sessionKey",
            "description": "<p>sessionKey</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "iv",
            "description": "<p>初始向量</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "传参示例",
          "content": "{\"encryptedData\":\"CiyLU1Aw2KjvrjMdj8YKliAjtP4gsMZMQmRzooG2xrDcvSnxIMXFufNstNGTyaGS9uT5geRa0W4oTOb1WT7fJlAC\",\"sessionKey\":\"tiihtNczf5v6AKRyjwEUhQ==\",\"iv\":\"r7BXXKkLb8qrSNn05n0qiA==\"}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/main/java/haiying/service/auth/controller/UserController.java",
    "groupTitle": "用户权限",
    "name": "PostUserGetphonenumber"
  },
  {
    "type": "Put",
    "url": "/user/updateUserInfo",
    "title": "updateUserInfo",
    "group": "用户权限",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Json",
            "optional": false,
            "field": "User",
            "description": "<p>微信用户信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "传参示例",
          "content": "{\"openId\":\"ojP-tvwqOu-kwE4qxKqhFJSC3KOw\",\"gender\":1,\"city\":\"济南\",\"nickName\":\"王维\",\"avatarUrl\":\"dasdsa\",\"country\":\"中国\",\"province\":\"山东省\"}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "返回值示例",
          "content": "{\"status\":0,\"msg\":\"ok\",\"result\":{\"openId\":\"ojP-tvwqOu-kwE4qxKqhFJSC3KOw\",\"gender\":1,\"country\":\"中国\",\"province\":\"山东省\",\"city\":\"济南\",\"avatarUrl\":\"dasdsa\",\"loginType\":0},\"success\":true}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/main/java/haiying/service/auth/controller/UserController.java",
    "groupTitle": "用户权限",
    "name": "PutUserUpdateuserinfo"
  },
  {
    "type": "Get",
    "url": "/archives/record/findOne/{guid}",
    "title": "findOne",
    "group": "首页",
    "description": "<p>首页通过guid获取宝宝档案信息</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "guid",
            "description": "<p>档案号</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "返回值示例",
          "content": "{\"guid\":\"TQDV26PDGS15VAV58JA68OU5VX86GG81\",\"cardId\":\"20190515000003\",\"name\":\"刘静\",\"sex\":\"女\",\"birthday\":\"2019-04-17\",\"openId\":\"1\"}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/main/java/haiying/service/archives/controller/RecordController.java",
    "groupTitle": "首页",
    "name": "GetArchivesRecordFindoneGuid"
  },
  {
    "type": "Post",
    "url": "/archives/record/findMessageOne",
    "title": "findMessageOne",
    "group": "首页",
    "description": "<p>首页获取宝宝信息</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "openId",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "guid",
            "description": "<p>(没有不传)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "pageSize",
            "description": "<p>每页数</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "pageNum",
            "description": "<p>当前页</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "传参示例",
          "content": "{\"openId\":\"ob1uW5IVlh-hcEgQVoFtfCS32owY\",\"pageSize\":15,\"pageNum\":1,\"guid\":\"\"}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "json",
            "optional": false,
            "field": "record",
            "description": "<p>宝宝基础信息(同findone接口)</p>"
          },
          {
            "group": "Success 200",
            "type": "json",
            "optional": false,
            "field": "History",
            "description": "<p>宝宝历史喂养记录(同findFeedPagination接口)</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "返回值示例",
          "content": "{\"dynamics\":[{\"contents\":[{\"typeName\":\"辅食\",\"dates\":\"2020-05-18\",\"type\":\"4\",\"title\":\"辅食\",\"nurseContent\":0,\"duration\":0,\"foodName\":\"水果,蔬菜\",\"createTime\":\"2020-05-18 14:25\",\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"days\":\"2020-05-18\",\"id\":\"1262267200106422272\",\"time\":\"14:25\",\"desc\":\"辅食:水果,蔬菜\"},{\"foodDescribe\":\"222\",\"typeName\":\"辅食\",\"dates\":\"2020-05-18\",\"type\":\"4\",\"title\":\"辅食\",\"nurseContent\":0,\"duration\":0,\"foodName\":\"水果\",\"createTime\":\"2020-05-18 14:00\",\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"days\":\"2020-05-18\",\"id\":\"1262260987125850112\",\"time\":\"14:00\",\"desc\":\"辅食:水果\"},{\"foodDescribe\":\"333\",\"typeName\":\"辅食\",\"dates\":\"2020-05-18\",\"type\":\"4\",\"title\":\"辅食\",\"nurseContent\":0,\"duration\":0,\"foodName\":\"米粉\",\"createTime\":\"2020-05-18 14:00\",\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"days\":\"2020-05-18\",\"id\":\"1262261423513821184\",\"time\":\"14:00\",\"desc\":\"辅食:米粉\"},{\"duration\":0,\"createTime\":\"2020-05-18 13:59\",\"typeName\":\"配方奶\",\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"days\":\"2020-05-18\",\"dates\":\"2020-05-18\",\"id\":\"1262260689217019904\",\"time\":\"13:59\",\"type\":\"3\",\"title\":\"配方奶\",\"nurseContent\":30,\"desc\":\"配方奶:30ml\"},{\"duration\":0,\"createTime\":\"2020-05-18 13:57\",\"typeName\":\"瓶装母乳\",\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"days\":\"2020-05-18\",\"dates\":\"2020-05-18\",\"id\":\"1262260304700006400\",\"time\":\"13:57\",\"type\":\"2\",\"title\":\"瓶装母乳\",\"nurseContent\":30,\"desc\":\"瓶装母乳:30ml\"},{\"duration\":0,\"createTime\":\"2020-05-18 13:53\",\"typeName\":\"瓶装母乳\",\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"days\":\"2020-05-18\",\"dates\":\"2020-05-18\",\"id\":\"1262259929653731328\",\"time\":\"13:53\",\"type\":\"2\",\"title\":\"瓶装母乳\",\"nurseContent\":30,\"desc\":\"瓶装母乳:30ml\"},{\"typeName\":\"母乳亲喂\",\"dates\":\"2020-05-18\",\"type\":\"1\",\"title\":\"母乳亲喂\",\"nurseContent\":0,\"duration\":0,\"createTime\":\"2020-05-18 13:52\",\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"days\":\"2020-05-18\",\"id\":\"1262259108132184064\",\"endTime\":\"2020-05-18 13:52\",\"time\":\"13:52\",\"desc\":\"母乳亲喂:0分钟\"},{\"typeName\":\"母乳亲喂\",\"dates\":\"2020-05-18\",\"type\":\"1\",\"title\":\"母乳亲喂\",\"nurseContent\":0,\"duration\":0,\"createTime\":\"2020-05-18 13:51\",\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"days\":\"2020-05-18\",\"id\":\"1262258734893654016\",\"endTime\":\"2020-05-18 13:51\",\"time\":\"13:51\",\"desc\":\"母乳亲喂:0分钟\"},{\"typeName\":\"母乳亲喂\",\"dates\":\"2020-05-18\",\"type\":\"1\",\"title\":\"母乳亲喂\",\"nurseContent\":0,\"duration\":0,\"createTime\":\"2020-05-18 13:33\",\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"days\":\"2020-05-18\",\"id\":\"1262256645941518336\",\"endTime\":\"2020-05-18 13:33\",\"time\":\"13:33\",\"desc\":\"母乳亲喂:0分钟\"}],\"time\":\"2020年05月18日\"},{\"contents\":[{\"typeName\":\"母乳亲喂\",\"dates\":\"2020-04-24\",\"type\":\"1\",\"title\":\"母乳亲喂\",\"nurseContent\":0,\"duration\":2,\"createTime\":\"2020-04-24 17:05\",\"lactation\":\"左侧哺乳\",\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"days\":\"2020-04-24\",\"id\":\"1262229370823008256\",\"endTime\":\"2020-04-25 18:07\",\"time\":\"17:05\",\"desc\":\"母乳亲喂:2分钟\"},{\"typeName\":\"母乳亲喂\",\"dates\":\"2020-04-24\",\"type\":\"1\",\"title\":\"母乳亲喂\",\"nurseContent\":0,\"duration\":2,\"createTime\":\"2020-04-24 17:05\",\"lactation\":\"左侧哺乳\",\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"days\":\"2020-04-24\",\"id\":\"1262255838319894528\",\"endTime\":\"2020-04-25 18:07\",\"time\":\"17:05\",\"desc\":\"母乳亲喂:2分钟\"},{\"typeName\":\"母乳亲喂\",\"dates\":\"2020-04-24\",\"type\":\"1\",\"title\":\"母乳亲喂\",\"nurseContent\":0,\"duration\":2,\"createTime\":\"2020-04-24 17:05\",\"lactation\":\"左侧哺乳\",\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"days\":\"2020-04-24\",\"id\":\"1262255891994402816\",\"endTime\":\"2020-04-25 18:07\",\"time\":\"17:05\",\"desc\":\"母乳亲喂:2分钟\"},{\"typeName\":\"换尿布\",\"dates\":\"2020-04-24\",\"type\":\"5\",\"title\":\"换尿布\",\"nurseContent\":0,\"duration\":0,\"urineShape\":\"正常\",\"selectTypeName\":\"嘘嘘+便便\",\"createTime\":\"2020-04-24 16:18\",\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"days\":\"2020-04-24\",\"selectType\":\"3\",\"id\":\"1262296583928045568\",\"shitShape\":\"绿色\",\"time\":\"16:18\",\"desc\":\"嘘嘘+便便:正常/绿色\"},{\"typeName\":\"换尿布\",\"dates\":\"2020-04-24\",\"type\":\"5\",\"title\":\"换尿布\",\"nurseContent\":0,\"duration\":0,\"urineShape\":\"正常\",\"selectTypeName\":\"嘘嘘+便便\",\"createTime\":\"2020-04-24 16:15\",\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"days\":\"2020-04-24\",\"selectType\":\"3\",\"id\":\"1262204426756055040\",\"shitShape\":\"绿色\",\"time\":\"16:15\",\"desc\":\"嘘嘘+便便:正常/绿色\"},{\"duration\":0,\"createTime\":\"2020-04-24 16:05\",\"typeName\":\"配方奶\",\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"days\":\"2020-04-24\",\"dates\":\"2020-04-24\",\"id\":\"1262204631140294656\",\"time\":\"16:05\",\"type\":\"3\",\"title\":\"配方奶\",\"nurseContent\":120,\"desc\":\"配方奶:120ml\"}],\"time\":\"2020年04月24日\"}],\"record\":{\"birthday\":\"2019-04-17 \",\"openId\":\"ob1uW5IVlh-hcEgQVoFtfCS32owY\",\"cardId\":\"20190515000003\",\"sex\":\"女\",\"name\":\"刘静\",\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"weight\":43.0,\"avatar\":\"https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1979468705,1007738189&fm=26&gp=0.jpg\",\"height\":100.0}}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/main/java/haiying/service/archives/controller/RecordController.java",
    "groupTitle": "首页",
    "name": "PostArchivesRecordFindmessageone"
  },
  {
    "type": "Post",
    "url": "/feed/findFeedPagination",
    "title": "findFeedPagination",
    "group": "首页",
    "description": "<p>首页-动态 获取宝宝喂养记录动态历史(暂时未用)</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "guid",
            "description": "<p>档案号</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "pageSize:",
            "description": "<p>每页固定数</p>"
          },
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "pageNum:",
            "description": "<p>开始</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "传参示例",
          "content": "{\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"pageSize\":5,\"pageNum\":1}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "返回值示例",
          "content": "[{\"id\":\"1254591150861869056\",\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"typeName\":\"辅食\",\"type\":\"4\",\"createTime\":\"2020-04-24 16:05:15\",\"nurseContent\":0,\"foodName\":\"米粉\",\"foodDescribe\":\"少量\"},{\"id\":\"1254649324197404672\",\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"typeName\":\"换尿布\",\"type\":\"5\",\"createTime\":\"2020-04-23 16:15:06\",\"nurseContent\":0,\"selectType\":\"3\",\"selectTypeName\":\"xuxu+便便\",\"urineShape\":\"正常\",\"shitShape\":\"绿色\"},{\"id\":\"1255298935547256832\",\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"typeName\":\"换尿布\",\"type\":\"5\",\"createTime\":\"2020-04-23 16:15:06\",\"nurseContent\":0,\"selectTypeName\":\"xuxu+便便\",\"urineShape\":\"正常\",\"shitShape\":\"绿色\"},{\"id\":\"1254591084684140544\",\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"typeName\":\"换尿布\",\"type\":\"5\",\"createTime\":\"2020-04-23 16:07:05\",\"nurseContent\":0,\"selectType\":\"3\",\"selectTypeName\":\"xuxu+便便\",\"urineShape\":\"正常\",\"shitShape\":\"绿色\"},{\"id\":\"1254591268839251968\",\"guid\":\"EFIWFQE15PIH69NBYDFV3LPDTXTL5ES8\",\"typeName\":\"母乳亲喂\",\"type\":\"1\",\"lactation\":\"左侧哺乳\",\"createTime\":\"2020-04-23 16:06:08\",\"endTime\":\"2020-04-23 16:07:05\",\"duration\":\"2\",\"nurseContent\":0}]",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/main/java/haiying/service/feed/controller/FeedController.java",
    "groupTitle": "首页",
    "name": "PostFeedFindfeedpagination"
  }
] });
